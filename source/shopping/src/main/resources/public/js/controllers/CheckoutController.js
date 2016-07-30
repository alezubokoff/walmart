angular.module('shopping').controller('CheckoutController',
function($scope, $location, CarrinhoService) {
	
	$scope.carrinho = CarrinhoService.query();
	
	$scope.voltarCarrinho = function() {
		$location.url('/carrinho');
	};

});