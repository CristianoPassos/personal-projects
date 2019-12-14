(function (angular) {
    'use strict';

    function AlertCtrl($scope, AlertService) {
        var ctrl = this;

        ctrl.alerts = AlertService.alerts;

        ctrl.closeAlert = function (index) {
            AlertService.close(index);
        }
    }

    angular.module('meuMenu').controller('AlertCtrl', AlertCtrl);
})(window.angular);
