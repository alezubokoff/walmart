angular.module('shopping').controller('CadastroController',
function($scope, $location, $http) {
	
	$scope.produto = {};
	
	$scope.cadastrar = function() {
		if ($scope.nomeValido() && $scope.valorValido()) {
			$http.post('/rest/produto', $scope.produto).success(function(data){
				$location.url("/carrinho");
			});
		}
	};
	
	$scope.nomeValido = function() {
		return $scope.cadastroForm.nomeProduto.$touched && !$scope.cadastroForm.nomeProduto.$error.required;
	}
	
	$scope.nomeInvalido = function() {
		return $scope.cadastroForm.nomeProduto.$touched && $scope.cadastroForm.nomeProduto.$error.required;
	}
	
	$scope.valorValido = function() {
		return $scope.cadastroForm.valorProduto.$touched && !$scope.cadastroForm.valorProduto.$error.required;
	}
	
	$scope.valorInvalido = function() {
		return $scope.cadastroForm.valorProduto.$touched && $scope.cadastroForm.valorProduto.$error.required;
	}

});