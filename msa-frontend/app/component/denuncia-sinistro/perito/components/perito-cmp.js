(function () {
    "use strict";

    app.component('msaPerito', {
        templateUrl: '../../app/component/denuncia-sinistro/perito/components/templates/perito-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("peritoController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location', '$timeout', 'toastr', 'SinistriSvc', 'DebugSvc', 'PlacesSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, $timeout, toastr, SinistriSvc, DebugSvc, PlacesSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M25';

                $ctrl.isInputConsumed = false;
                $ctrl.isSaved = false;
                $ctrl.perito = {};
                $ctrl.indirizzo = undefined;

                $ctrl.bindPerito = function (perito) {
                    $ctrl.perito = perito;
                    $ctrl.isSaved = true;

                };

                $ctrl.cercaPerito = function () {
                    SinistriSvc.getPerito($ctrl.indirizzo).then(function (response) {
                        DebugSvc.log("getPerito", response);
                        if (response.status === 200 && _.isObject(response.data) && response.data.status === 200) {
                            $ctrl.bindPerito(response.data.result);
                            $ctrl.perito.telefono = parseInt($ctrl.perito.telefono) + Date.now(); //FIXME mock
                            $scope.cercaPeritoForm.$setDirty();
                            $ctrl.isSaved = false;
                        }
                    });
                };

                $ctrl.salvaPerito = function () {
                    SinistriSvc.salvaPerito($ctrl.numeroSinistroProvvisorio, $ctrl.perito).then(function (response) {
                        DebugSvc.log("salvaPerito", response);
                        if (response.status === 200 && _.isObject(response.data) && response.data.status === 200) {
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.cercaPeritoForm.$setPristine();
                            $ctrl.isSaved = true;

                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId)
                });

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            peritoTempSegnalazione: $ctrl.tempSegnalazione.perito,
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (!$ctrl.isInputConsumed) {
                            if (_.isObject(newValues.peritoTempSegnalazione) &&
                                _.isObject(newValues.sinistroProvvisorio) &&
                                !_.isObject(newValues.sinistroProvvisorio.perito)) {
                                DebugSvc.log("Loading perito using peritoTempSegnalazione (temp)");
                                $ctrl.bindPerito(newValues.peritoTempSegnalazione);
                                $ctrl.isInputConsumed = true;
                            } else if (_.isObject(newValues.sinistroProvvisorio) && _.isObject(newValues.sinistroProvvisorio.perito)) {
                                DebugSvc.log("Loading perito using peritoSinistroProvvisorio (persistence)");
                                $ctrl.bindPerito(newValues.sinistroProvvisorio.perito);
                                $ctrl.isInputConsumed = true;
                            } else if (_.isObject(newValues.sinistroProvvisorio) && newValues.sinistroProvvisorio.perito === null) {
                                DebugSvc.log("Loading empty perito using peritoSinistroProvvisorio (persistence)");
                                $ctrl.isInputConsumed = true;
                                $ctrl.isSaved = true;
                            }
                        }

                        if (_.isObject(newValues.sinistroProvvisorio) &&
                            _.isObject(newValues.sinistroProvvisorio.contraente) &&
                            _.isObject(newValues.sinistroProvvisorio.contraente.tracking)) {

                            $ctrl.indirizzo = PlacesSvc.buildIndirizzo(newValues.sinistroProvvisorio.contraente.tracking.residenza, newValues.sinistroProvvisorio.contraente.tracking.indirizzo)
                        }


                    }, true
                );


            }])
    });

}());