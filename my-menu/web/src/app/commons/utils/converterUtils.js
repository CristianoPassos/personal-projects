(function () {
    'use strict';
    angular.module('meuMenu').factory('ConverterUtils', function () {
        var convertToExcelFile = function (response) {
            return new Blob([response], {type: 'application/vnd.ms-excel;charset=utf-8'});
        };

        return {
            convertToExcelFile: convertToExcelFile
        };
    });
})();
