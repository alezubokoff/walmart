angular.module('shopping').controller('CheckoutController',
function($scope, $http, $location) {
	
	$http.get('/rest/carrinho').success(function(data){
		$scope.carrinho = data;
	});
	
	$scope.voltarCarrinho = function() {
		$location.url('/carrinho');
	};

});