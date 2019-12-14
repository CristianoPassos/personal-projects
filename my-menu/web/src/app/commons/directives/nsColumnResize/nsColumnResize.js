(function () {
  'use strict';

  angular
    .module('portalMarketplace')
    .directive('resizeColumn', function() {
      return {
        restrict : 'A',
        link : function(scope, element) {
          element.resizableColumns();
          }
      };
    });
})();
