function Carrinho () {}

Carrinho.incrementar = function (indicePedido) {
	$.ajax({
        url: 'pedido/' + indicePedido + '/incrementar',
    	method: 'put'
    }).then(function(resposta) {
    	atualizar(indicePedido, resposta);
    });
}

Carrinho.decrementar = function (indicePedido) {
	$.ajax({
        url: 'pedido/' + indicePedido + '/decrementar',
    	method: 'put'
    }).then(function(resposta) {
    	atualizar(indicePedido, resposta);
    });
}

Carrinho.alterarQuantidade = function (indicePedido, quantidade) {
	$.ajax({
        url: 'pedido/' + indicePedido + '/' + quantidade,
    	method: 'put'
    }).then(function(resposta) {
    	atualizar(indicePedido, resposta);
    });
}

Carrinho.removerPedido = function (indicePedido) {
	$.ajax({
        url: 'pedido/' + indicePedido,
    	method: 'delete'
    }).then(function(resposta) {
    	var pedido = $('#pedido_' + indicePedido)[0];
    	pedido.parentNode.removeChild(pedido);
    	        
    	atualizarResumo(resposta);
    });
}

function atualizar(indicePedido, resposta) {
	if (resposta.pedido.quantidade == 0) {
		removerPedido(indicePedido);
	} else {
		$('#quantidadePedido_' + indicePedido)[0].value = resposta.pedido.quantidade;
		$('#totalPedido_' + indicePedido)[0].textContent = valorMonetario(resposta.pedido.total);
	
		atualizarResumo(resposta.resumo);
	}
}

function atualizarResumo(resumo) {
	$('#totalOriginal')[0].textContent = valorMonetario(resumo.totalOriginal);
	$('#desconto')[0].textContent = valorMonetario(resumo.desconto);
	$('#totalComDesconto')[0].textContent = valorMonetario(resumo.totalComDesconto);
	$('#quantidadeItensTitulo')[0].textContent = resumo.quantidade;
	$('#quantidadeItensSubtotal')[0].textContent = resumo.quantidade;
	$('#botaoFinalizar')[0].style.display = (resumo.podeFinalizar ? 'block' : 'none');
}

function valorMonetario(valor) {
	return 'R$ ' + Number(valor).toLocaleString('pt', { minimumFractionDigits: 2 });
}