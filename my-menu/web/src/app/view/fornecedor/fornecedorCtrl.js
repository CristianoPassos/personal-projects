(function () {
  'use strict';

  angular
    .module('portalMarketplace.login')
    .controller('LoginCtrl', ['$scope','$filter','LoginService', LoginCtrl]);

  function LoginCtrl($scope,$filter,LoginService){ // jshint ignore:line
    var ctrl = this;
    ctrl.email = '';
    ctrl.senha = '';
    ctrl.alerts = [];

    ctrl.login = login;
    ctrl.recuperarSenha = recuperarSenha;
    ctrl.closeMessage = closeMessage;

    //aguardando definição do servidor de autenticação

    function login(){
      if(ctrl.email === 'richard.santana@netshoes.com' && ctrl.senha === '123'){
        message('Login efetuado com sucesso', 'success');
      }else {
      // LoginService.login(ctrl.email,ctrl.senha).success(function(){
      //
      // }).error(function(){
      message($filter('translate')('login.autenticacao.falha'), 'danger');
      // });
      }
    }

    function recuperarSenha(){
      if(ctrl.email){
        // LoginService.recuperarSenha(ctrl.email).success(function(){
          message($filter('translate')('login.autenticacao.geracaoSenha'),'success');
        // }).error(function(){
        //   message('Sistema de recuperação de senha indisponível temporariamente. Tente novamente mais tarde.','danger');
        // });
      }else {
        message($filter('translate')('login.autenticacao.emailInvalido'),'danger');
      }
    }

    var message = function (msg, typeAlert) {
     ctrl.alerts[0] = { type: typeAlert, msg: msg };
    };

     function closeMessage(index) {
      if (index) {
        ctrl.alerts.splice(index, 1);
      } else {
        ctrl.alerts = [];
      }
    }
  }
})();
