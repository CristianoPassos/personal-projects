//ignorando jshint aguardando definição do servidor de autenticação
(function() {
  'use strict';

  angular.module('portalMarketplace.login').factory('LoginService', ['Api', LoginService]);

  function LoginService (Api) {
    var $http = Api.login; // jshint ignore:line
    var service = this;

    //var LOGIN_SERVICES = 'login';

    service.recuperarSenha = function(email){// jshint ignore:line

    };

    service.login = function(email,senha){// jshint ignore:line

    };

    return {
      recuperarSenha : service.recuperarSenha,
      login : service.login
    };
  }
})();
