angular.module('shopping').controller('CarrinhoController',
function($scope, $location, $http) {
	
	$http.get('/rest/carrinho', $scope.produto).success(function(data){
		console.log(JSON.stringify(data));
		$scope.carrinho = data;
	});

});