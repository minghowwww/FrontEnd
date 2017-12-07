/**
 *angular JS 前端controller 
 */
var studentApp = angular.module("studentApp", []);  
studentApp.controller('stuController', function ($scope, $http, $compile) {  
	$scope.scores = [
		{value : 'A', msg : 'A等级'},
		{value : 'B', msg : 'B等级'},
		{value : 'C', msg : 'C等级'},
		{value : 'D', msg : 'D等级'}
	];
	
	$scope.showInsertDialog = function() {
		//初始化对象，并设置下拉列表初始默认选中
		$scope.studentInfo = {
				score:"A"
		}
		$('.insert_btn').show();
		$('.update_btn').hide();
		AR.showDialog('#myModal');
	}
	
    $scope.insertStudent=function() { 
		    //进行表单验证
		    var bv = $('#insertForm').data('bootstrapValidator');
		    bv.validate();
		    if (bv.isValid()) {
		    	$http({
		    		method: 'post',
		    		url: '../student/insert',
		    		data: $scope.studentInfo//消息实体传参数
		    	}).then(function(data) {
		    		//成功状态
		    		if(data.data.code == 200){
		    			//dialog隐藏
		    			$('#myModal').modal('hide');
		    			//表格刷新
		    			$('#dataShow').bootstrapTable('refresh');
		    			//重置对象
		    			$scope.studentInfo = {
		    					score:"A"
		    			}
		    		}else if(data.data.code == 500){
		    			alert(data.data.msg);
		    		}
				})
	        	
		    }
    }  
    
    $scope.loadStudent=function(id){
    	$http({
    		method: 'get',
    		url: '../student/load',
    		params: {stuId: id}//url传参数
    	}).then(function(data) {
    		//成功状态
    		if(data.data.code == 200){
    			$scope.studentInfo = data.data.data;
    			$('.insert_btn').hide();
    			$('.update_btn').show();
    			AR.showDialog('#myModal');
    		}else if(data.data.code == 500){
    			alert(data.data.msg);
    		}
		})
    }
    
    $scope.updateStudent=function(){
    	$http({
    		method: 'post',
    		url: '../student/update',
    		data: $scope.studentInfo
    	}).then(function(data) {
    		//成功状态
    		if(data.data.code == 200){
    			//dialog隐藏
    			$('#myModal').modal('hide');
    			//表格刷新
    			$('#dataShow').bootstrapTable('refresh');
    			//重置对象
    			$scope.studentInfo = {
    					score:"A"
    			}
    		}else if(data.data.code == 500){
    			alert(data.data.msg);
    		}
		})
    }
    
    $scope.deleteStudent=function(id){
    	$http({
    		method: 'get',
    		url: '../student/delete',
    		params: {stuId: id}//url传参数
    	}).then(function(data) {
    		//成功状态
    		if(data.data.code == 200){
    			//表格刷新
    			$('#dataShow').bootstrapTable('refresh');
    		}else if(data.data.code == 500){
    			alert(data.data.msg);
    		}
		})
    }
    
    $scope.showURLDialog=function(){
    	$scope.url='D://stdent.xls';
    	AR.showDialog('#printcfmModel');
    }
    
    $scope.printSubmit=function(){
    	console.log($scope.url);
    	$http({
    		method: 'get',
    		url: '../student/print',
    		params: {'url': $scope.url}//url传参数
    	}).then(function(data) {
    		//成功状态
    		if(data.data.code == 200){
    			$('#printcfmModel').modal('hide');
    		}else if(data.data.code == 500){
    			$('#printError').text(data.data.msg);
    		}
		})
    }
    
});