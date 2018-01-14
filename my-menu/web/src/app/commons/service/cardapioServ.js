'use strict';
(function (angular) {
    angular.module('meuMenu.cardapio').factory('CardapioService', ['$http', CardapioService]);

    function CardapioService($http) {
        var service = this;

        //var BACK_END = 'http://54.233.77.217/backend/rest/';
        var BACK_END = 'http://localhost:8080/backend/rest/';
        var REST_CARDAPIO = BACK_END + 'fornecedor/1/cardapio/';
        var REST_REFEICAO = REST_CARDAPIO + 'refeicao/'

        service.listarCardapio = function () {
            return $http.get(REST_CARDAPIO);
        };

        service.bucarRefeicao = function (refeicaoId) {
            return $http.get(REST_REFEICAO + refeicaoId);
        };

        service.delete = function (refeicaoId) {
            return $http.delete(REST_REFEICAO + refeicaoId);
        };

        service.filtrar = function (filtro) {
            return $http.get(REST_GPV_LISTA_PRECOS, {
                params: filtro
            });
        };

        service.salvar = function (refeicao) {
            if (refeicao.id) {
                return $http.put(REST_REFEICAO, refeicao);
            } else {
                return $http.post(REST_REFEICAO, refeicao);
            }
        };

        return {
            listarCardapio: service.listarCardapio,
            salvar: service.salvar,
            buscarRefeicao: service.bucarRefeicao,
            delete: service.delete
        };
    }
})(window.angular);

