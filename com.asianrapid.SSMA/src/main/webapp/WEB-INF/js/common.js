/**
 * 
 */
var AR = AsianRapid = {
		//表格初始页数
		currPageIndex:0,
		//表格初始显示行数
		currLimit:10,
		//搜索框id
		searchInputSelector:"#searchKey",
		//表格查询初始化参数
		Table_Query_Param:function(params){
			var searchKey = $(AsianRapid.searchInputSelector).val();
	        return { page: params.offset/params.limit + 1, rows: params.limit , search: searchKey };
		},
		/**
		 * 表格初始化方法
		 * param:
		 * 	tableSelector:表格选择器字符串，jquery形式
		 * 	searchBtnSelector:搜索按钮的选择器，jquery形式
		 *	url:请求后台controller的url
		 *	sortId:初始化排序字段的data-field
		 *	method:请求方式
		 *	eaitBtnSelector:编辑按钮的选择器
		 *	controllerName:angular JS 的controller的值
		 *	deleteBtnSelector:删除按钮的选择器
		 *	idSelector:删除时，传id值控件的选择器
		 *	deleteDialogSelector:删除按钮弹出的dialog的选择器
		 * */
		Table_Init:function(tableSelector, searchBtnSelector, url, sortId,
				method, eaitBtnSelector, controllerName,
				deleteBtnSelector, idSelector, deleteDialogSelector){
			$(tableSelector).bootstrapTable({
	            url: url,
	            sortName: sortId,//排序列
	            striped: true,//隔行高亮行
	            sidePagination: "server",//服务器分页
	            search: true,//搜索功能
	            searchOnEnterKey: true,//ENTER键搜索
	            pagination: true,//启用分页
	            escape: true,//过滤危险字符
	            queryParams: AsianRapid.Table_Query_Param,//携带参数
	            pageCount: 10,//每页行数
	            pageIndex: 0,//起始页
	            method: method,//请求格式
	            onPageChange: function (number, size) {
	                currPageIndex = number;
	                currLimit = size
	            },
	            onLoadSuccess: function (result){
	            	//给编辑按钮绑定angular事件
	            	$(eaitBtnSelector).click(function() {
						var appElement = document.querySelector('[ng-controller=' + controllerName + ']');
		            	var $scope = angular.element(appElement).scope(); 
		            	var id = AR.getSelectId(this);
		            	$scope.loadStudent(id);
					})
					//点击删除按钮，将id传递到dialog上，并打开dialog。
					$(deleteBtnSelector).click(function() {
						var id = AR.getSelectId(this);
						$(idSelector).val(id);
						AR.showDialog(deleteDialogSelector);
					})
	            }
	        }
	        );
	
	        //搜索
	        $(searchBtnSelector).click(function () {
	            $(this).button('loading');
	            var nullparams = {};
	            $(tableSelector).bootstrapTable("refresh", nullparamss);
	            $(this).button('reset');
	        });
	        
	        //enter搜索
//   	        $(AsianRapid.searchInputSelector).keydown(function (event) {
//   	            if (event.keyCode == 13)
//   	            {
//   	                $(searchBtnSelector).click();
//   	            }
//   	        });
		},
		/**
		 * 修改搜索框的提示信息
		 * */
		changeInputValue:function(value){
			$(AsianRapid.searchInputSelector).prop('placeholder', value);
		},
		//为每行添加修改和删除按钮
	    operateFormatter:function (value, row, index) {  
	        return [  
				'<a class="btn btn-info edit_btn" href="javascript:void(0)">'+
					'<i class="halflings-icon white edit"></i>'+  
				'</a>'+
				'<a class="btn btn-danger delete_btn" href="javascript:void(0)">'+
					'<i class="halflings-icon white trash"></i> '+
				'</a>', 
	        ].join('');  
	    },
	    //获得选择的id
	    getSelectId:function(selector){
	    	return $(selector).parent().parent().children().first().text();
	    },
	    showDialog:function(selector){
	    	$(selector).modal('show');
	    }
}