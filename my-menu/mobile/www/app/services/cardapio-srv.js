(function (angular) {
  angular.module('meuMenu.services').factory('CardapioSrv', ['$http', 'ConfigSrv', CardapioSrv]);

  function CardapioSrv($http, configSrv) {

    var REST_CARDAPIO = configSrv.backend + 'fornecedor/1/cardapio/';

    var refeicoes = [{
      "id": 2,
      "titulo": "Prato do dia",
      "img": 'http://imguol.com/c/noticias/2013/10/25/nos-dias-de-exame-nada-de-experimentalismos-o-bom-e-velho-prato-de-arroz-feijao-e-bife-e-uma-otima-pedida-1382721754775_615x300.jpg',
      "descricao": " Arroz, feijão, vagem com ovos e tortinha de legumes.",
      "variacoes": [
        {
          "id": 1,
          "peso": 400,
          "preco": 17,
          "observacao": "Acompanha cupim assado",
        },
        {
          "id": 2,
          "peso": 400,
          "preco": 16,
          "observacao": "Acompanha  bife de boi",
        },
        {
          "id": 3,
          "peso": 400,
          "preco": 15,
          "observacao": "Acompanha bife de frango e saladinha",
        },
        {
          "id": 4,
          "peso": 650,
          "preco": 21,
          "observacao": "Acompanha cupim assado",
        },
        {
          "id": 5,
          "peso": 650,
          "preco": 20,
          "observacao": "Acompanha  bife de boi",
        },
        {
          "id": 6,
          "peso": 650,
          "preco": 19,
          "observacao": "Acompanha bife de frango e saladinha",
        }
      ],
      "fornecedor": {
        "id": 1
      }
    }];

    function listar() {
      return $http.get(REST_CARDAPIO);
    };

    function recuperarRefeicao(refeicaoId) {
      for (var i = 0; i < refeicoes.length; i++) {
        if (refeicoes[i].id === parseInt(refeicaoId)) {
          return refeicoes[i];
        }
      }
      return null;
    }

    function recuperarMocks() {
      return refeicoes;
    }

    return {
      mock: recuperarMocks,
      recuperarRefeicao: recuperarRefeicao,
      listar: listar
    };
  }
})(window.angular);



