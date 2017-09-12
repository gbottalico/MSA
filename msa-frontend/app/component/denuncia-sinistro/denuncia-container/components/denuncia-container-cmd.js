(function () {
    "use strict";

    app.component('msaDenunciaContainer', {
        templateUrl: '../../app/component/denuncia-sinistro/denuncia-container/components/templates/denuncia-container-tpl.html',
        bindings: {},
        controller: ("denunciaContainerController", ['$rootScope', '$scope', '$routeParams', '$location', '$debugMode', '$timeout', '$filter', '$anchorScroll', 'toastr', 'SinistriSvc', 'UtilSvc', 'PathSvc', 'DebugSvc',
            function ($rootScope, $scope, $routeParams, $location, $debugMode, $timeout, $filter, $anchorScroll, toastr, SinistriSvc, UtilSvc, PathSvc, DebugSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                $scope.$debugMode = $debugMode;
                $ctrl.tempSegnalazione = {
                    nveicoli: undefined,
                    garanzia: undefined,
                    tipoSinistro: undefined
                };
                $ctrl.mappe = [];
                $ctrl.sinistroProvvisorio = undefined;

                // Numero sinistro provvisorio da url
                $ctrl.numeroSinistroProvvisorio = $routeParams.idSinistroProvvisorio;

                $ctrl.caricaMappe = function () {
                    PathSvc.getPath($ctrl.sinistroProvvisorio.numSinistroProvv).then(function (response) {
                        DebugSvc.log("getPath", response);
                        var path = undefined;
                        if (response.data.status === 200) {
                            path = UtilSvc.mapToValueArray(response.data.result);
                            $ctrl.mappe = path;
                        } else {
                            toastr.error($translate('global.generic.erroremappe'));
                        }
                        DebugSvc.log("caricaMappe", path);
                    });
                };

                $ctrl.aggiornaMappe = function () {
                    //TODO se qualcuno cambia il tipo di garanzia in corso d'opera, probabilmente va scelto $ctrl.tempSegnalazione.garanzia.
                    var garanzia = $ctrl.sinistroProvvisorio.segnalazione ?
                        $ctrl.sinistroProvvisorio.segnalazione.garanziaSelected :
                        $ctrl.tempSegnalazione.garanzia;

                    PathSvc.getNextPath(garanzia, $ctrl.sinistroProvvisorio.numSinistroProvv).then(function (response) {
                        DebugSvc.log("getNextPath", response);
                        var path = undefined;
                        if (response.data.status === 200) {
                            path = UtilSvc.mapToValueArray(response.data.result);
                            $ctrl.mappe = path;
                        } else {
                            toastr.error($translate('global.generic.erroremappe'));
                        }
                        DebugSvc.log("aggiornaMappe", path);
                    });
                };

                $ctrl.mappaCaricata = function (mapId) {
                    DebugSvc.log(mapId + " initialized.");
                    $ctrl.tempSegnalazione.lastMap = mapId;
                };

                $ctrl.isMappaVisibile = function (nomeMappa) {
                    switch (nomeMappa) {
                        case 'M12':
                            return $ctrl.mappe.indexOf('M12') > -1;
                        case 'M13':
                            return $ctrl.mappe.indexOf('M14') > -1 && $ctrl.tempSegnalazione.nveicoli > 1;
                        case 'M14':
                            return $ctrl.mappe.indexOf('M14') > -1;
                        case 'M15':
                        case 'M18':
                            return $ctrl.mappe.indexOf('M15') > -1;
                        case 'M20':
                            return $ctrl.mappe.indexOf('M20') > -1;
                        case 'M24':
                            return $ctrl.mappe.indexOf('M24') > -1;
                        case 'M25':
                            return $ctrl.mappe.indexOf('M25') > -1; //TODO
                        case 'M26':
                            return $ctrl.mappe.indexOf('M26') > -1;
                        case 'M27':
                            return $ctrl.mappe.indexOf('M27') > -1;
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

                $scope.aggiornaMappe = function () {
                    $ctrl.aggiornaMappe();
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
                    });
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            numeroSinistroProvvisorio: $ctrl.numeroSinistroProvvisorio
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