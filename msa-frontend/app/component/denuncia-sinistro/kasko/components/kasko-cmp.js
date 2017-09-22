(function () {
    "use strict";

    app.component('msaKasko', {
        templateUrl: '../../app/component/denuncia-sinistro/kasko/components/templates/kasko-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("kaskoController", ['_', '$MSAC', '$rootScope', '$scope', '$debugMode', '$filter', '$location', '$timeout', 'toastr', 'SinistriSvc', 'DebugSvc', 'RegexSvc',
            function (_, $MSAC, $rootScope, $scope, $debugMode, $filter, $location, $timeout, toastr, SinistriSvc, DebugSvc, RegexSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $scope.$MSAC = $MSAC;
                $scope.$regex = RegexSvc;
                $ctrl.mapId = 'M32';

                $ctrl.kasko = {};
                $ctrl.isInputConsumed = false;


                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId);
                });

                $ctrl.calcolaCf = function () {
                    var luogoNascita = $ctrl.kasko.conducente.nascita.comune ?
                        $ctrl.kasko.conducente.nascita.comune.descrizione :
                        $ctrl.kasko.conducente.nascita.nazione.descrizione;

                    UtilSvc.calcolaCf($ctrl.kasko.conducente.cognome, $ctrl.kasko.conducente.nome, $ctrl.kasko.conducente.sesso, $ctrl.kasko.conducente.nascita.data, luogoNascita).then(function (response) {
                        DebugSvc.log("calcolaCf", response);
                        if (response.data.status === 200) {
                            $ctrl.kasko.conducente.cf = response.data.result;
                        } else {
                            toastr.error($translate('global.generic.cfko'));
                        }
                    });
                };

                $ctrl.isCalcolaCfDisabled = function () {
                    try {
                        return !($ctrl.kasko.conducente &&
                            $ctrl.kasko.conducente.cognome &&
                            $ctrl.kasko.conducente.nome &&
                            $ctrl.kasko.conducente.sesso &&
                            $ctrl.kasko.conducente.nascita.data);
                    } catch (error) {
                        return false;
                    }
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (_.isObject(newValues.sinistroProvvisorio) && !$ctrl.isInputConsumed) {

                            $ctrl.isInputConsumed = true;
                        }

                    }, true
                );


            }])
    });

}());
