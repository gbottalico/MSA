(function () {
    "use strict";

    app.component('msaDenunciaContainer', {
        templateUrl: '../../app/component/denuncia-sinistro/denuncia-container/components/templates/denuncia-container-tpl.html',
        bindings: {},
        controller: ("denunciaContainerController", ['_', '$MSAC', '$rootScope', '$scope', '$routeParams', '$location', '$debugMode', '$timeout', '$filter', '$anchorScroll', '$sessionStorage', 'toastr', 'SinistriSvc', 'UtilSvc', 'PathSvc', 'DebugSvc',
            function (_, $MSAC, $rootScope, $scope, $routeParams, $location, $debugMode, $timeout, $filter, $anchorScroll, $sessionStorage, toastr, SinistriSvc, UtilSvc, PathSvc, DebugSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                $scope.$debugMode = $debugMode;
                $scope.$storage = $sessionStorage;
                $ctrl.tempSegnalazione = {
                    nveicoli: undefined,
                    garanzia: undefined,
                    tipoSinistro: undefined,
                    numeroPolizza: undefined
                };
                $ctrl.mappe = [];
                $ctrl.sinistroProvvisorio = undefined;
                $ctrl.scrollable = false;
                $ctrl.percentuale = 0;

                $ctrl.START_MAP = "M11";

                // Numero sinistro provvisorio da url
                $ctrl.numeroSinistroProvvisorio = $routeParams.idSinistroProvvisorio;

                $ctrl.caricaMappe = function () {
                    PathSvc.getPath($ctrl.numeroSinistroProvvisorio).then(function (response) {
                        DebugSvc.log("getPath", response);
                        var path = undefined;
                        if (response.data.status === 200) {
                            path = UtilSvc.mapToValueArray(response.data.result);
                            if (path.length > 0) {
                                $ctrl.mappe = path;
                            } else {
                                $ctrl.mappe.push($ctrl.START_MAP);
                            }
                        } else {
                            toastr.error($translate('global.generic.erroremappe'));
                        }
                        DebugSvc.log("caricaMappe", $ctrl.mappe);
                    });
                };

                $ctrl.aggiornaMappe = function (callerMapId) {
                    var garanzia = $ctrl.sinistroProvvisorio.segnalazione ?
                        $ctrl.sinistroProvvisorio.segnalazione.garanziaSelected :
                        $ctrl.tempSegnalazione.garanzia;

                    // Carico la mappa successiva solo se sto salvando l'ultima caricata.
                    if ($ctrl.mappe[$ctrl.mappe.length - 1] === callerMapId) {
                        PathSvc.getNextPath(garanzia, $ctrl.numeroSinistroProvvisorio).then(function (response) {
                            DebugSvc.log("getNextPath", response);
                            var path = undefined;
                            if (response.data.status === 200) {
                                path = UtilSvc.mapToValueArray(response.data.result);
                                $ctrl.mappe = path;
                                $ctrl.aggiornaPercentuale();
                            } else {
                                toastr.error($translate('global.generic.erroremappe'));
                            }
                            DebugSvc.log("aggiornaMappe", $ctrl.mappe);
                        });
                    } else {
                        DebugSvc.log("aggiornaMappe", "Path is up-to-date.");
                    }

                    $rootScope.$broadcast($MSAC.EVENTS.MAPPA_SALVATA, {mappa: callerMapId});

                };

                $ctrl.mappaCaricata = function (mapId) {
                    DebugSvc.log(mapId + " initialized.");
                    $ctrl.tempSegnalazione.lastMap = mapId;
                    if ($ctrl.scrollable) {
                        $ctrl.scrollTo(mapId);
                    }
                };

                $ctrl.aggiornaPercentuale = function () {
                    if ($ctrl.tempSegnalazione.garanzia) {
                        PathSvc.getPercentuale($ctrl.numeroSinistroProvvisorio, $ctrl.tempSegnalazione.garanzia).then(function (response) {
                            DebugSvc.log("getPercentuale", response);
                            if (response.data.status === 200) {
                                $ctrl.percentuale = response.data.result;
                            }
                        });
                    } else {
                        $ctrl.percentuale = 0;
                    }
                };

                $ctrl.isMappaVisibile = function (nomeMappa) {
                    return $ctrl.mappe.indexOf(nomeMappa) > -1;
                };

                $ctrl.scrollTo = function (divId) {
                    var id = $location.hash();
                    $location.hash(divId);
                    $anchorScroll();
                    $location.hash(id);
                };

                $ctrl.setScrollabile = function () {
                    $timeout(function () {
                        //TODO assumiamo che dopo 5 secondi la pagina sia caricata
                        $ctrl.scrollable = true;
                        DebugSvc.log("Setting scrollable to true");
                    }, 5000);
                };

                $scope.scrollTo = function (divId) {
                    $ctrl.scrollTo(divId);
                };

                $scope.aggiornaMappe = function (mapId) {
                    $ctrl.aggiornaMappe(mapId);
                };

                $scope.mappaCaricata = function (mapId) {
                    $ctrl.mappaCaricata(mapId);
                };

                $scope.isMappaVisibile = function (nomeMappa) {
                    return $ctrl.isMappaVisibile(nomeMappa);
                };

                $ctrl.getSinistroProvvisorio = function (numeroSinistroProvvisorio) {
                    SinistriSvc.cercaSinistroProvvisorio(numeroSinistroProvvisorio).then(function (response) {
                        DebugSvc.log("getSinistroProvvisorio", response);
                        if(response.status === 200 && _.isObject(response.data) && response.data.result) {
                            var result = response.data.result;
                            $ctrl.sinistroProvvisorio = result;
                            $ctrl.caricaMappe();
                            $ctrl.aggiornaPercentuale();
                            $ctrl.setScrollabile();

                            $ctrl.tempSegnalazione.garanzia = $ctrl.tempSegnalazione.garanzia || (_.isObject($ctrl.sinistroProvvisorio.segnalazione) ? $ctrl.sinistroProvvisorio.segnalazione.garanziaSelected : null);
                            $ctrl.tempSegnalazione.numeroPolizza = $ctrl.sinistroProvvisorio.numeroPolizza;
                            $ctrl.tempSegnalazione.lastSearch = $scope.$storage.lastSearch;
                            if(_.isObject($ctrl.sinistroProvvisorio.segnalazione)) {
                                $ctrl.tempSegnalazione.dataEvento = new Date($ctrl.sinistroProvvisorio.segnalazione.dataOraSinistro);
                            }
                        }
                    });
                };

                $scope.$on("$locationChangeStart", function (event, next, current) {
                    DebugSvc.log("$locationChangeStart, svuoto cache sinistro");
                    $ctrl.mappe = [];
                    $ctrl.sinistroProvvisorio = undefined;
                    $ctrl.scrollable = false;
                    $ctrl.percentuale = 0;
                });

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            numeroSinistroProvvisorio: $ctrl.numeroSinistroProvvisorio,
                        };
                    },
                    function handleChanges(newValues, oldValues) {
                        // Non controllo che il numeroSinistroProvvisorio, altrimenti non posso aggiornare il modulo dei dati in step-cmd.js
                        if (newValues.numeroSinistroProvvisorio !== undefined && !(isNaN(newValues.numeroSinistroProvvisorio))) {
                            $ctrl.getSinistroProvvisorio(newValues.numeroSinistroProvvisorio);
                        }

                    }, true
                );

            }])
    });

}());