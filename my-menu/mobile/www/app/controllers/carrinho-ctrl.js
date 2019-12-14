(function (angular) {
  angular.module('meuMenu.controllers').controller('CarrinhoCtrl', ['$scope', '$ionicPopup', 'CarrinhoSrv', CarrinhoCtrl]);

  function CarrinhoCtrl($scope, $ionicPopup, CarrinhoSrv) {

    $scope.aumentarQuantidade = aumentarQuantidade;
    $scope.diminuirQuantidade = diminuirQuantidade;
    $scope.removerPedido = removerPedido;
    $scope.limparCarrinho = limparCarrinho;
    $scope.confirmarPedidos = confirmarPedidos;

    $scope.$on('$ionicView.enter', function (e) {
      init();
    });

    function init() {
      $scope.pedidos = CarrinhoSrv.pedidosRealizados();
      calcularValorTotal();
    }

    function confirmarPedidos() {
      CarrinhoSrv.enviarPedidos();
      init();
      var alertPopup = $ionicPopup.alert({
        title: 'Solicitação de Pedidos',
        template: 'Pedido(s) solicitados com sucesso!'
      });
    }

    function limparCarrinho() {
      CarrinhoSrv.limparCarrinho();
      init();
    }

    function removerPedido(pedido) {
      CarrinhoSrv.removerPedido(pedido);
      init();
    }

    function aumentarQuantidade(pedido) {
      pedido.aumentarQuantidade();
      calcularValorTotal();
    }

    function diminuirQuantidade(pedido) {
      pedido.diminuirQuantidade();
      calcularValorTotal();
    }

    function calcularValorTotal() {
      $scope.valorTotal = CarrinhoSrv.calcularValorTotal();
    }
  }
})(window.angular);
