/**
 * Created by Administrator on 2017/9/6.
 */
//分离出产品相关的代码,最好就是按照功能的不同来分离代码


(function(){
    var app = angular.module('store-products', []);

    //angular自定义标签，标签名由html中的-变成驼峰法
    app.directive('productTitle', function(){
        //返回一个指令定义对象，也就是定义你的标签如何工作
        return {
            restrict: 'A',//自定义指令的类型，E表示element，A表示属性型指令
            templateUrl: 'title.html'//指令加载的url
            //angular的自定义指令可以实现语义化
        };
    })

    //带controller的指令
    app.directive('productPanels', function(){
        return {
            restrict: 'E',
            templateUrl: 'panelshtml.html',
            controller: function(){               //将controller整合到指令中
                this.tab = 1;
                this.selectTab = function(sel){
                    this.tab = sel;
                }
                this.isTab = function(checkTab){
                    return this.tab === checkTab;
                }
            },
            controllerAs: 'panel'//为controller取别名，方便在页面中引用
        };
    })
})();