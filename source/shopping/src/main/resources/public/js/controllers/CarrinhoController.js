angular.module('shopping').controller('CarrinhoController',
function($scope, $location, CarrinhoService, PedidoService) {
	
	$scope.carrinho = CarrinhoService.query();
	
	$scope.incrementar = function(index) {
		var pedido = $scope.carrinho.pedidos[index];
		pedido.quantidade++;
		
		var carrinho = PedidoService.update({ indice: index }, pedido, function() {
			$scope.carrinho = carrinho;
		});
	};
	
	$scope.decrementar = function(index) {
		var pedido = $scope.carrinho.pedidos[index];
		pedido.quantidade--;
		
		var carrinho = PedidoService.update({ indice: index }, pedido, function() {
			$scope.carrinho = carrinho;
		});
	};
	
	$scope.remover = function(index) {
		var carrinho = PedidoService.delete({ indice: index }, null, function() {
			$scope.carrinho = carrinho;
		});
	};
	
	$scope.alterar = function(index) {
		var pedido = $scope.carrinho.pedidos[index];
		
		var carrinho = PedidoService.update({ indice: index }, pedido, function() {
			$scope.carrinho = carrinho;
		});
	};
	
	$scope.novoProduto = function() {
		$location.url('/cadastro');
	};
	
	$scope.finalizar = function() {
		$location.url('/checkout');
	};

});