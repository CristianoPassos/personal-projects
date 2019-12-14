(function () {
    'use strict';

    angular.module('meuMenu.cardapio')
        .config(['$routeProvider', '$locationProvider', function ($routeProvider) {
            $routeProvider
                .when('/cardapio/atual', {
                    templateUrl: 'src/app/view/cardapio/atual/form.html',
                    controller: 'cardapioCtrl',
                    controllerAs: 'ctrl'
                }).when('/cardapio/refeicao/:refeicaoId', {
                    templateUrl: 'src/app/view/cardapio/refeicao/form.html',
                    controller: 'refeicaoCtrl',
                    controllerAs: 'ctrl'
                }).when('/cardapio/refeicao/', {
                    templateUrl: 'src/app/view/cardapio/refeicao/form.html',
                    controller: 'refeicaoCtrl',
                    controllerAs: 'ctrl'
                });
        }]);
})();