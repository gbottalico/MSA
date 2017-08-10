(function () {
    "use strict";

    /**
     * Selettore di data.
     * Permette la selezione di date solamente nel passato, ma il comportamento è evitabile omettendo
     * il campo maxDate.
     *
     */

    app.component('msaDateSelector', {
        templateUrl: '../../app/component/common/date-selector/components/templates/date-selector-tpl.html',
        bindings: {
            result: "=",
            required: '='
        },
        controller: ("msaDateSelectorController", ['$scope', function ($scope) {

            var ctrl = this;

            $scope.opened = false;
            $scope.format = "dd-MM-yyyy";
            ctrl.result = {};

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

            $scope.$watch("date", function (newValue, oldValue) {
                ctrl.result.date = $scope.date;
                if(ctrl.required) {
                    ctrl.result.$valid = $scope.date !== undefined;
                } else {
                    ctrl.result.$valid = true;
                }
            });
        }])
    });

}());