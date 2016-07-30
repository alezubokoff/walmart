angular.module('shopping')

	.factory('ProdutoService', function($resource) {
		return $resource('/rest/produto');
	})

	.factory('CarrinhoService', function($resource) {
		return $resource('/rest/carrinho', null, {
			query: {
				method: 'GET',
				isArray: false
			}
		});
	})

	.factory('PedidoService', function($resource) {
		return $resource('/rest/pedido/:indice', { indice : '@indice' }, {
			update: {
				method: 'PUT'
			}
		});
	});
