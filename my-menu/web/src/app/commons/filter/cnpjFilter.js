(function() {
  'use strict';

  angular.module('portalMarketplace').filter('CnpjFilter',CnpjFilter); 

  function CnpjFilter() {
    return function(input) { return CNPJ.format(input);};
  }
})();
