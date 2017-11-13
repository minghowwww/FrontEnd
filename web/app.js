//将代码写在闭包函数当中
(function(){
	var app = angular.module('store', ['store-products']);
	//定义一个controller,注意命名规范
	app.controller('StoreController', function(){
		this.products = gems;
	})

//	app.controller('PanelController', function(){
//		this.tab = 1;
//		this.selectTab = function(sel){
//			this.tab = sel;
//		}
//		this.isTab = function(checkTab){
//			return this.tab === checkTab;
//		}
//	})

	app.controller('ReiewController', function(){
		this.reviews = {
			stars: null,
			body: '',
			author: ''
		};
		this.addReiew = function(product){
			product.reviews.push(this.reviews);
			console.log(product.name + product.reviews.length);
			this.reviews = {
				stars: null,
				body: '',
				author: ''
			};
		}
	})



	var gems = [
		{
			name: 'Dodecahedron',
			price: 2,
			description: 'very good product',
			canPurchase: false,
			images: [
				{
					full: 'images/nomal.jpg',
					thumb: 'images/nano.jpg'
				}
			],
			reviews: [
				{
					stars: 5,
					body: "i love this products!",
					author: "joe@thomas.com"
				},
				{
					stars: 1,
					body: "this product sucks",
					author: "tim@hater.com"
				}
			]
		}, 
		{
			name: 'Pentagonal Gem',
			price: 5.95,
			description: 'very good product',
			canPurchase: false,
			images: [
				{
					full: 'images/nomal.jpg',
					thumb: 'images/nano.jpg'
				}
			]
		}
	]
})();