angular.module('shopping', ['ngRoute', 'ngResource', 'ui.utils.masks', 'flow'])
	.config(function($routeProvider) {
		$routeProvider.when('/cadastro', {
			templateUrl: 'cadastro.html',
			controller: 'CadastroController'
	});

	$routeProvider.when('/carrinho', {
		templateUrl: 'carrinho.html',
		controller: 'CarrinhoController'
	});
	
	$routeProvider.when('/checkout', {
		templateUrl: 'checkout.html',
		controller: 'CheckoutController'
	});


	$routeProvider.otherwise({redirectTo: '/cadastro'});
});