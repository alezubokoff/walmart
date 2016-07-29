angular.module('shopping').controller('CadastroController',
function($scope, $location, $http) {
	
	$scope.produto = {};
	
	$scope.campoNome = {
			maxLength: 50
	};
	
	$scope.campoValor = {}
	
	$scope.cadastrar = function() {
		if ($scope.validaNome() & $scope.validaValor()) {
			$http.post('/rest/produto', $scope.produto).success(function(data){
				$location.url("/carrinho");
			});
		}
	};
	
	$scope.validaNome = function() {
		if ($scope.cadastroForm.nomeProduto.$valid) {
			$scope.campoNome.status = 'sucesso';
			
			return true;
		} else {
			$scope.campoNome.status = 'erro';
			$scope.campoNome.mensagem = 'Campo obrigatório.';
			
			return false;
		}
	}
	
	$scope.validaValor = function() {
		console.log($scope.produto.valor)
		
		if ($scope.cadastroForm.valorProduto.$valid && $scope.produto.valor > 0) {
			$scope.campoValor.status = 'sucesso';
			
			return true;
		} else {
			$scope.campoValor.status = 'erro';
			$scope.campoValor.mensagem = 'Campo obrigatório.';
			
			return false;
		}
	}

});