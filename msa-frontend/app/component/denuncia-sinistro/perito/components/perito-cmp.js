(function () {
    "use strict";

    app.component('msaPerito', {
        templateUrl: '../../app/component/denuncia-sinistro/perito/components/templates/perito-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("peritoController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location', 'toastr', 'SinistriSvc', 'DebugSvc', 'PlacesSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, toastr, SinistriSvc, DebugSvc, PlacesSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M25';

                $ctrl.isInputConsumed = false;
                $ctrl.isIndirizzoLoaded = false;
                $ctrl.perito = undefined;
                $ctrl.indirizzo = undefined;

                $ctrl.bindPerito = function (perito) {
                    $ctrl.perito = perito;

                };

                $ctrl.cercaPerito = function () {
                    SinistriSvc.getPerito($ctrl.indirizzo).then(function (response) {
                        //TODO Checcare errori
                        $ctrl.bindPerito(response.data.result);
                    });
                };

                $ctrl.salvaPerito = function () {
                    SinistriSvc.salvaPerito($ctrl.numeroSinistroProvvisorio, $ctrl.perito).then(function (response) {
                        //TODO Checcare errori
                        DebugSvc.log("salvaPerito", response);
                    });
                };

                $ctrl.$onInit = function () {
                    parent.mappaCaricata($ctrl.mapId);
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            peritoTempSegnalazione: $ctrl.tempSegnalazione.perito,
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (_.isObject(newValues.peritoTempSegnalazione) &&
                            _.isObject(newValues.sinistroProvvisorio) &&
                            !_.isObject(newValues.sinistroProvvisorio.perito) &&
                            !$ctrl.isInputConsumed) {
                            DebugSvc.log("Using peritoTempSegnalazione");
                            $ctrl.bindPerito(newValues.peritoTempSegnalazione);
                            $ctrl.isInputConsumed = true;
                        } else if (_.isObject(newValues.sinistroProvvisorio) &&
                            _.isObject(newValues.sinistroProvvisorio.perito) &&
                            !$ctrl.isInputConsumed) {
                            DebugSvc.log("Using peritoSinistroProvvisorio");
                            $ctrl.bindPerito(newValues.sinistroProvvisorio.perito);
                            $ctrl.isInputConsumed = true;

                        }

                        if (_.isObject(newValues.sinistroProvvisorio) &&
                            _.isObject(newValues.sinistroProvvisorio.contraente) &&
                            _.isObject(newValues.sinistroProvvisorio.contraente.tracking) &&
                            !$ctrl.isIndirizzoLoaded) {
                            var promise = undefined;
                            if (_.isObject(newValues.sinistroProvvisorio.contraente.tracking.comune)) {
                                promise = PlacesSvc.getComuneById(newValues.sinistroProvvisorio.contraente.tracking.comune);
                            } else {
                                promise = PlacesSvc.getNazioneById(newValues.sinistroProvvisorio.contraente.tracking.nazione);
                            }
                            promise.then(function (response) {
                                $ctrl.indirizzo = newValues.sinistroProvvisorio.contraente.tracking.indirizzo + ", " + response.data.result;
                                $ctrl.isIndirizzoLoaded = true;
                            });
                        }


                    }, true
                );


            }])
    });

}());