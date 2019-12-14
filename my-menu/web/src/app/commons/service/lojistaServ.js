(function () {
  'use strict';

  angular.module('portalMarketplace.lojista').factory('LojistaService', ['Api', 'Upload', LojistaService]);

  function LojistaService(Api, Upload) {
    var $http = Api.lojista;
    var service = this;

    ////////////////////////////////////////INICIO////////////////////////////////////////            
    var REST_LOJISTA = '/lojistas/';

    ////////////////////////////////////////LOJISTA////////////////////////////////////////

    service.buscar = function (lojistaCNPJ) {
      return $http.get(REST_LOJISTA + lojistaCNPJ).then(function (response) {
        if (response.data) {
          response.data.forEach(function (lojista) {
            lojista.cnpjFormatado = CNPJ.format(lojista.cnpj);
          });
        }
        return response;
      });
    };

    service.buscarTodos = function () {
      return $http.get(REST_LOJISTA).then(function (response) {
        if (response.data) {
          response.data.forEach(function (lojista) {
            lojista.cnpjFormatado = CNPJ.format(lojista.cnpj);
          });
        }
        return response;
      });
    };

    return {
      buscar: service.buscar,
      buscarTodos: service.buscarTodos
    };
  }
})();
