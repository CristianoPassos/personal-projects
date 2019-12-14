(function (angular) {
  angular.module('meuMenu.services').factory('CarrinhoSrv', ['$http', 'ConfigSrv', CarrinhoSrv]);

  function CarrinhoSrv($http, ConfigSrv) {
    var pedidos = [];

    function calcularValorTotal() {
      valorTotal = 0;
      pedidos.forEach(function (pedido) {
        valorTotal += pedido.preco * pedido.quantidade;
      });
      return valorTotal;
    }

    function enviarPedidos() {
      limparCarrinho();
    }

    function limparCarrinho() {
      pedidos = [];
    }

    function adicionarPedido(variacao, titulo) {
      pedido = containsPedido(variacao, pedidos);

      if (null === pedido) {
        variacao.titulo = titulo;
        variacao.quantidade = 1;
        variacao.aumentarQuantidade = function () {
          this.quantidade += 1;
        };
        variacao.diminuirQuantidade = function () {
          if (this.quantidade > 1) {
            this.quantidade -= 1;
          }
        };
        pedidos.push(variacao);
      } else {
        pedido.quantidade += 1;
      }
    }

    function removerPedido(pedido) {
      pedidos.splice(pedidos.indexOf(pedido), 1);
    }

    function containsPedido(obj, list) {
      var i;
      for (i = 0; i < list.length; i++) {
        if (list[i].id === obj.id) {
          return list[i];
        }
      }
      return null;
    }

    function pedidosRealizados() {
      return pedidos;
    }

    return {
      pedidosRealizados: pedidosRealizados,
      limparCarrinho: limparCarrinho,
      calcularValorTotal: calcularValorTotal,
      enviarPedidos: enviarPedidos,
      removerPedido: removerPedido,
      adicionarPedido: adicionarPedido
    }
  }
})(window.angular);

