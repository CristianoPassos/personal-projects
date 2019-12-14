(function() {
  'use strict';
  angular
    .module('portalMarketplace')
    .directive('nsDatePicker', function() {

      var directive = {
        require: 'ngModel',
        scope: {
          datePickerOptions: '=nsDatePicker'
        },
        link: function(scope, element, attrs, ngModelCtrl) {
          $(function() {
            var defaults = {
              changeYear: true,
              changeMonth: true,
              dateFormat: 'dd-mm-yy 00:00',
              yearRange: '2015:2200',
              minDate: attrs.minDate,
              maxDate: attrs.maxDate,
              onSelect: function(dateText) {
                ngModelCtrl.$setViewValue(dateText);
                scope.$apply();
              },
              onClose: function(dateText) {
                if (options.dataMaxId) {
                  $('#' + options.dataMaxId).datepicker('option', 'minDate', dateText);
                }

                if (options.dataMinId) {
                  $('#' + options.dataMinId).datepicker('option', 'maxDate', dateText);
                }

                ngModelCtrl.$setViewValue(dateText);
                scope.$apply();
              }
            };

            var options = {};
            if (scope.datePickerOptions) {
              options = scope.datePickerOptions;
            }

            options = angular.extend({}, defaults, options);
            element.datepicker(options);

          });
        }
      };

      return directive;
    }).directive('nsDateFilter', ['ConverterUtils', function(Utils) {
      return {
        require: 'ngModel',
        link: function(scope, element, attrs, ngModelController) {
          var formatoData = 'DD/MM/YYYY HH:mm';
          ngModelController.$parsers.push(function(data) {
            data = Utils.stringToMoment(data, formatoData);
            return data;
          });

          ngModelController.$formatters.push(function(data) {
            data = Utils.stringToMoment(data).format(formatoData);
            return data;
          });
        }
      };
    }]);
})();
