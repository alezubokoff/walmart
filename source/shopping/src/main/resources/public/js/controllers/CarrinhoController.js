angular.module('shopping').controller('CarrinhoController',
function($scope, $http, $location) {
	
	$http.get('/rest/carrinho', $scope.produto).success(function(data){
		$scope.carrinho = data;
	});
	
	$scope.incrementar = function(index) {
		$http.put('/rest/pedido/' + index +'/incrementar').success(function(data){
			$scope.carrinho.pedidos[index] = data.pedido;
			$scope.carrinho.resumo = data.resumo;
		});
	};
	
	$scope.decrementar = function(index) {
		$http.put('/rest/pedido/' + index +'/decrementar').success(function(data){
			if (data.pedido.quantidade > 0) {
				$scope.carrinho.pedidos[index] = data.pedido;
			} else {
				$scope.carrinho.pedidos.splice(index, 1);
			}
			
			$scope.carrinho.resumo = data.resumo;
		});
	};
	
	$scope.remover = function(index) {
		$http.delete('/rest/pedido/' + index).success(function(data){
			$scope.carrinho.pedidos.splice(index, 1);
			$scope.carrinho.resumo = data;
		});
	};
	
	$scope.alterar = function(index) {
		var url = '/rest/pedido/' + index + '/' + $scope.carrinho.pedidos[index].quantidade;
		
		$http.put(url).success(function(data){
			if (data.pedido.quantidade > 0) {
				$scope.carrinho.pedidos[index] = data.pedido;
			} else {
				$scope.carrinho.pedidos.splice(index, 1);
			}
			
			$scope.carrinho.resumo = data.resumo;
		});
	};
	
	$scope.novoProduto = function() {
		$location.url('/cadastro');
	};
	
	$scope.finalizar = function() {
		$location.url('/checkout');
	};

});