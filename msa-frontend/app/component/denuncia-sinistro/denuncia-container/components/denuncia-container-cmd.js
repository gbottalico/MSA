(function () {
    "use strict";

    app.component('msaDenunciaContainer', {
        templateUrl: '../../app/component/denuncia-sinistro/denuncia-container/components/templates/denuncia-container-tpl.html',
        bindings: {},
        controller: ("denunciaContainerController", ['_', '$rootScope', '$scope', '$routeParams', '$location', '$debugMode', '$timeout', '$filter', '$anchorScroll', 'toastr', 'SinistriSvc', 'UtilSvc', 'PathSvc', 'DebugSvc',
            function (_, $rootScope, $scope, $routeParams, $location, $debugMode, $timeout, $filter, $anchorScroll, toastr, SinistriSvc, UtilSvc, PathSvc, DebugSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                $scope.$debugMode = $debugMode;
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
                            $ctrl.scrollable = true;
                        } else {
                            toastr.error($translate('global.generic.erroremappe'));
                        }
                        DebugSvc.log("caricaMappe", $ctrl.mappe);
                    });
                };

                $ctrl.aggiornaMappe = function (callerMapId) {
                    //TODO se qualcuno cambia il tipo di garanzia in corso d'opera, probabilmente va scelto $ctrl.tempSegnalazione.garanzia.
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
                                // Scrolla alla mappa successiva al salvataggio della precedente.
                                if (callerMapId) {
                                    var id = $ctrl.mappe[$ctrl.mappe.indexOf(callerMapId) + 1];
                                    $ctrl.scrollTo(id);
                                }
                            } else {
                                toastr.error($translate('global.generic.erroremappe'));
                            }
                            DebugSvc.log("aggiornaMappe", $ctrl.mappe);
                        });
                    } else {
                        DebugSvc.log("aggiornaMappe", "Path is up-to-date.");
                    }
                };

                $ctrl.mappaCaricata = function (mapId) {
                    DebugSvc.log(mapId + " initialized.");
                    $ctrl.tempSegnalazione.lastMap = mapId;
                    // if($ctrl.scrollable) {
                    //     $ctrl.scrollTo(mapId);
                    // }
                };

                $ctrl.aggiornaPercentuale = function () {
                    if($ctrl.tempSegnalazione.garanzia) {
                        PathSvc.getPercentuale($ctrl.numeroSinistroProvvisorio, $ctrl.tempSegnalazione.garanzia).then(function (response) {
                            DebugSvc.log("getPercentuale", response);
                            if(response.data.status === 200) {
                               $ctrl.percentuale = response.data.result;
                           }
                        });
                    } else {
                        $ctrl.percentuale = 0;
                    }
                };

                $ctrl.isMappaVisibile = function (nomeMappa) {
                    // if(!$ctrl.scrollable) TODO
                    //     return false;
                    switch (nomeMappa) {
                        case 'M12':
                            return $ctrl.mappe.indexOf('M12') > -1;
                        case 'M13':
                            return $ctrl.mappe.indexOf('M14') > -1 && $ctrl.tempSegnalazione.nveicoli > 1;
                        case 'M14':
                            return $ctrl.mappe.indexOf('M14') > -1;
                        case 'M15':
                            return $ctrl.mappe.indexOf('M15') > -1;
                        case 'M18':
                            return $ctrl.mappe.indexOf('M18') > -1;
                        case 'M20':
                            return $ctrl.mappe.indexOf('M20') > -1;
                        case 'M23':
                            return $ctrl.mappe.indexOf('M23') > -1;
                        case 'M25':
                            return $ctrl.mappe.indexOf('M25') > -1; //TODO
                        case 'M26':
                            return $ctrl.mappe.indexOf('M26') > -1;
                        case 'M27':
                            return $ctrl.mappe.indexOf('M27') > -1;
                        case 'M28':
                            return $ctrl.mappe.indexOf('M28') > -1;
                        default:
                            return false;
                    }
                };

                $ctrl.scrollTo = function (divId) {
                    var id = $location.hash();
                    $location.hash(divId);
                    $anchorScroll();
                    $location.hash(id);
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
                        var result = response.data.result;
                        DebugSvc.log("getSinistroProvvisorio", response);

                        $ctrl.sinistroProvvisorio = result;
                        $ctrl.caricaMappe();
                        $ctrl.tempSegnalazione.garanzia = $ctrl.tempSegnalazione.garanzia || (_.isObject($ctrl.sinistroProvvisorio.segnalazione) ? $ctrl.sinistroProvvisorio.segnalazione.garanziaSelected : null);
                        $ctrl.aggiornaPercentuale();
                        $ctrl.tempSegnalazione.numeroPolizza = $ctrl.sinistroProvvisorio.numeroPolizza;
                    });
                };

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