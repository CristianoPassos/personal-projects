(function (angular) {
  angular.module('meuMenu.services').factory('PedidoSrv', ['$http', 'ConfigSrv', PedidoSrv]);

  function PedidoSrv($http, configSrv) {

    var pedidos = [
      {
        "id": 1,
        "titulo": "Feijoada",
        "quantidade": 2,
        "data": "11/01/2015",
        "total": 34
      },
      {
        "id": 2,
        "titulo": "Prato do dia",
        "quantidade": 3,
        "data": "12/01/2015",
        "total": 48,
      },
      {
        "id": 3,
        "titulo": "Strogonoff",
        "quantidade": 4,
        "data": "13/01/2015",
        "total": 60,
      },
      {
        "id": 4,
        "titulo": "Mexido",
        "quantidade": 1,
        "data": "14/01/2015",
        "total": 21,
      }
    ];

    function listarTodos() {
      return pedidos;
    }

    return {
      listarTodos: listarTodos
    }
  }
})(window.angular);
