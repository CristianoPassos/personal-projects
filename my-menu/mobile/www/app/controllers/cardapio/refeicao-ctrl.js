(function (angular) {
  angular.module('meuMenu.controllers').controller('RefeicaoCtrl',
    ['$scope', '$stateParams', '$ionicPopup', 'CardapioSrv', 'CarrinhoSrv', RefeicaoCtrl]);

  function RefeicaoCtrl($scope, $stateParams, $ionicPopup, cardapioSrv, carrinhoSrv) {
    $scope.showConfirm = showConfirm;
    $scope.addToShop = addToShop;

    init();

    function init() {
      $scope.refeicao = cardapioSrv.recuperarRefeicao($stateParams.refeicaoId);
    }

    function addToShop(variacao, titulo) {
      carrinhoSrv.adicionarPedido(variacao, titulo);

      var alertPopup = $ionicPopup.alert({
        title: 'Adicionar ao Carrinho',
        template: 'Pedido adicionado ao carrinho com sucesso!'
      });
    };

    function showConfirm() {
      var confirmPopup = $ionicPopup.confirm({
        title: 'Confirmar Pedido',
        template: 'Você confirma a solicitação do pedido?'
      });

      confirmPopup.then(function (res) {
        if (res) {
          //TODO: Send pedido
        }
      });
    };
  }
})(window.angular);
