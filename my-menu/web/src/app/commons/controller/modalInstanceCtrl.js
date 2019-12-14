(function () {
  'use strict';

  function ModalInstanceCtrl($scope, $modalInstance, data, msg) {
    $scope.msg = msg;
    $scope.ok = function (valor) {
      $modalInstance.close(valor || 'ok');
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }

  angular.module('portalMarketplace.produto').controller('ModalInstanceCtrl', ModalInstanceCtrl);
})();
