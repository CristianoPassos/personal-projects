(function() {
  'use strict';
  angular
    .module('portalMarketplace')
    .directive('nsRowDetails', function($compile) {
      return {
        restrict: 'A',
        scope: {
          detailsData: '=nsRowDetails'
        },
        link: function(scope, element, attrs) {
          scope.detailsOpen = false;
          scope.loaded = false;

          var css = element.attr('class');
          css = css + ' hand-pointer';
          element.attr('class', css);

          var content = '';
          if (!scope.detailsData) {
            throw 'detailsData undefined';
          }

          if (!attrs.nsRowDetailsTemplate) {
            throw 'nsRowDetailsTemplate undefined';
          }

          element.on('click', function() {
            if (!scope.loaded) {
              scope.loaded = true;
              $.get(attrs.nsRowDetailsTemplate).success(function(data) {
                content = data;

                element.after($compile(
                  '<tr style=\"background-color: #fff!important;">' +
                  '  <td colspan="' + element.children().length +
                  '" style="padding: 0px!important;">' +
                  '    <div class="container-fluid" collapse="!detailsOpen" style="padding: 5px">' +
                  content +
                  '    </div>' +
                  '  </td>' +
                  '</tr>')(scope));

                scope.$apply();
              });
            }

            scope.detailsOpen = !scope.detailsOpen;
            scope.$apply();
          });
        }
      };
    });
})();
