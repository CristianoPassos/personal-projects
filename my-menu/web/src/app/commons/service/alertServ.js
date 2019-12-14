'use strict';
(function (angular) {
    angular.module('meuMenu').factory('AlertService', [AlertService]);

    function AlertService() {
        var service = this;

        service.alerts = [];

        service.close = function (index) {
            service.alerts.splice(index, 1);
        }

        service.processandoMessage = function () {
            service.alerts.splice(0, 1);
            service.push({type: 'info', msg: 'Processando ...'})
        }

        service.sucessoMessage = function () {
            service.alerts.splice(0, 1);
            service.push({type: 'success', msg: 'Sucesso!'})
        }

        service.push = function (message) {
            service.alerts.splice(0, 1);
            service.alerts.push(message);
        }

        return {
            alerts: service.alerts,
            close: service.close,
            sucessoMessage: service.sucessoMessage,
            processandoMessage: service.processandoMessage,
            push: service.push
        };
    }
})(window.angular);

