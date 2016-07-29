angular.module('shopping').controller('CadastroController',
function($scope, $location, $http) {
	
	$scope.produto = {};
	
	$scope.cadastrar = function() {	
		$http.post('/rest/produto', $scope.produto).success(function(data){
			$location.url("/carrinho");
		});
	};

});