angular.module('shopping').controller('CarrinhoController',
function($scope, $http, $location) {
	
	$http.get('/rest/carrinho').success(function(data){
		$scope.carrinho = data;
	});
	
	$scope.incrementar = function(index) {
		var pedido = $scope.carrinho.pedidos[index];
		pedido.quantidade++;
		
		$http.put('/rest/pedido/' + index, pedido).success(function(data){
			$scope.carrinho = data;
		});
	};
	
	$scope.decrementar = function(index) {
		var pedido = $scope.carrinho.pedidos[index];
		pedido.quantidade--;
		
		$http.put('/rest/pedido/' + index, pedido).success(function(data){
			$scope.carrinho = data;
		});
	};
	
	$scope.remover = function(index) {
		$http.delete('/rest/pedido/' + index).success(function(data){
			$scope.carrinho = data;
		});
	};
	
	$scope.alterar = function(index) {
		$http.put('/rest/pedido/' + index, pedido).success(function(data){
			$scope.carrinho = data;
		});
	};
	
	$scope.novoProduto = function() {
		$location.url('/cadastro');
	};
	
	$scope.finalizar = function() {
		$location.url('/checkout');
	};

});