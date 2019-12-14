(function (angular) {
  angular.module('meuMenu.controllers').controller('PedidoCtrl', ['$scope', 'PedidoSrv', PedidoCtrl]);

  function PedidoCtrl($scope, pedidoSrv) {
    init();

    function init() {
      $scope.pedidos = pedidoSrv.listarTodos();

      // CardapioSrv.listar().then(function (response) {
      //  $scope.cardapio = response.data;
      // });
    }
  }
})(window.angular);
