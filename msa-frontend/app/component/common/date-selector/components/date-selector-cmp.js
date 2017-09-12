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
            required: "<",
            input: "<",
            disabled: "<",
            name: "<"
        },
        controller: ("msaDateSelectorController", ['$scope', 'DebugSvc', function ($scope, DebugSvc) {

            var $ctrl = this;

            $scope.opened = false;
            $scope.format = "dd/MM/yyyy";
            $scope.name = $ctrl.name || "date" + Date.now();
            $ctrl.result = {};
            $ctrl.isInputConsumed = false;

            $scope.today = function () {
                $scope.date = new Date();
            };

            $scope.clear = function () {
                $scope.date = null;
            };

            $scope.dateOptions = {
                formatYear: 'yy',
                maxDate: new Date(),
                startingDay: 1
            };

            $scope.open = function () {
                $scope.opened = true;
            };

            $scope.today();
            $scope.clear();

            $scope.$watch(
                function watchScope(scope) {
                    return {
                        date: $scope.date,
                        input: $ctrl.input,
                        required: $ctrl.required,
                        disabled: $ctrl.disabled
                    };
                },
                function handleChanges(newValues, oldValues) {

                    /**
                     * Legge l'input date passata come parametro di inizializzazione.
                     * Osserva il cambiamento di input, perché al momento del caricamento del modulo
                     * il valore iniziale potrebbe non essere disponibile, ma una volta consumato l'input
                     * non lo usa più.
                     */

                    if (!$ctrl.isInputConsumed) {
                        if (newValues.input !== undefined) {
                            $ctrl.isInputConsumed = true;
                            $scope.date = newValues.input;

                        }
                    }

                    $ctrl.result.date = $scope.date;
                    if ($ctrl.required) {
                        $ctrl.$valid = $scope.date !== undefined;
                    } else {
                        $ctrl.$valid = true;
                    }
                    if($ctrl.disabled) {
                        $ctrl.$valid = true;
                    }
                    $ctrl.result.$valid = $ctrl.$valid;
                    $scope[$scope.name].$setValidity("date", $ctrl.$valid);
                }, true
            );


        }])
    });

}());