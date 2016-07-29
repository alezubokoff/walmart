angular.module('shopping', ['ngRoute', 'ngResource'])
	.config(function($routeProvider) {
		$routeProvider.when('/cadastro', {
			templateUrl: 'cadastro.html',
			controller: 'CadastroController'
	});

	$routeProvider.when('/carrinho', {
		templateUrl: 'carrinho.html',
		controller: 'CarrinhoController'
	});


	$routeProvider.otherwise({redirectTo: '/cadastro'});
});