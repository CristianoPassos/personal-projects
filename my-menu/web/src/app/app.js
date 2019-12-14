(function (angular) {
  angular.module('meuMenu', ['ngRoute', 'ui.bootstrap', 'meuMenu.cardapio'])
    .config(['$routeProvider', function ($routeProvider) {
      $routeProvider.otherwise('/');
    }]);
})(window.angular);
