'use strict';
(function (angular) {
    angular.module('meuMenu.cardapio').factory('PedidoService', ['$http', PedidoService]);

    function PedidoService($http) {
        var service = this;

        //var BACK_END = 'http://54.233.77.217/backend/rest/';
        var BACK_END = 'http://localhost:8080/backend/rest/';
        var REST_CARDAPIO = BACK_END + 'fornecedor/1/';
        var REST_PEDIDO = REST_CARDAPIO + 'pedido/'

        service.downloadPedidos = function (config) {
            config = config || {};
            config.responseType = 'arraybuffer';
            return $http.get(REST_PEDIDO + 'exportar', config);
        };

        return {
            downloadPedidos: service.downloadPedidos
        };
    }
})(window.angular);

