(function () {
  'use strict';

  angular
    .module('portalMarketplace')
    .directive('nsInputMask', function () {
      return {
        restrict: 'A',
        link: function (scope, el, attrs) {
          $(el).inputmask(scope.$eval(attrs.nsInputMask));
        }
      };
    });
})();
