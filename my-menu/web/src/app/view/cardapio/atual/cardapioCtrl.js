(function (angular) {
  angular.module('meuMenu.cardapio').controller('cardapioCtrl', ['$scope', 'CardapioService', 'PedidoService', 'ConverterUtils', CardapioCtrl]);

  function CardapioCtrl($scope, cardapioServ, pedidoServ, converterUtils) {
    var ctrl = this;

    ctrl.refeicoes = [];
    ctrl.downloadPedidos = extrairPedidos;
    ctrl.remover = remover;

    init();

    function init() {
      listar();
    }

    function listar() {
      cardapioServ.listarCardapio().then(function (response) {
        ctrl.refeicoes = response.data;
      });
    }

    function extrairPedidos() {
      pedidoServ.downloadPedidos().success(function (response) {
        var file = converterUtils.convertToExcelFile(response);
        saveAs(file, 'pedidos.xls');
      });
    }

    function remover(refeicaoId) {
      cardapioServ.delete(refeicaoId).then(function (response) {
        listar();
      });
    }
  }
})(window.angular);
