(function (angular) {
  angular.module('meuMenu.controllers').controller('CardapioCtrl', ['$scope', 'CardapioSrv', CardapioCtrl]);

  function CardapioCtrl($scope, cardapioSrv) {
    init();

    function init() {
      $scope.cardapio = cardapioSrv.mock();

      // CardapioSrv.listar().then(function (response) {
      //  $scope.cardapio = response.data;
      // });
    }
  }
})(window.angular);
