angular.module('meuMenu', ['ionic', 'ngCordova', 'meuMenu.controllers', 'meuMenu.services'])
  .run(function ($ionicPlatform) {
    $ionicPlatform.ready(function () {
      if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
        cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
        cordova.plugins.Keyboard.disableScroll(true);

      }
      if (window.StatusBar) {
        StatusBar.styleLightContent();
      }
    });
  })
  .config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('tab', {
        url: '/tab',
        abstract: true,
        templateUrl: 'templates/tabs.html'
      })
      .state('tab.dash', {
        url: '/dash',
        views: {
          'tab-dash': {
            templateUrl: 'templates/tab-carrinho.html',
            controller: 'CarrinhoCtrl'
          }
        }
      })
      .state('tab.cardapio', {
        url: '/cardapio',
        views: {
          'tab-cardapio': {
            templateUrl: 'templates/tab-cardapio.html',
            controller: 'CardapioCtrl'
          }
        }
      })
      .state('tab.refeicao-detail', {
        url: '/cardapio/:refeicaoId',
        views: {
          'tab-cardapio': {
            templateUrl: 'templates/refeicao-detail.html',
            controller: 'RefeicaoCtrl'
          }
        }
      })
      .state('tab.pedido', {
        url: '/pedido',
        views: {
          'tab-pedido': {
            templateUrl: 'templates/tab-pedido.html',
            controller: 'PedidoCtrl'
          }
        }
      })
      .state('tab.chat-detail', {
        url: '/chats/:chatId',
        views: {
          'tab-chats': {
            templateUrl: 'templates/chat-detail.html',
            controller: 'ChatDetailCtrl'
          }
        }
      })
      .state('tab.account', {
        url: '/account',
        views: {
          'tab-account': {
            templateUrl: 'templates/tab-account.html',
            controller: 'AccountCtrl'
          }
        }
      });
    $urlRouterProvider.otherwise('/tab/cardapio');
  });
