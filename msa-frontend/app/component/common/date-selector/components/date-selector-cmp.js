"use strict";

app.component('msaDateSelector', {
    templateUrl: '../../app/component/common/date-selector/components/templates/date-selector-tpl.html',
    bindings: {
        result: "=",
        required: '='
    },
    controller: ("msaDateSelectorController", ['$scope', function ($scope, result) {

        var ctrl = this;

        $scope.opened = false;
        $scope.format = "dd-MM-yyyy";

        $scope.today = function () {
            $scope.date = new Date();
        };

        $scope.today();

        $scope.dateOptions = {
            formatYear: 'yy',
            maxDate: new Date(),
            startingDay: 1
        };
        $scope.open = function () {

            $scope.opened = true;

        };
        $scope.setDate = function (year, month, day) {
            ctrl.date = new Date(year, month, day);
        };

    }])
});
