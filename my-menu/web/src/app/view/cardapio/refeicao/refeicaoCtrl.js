(function (angular) {
  angular.module('meuMenu.cardapio').controller('refeicaoCtrl', ['$scope', '$routeParams', 'CardapioService', 'AlertService', RefeicaoCtrl]);

  function RefeicaoCtrl($scope, $routeParams, cardapioSrv, alertService) {
    var ctrl = this;

    ctrl.refeicao = [];
    ctrl.refeicao.variacoes = [];
    ctrl.novaVariacao = [];

    ctrl.iniciarNovaVariacao = inicarNovaVariacao;
    ctrl.salvarNovaVariacao = salvarNovaVariacao;
    ctrl.removerVariacao = removerVariacao;
    ctrl.sucessoMessage = sucessoMessage;
    ctrl.iniciarNovaRefeicao = iniciarNovaRefeicao;
    ctrl.postRefeicao = postRefeicao;

    init();

    function init() {
      if (null != $routeParams.refeicaoId) {
        cardapioSrv.buscarRefeicao($routeParams.refeicaoId).then(function (response) {
          ctrl.refeicao = response.data;
        });
      }
    }

    function limparVariacao() {
      ctrl.novaVariacao = {};
    }

    function salvarNovaVariacao() {
      ctrl.refeicao.variacoes.push(ctrl.novaVariacao);
      inicarNovaVariacao();
    }

    function removerVariacao(index) {
      ctrl.refeicao.variacoes.splice(index, 1);
    }

    function inicarNovaVariacao() {
      ctrl.novaVariacao = [];
    }

    function iniciarNovaRefeicao() {
      ctrl.refeicao = {};
    }

    function sucessoMessage() {
      alertService.sucessoMessage();
    }

    function postRefeicao() {
      alertService.processandoMessage();
      cardapioSrv.postRefeicao(ctrl.refeicao).then(function (response) {
        alertService.sucessoMessage();
        ctrl.limpar();
        init();
      });
    }
  }
})(window.angular);
