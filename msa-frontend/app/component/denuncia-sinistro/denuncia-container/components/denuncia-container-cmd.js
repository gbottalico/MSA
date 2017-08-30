(function () {
    "use strict";

    app.component('msaDenunciaContainer', {
        templateUrl: '../../app/component/denuncia-sinistro/denuncia-container/components/templates/denuncia-container-tpl.html',
        bindings: {},
        controller: ("denunciaContainerController", ['$rootScope', '$scope', '$routeParams', '$debugMode', 'SinistriSvc', 'UtilSvc', 'PathSvc', 'DebugSvc',
            function ($rootScope, $scope, $routeParams, $debugMode, SinistriSvc, UtilSvc, PathSvc, DebugSvc) {

                var $ctrl = this;
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
                        var path = UtilSvc.mapToValueArray(response.data.result);
                        $ctrl.mappe = path;
                        DebugSvc.log("caricaMappe", path);
                    });
                };

                $ctrl.aggiornaMappe = function () {
                    //TODO se qualcuno cambia il tipo di garanzia in corso d'opera, probabilmente va scelto $ctrl.tempSegnalazione.garanzia.
                    var garanzia = $ctrl.sinistroProvvisorio.segnalazione ?
                        $ctrl.sinistroProvvisorio.segnalazione.garanziaSelected :
                        $ctrl.tempSegnalazione.garanzia;
                    PathSvc.getNextPath(garanzia, $ctrl.sinistroProvvisorio.numSinistroProvv).then(function (response) {
                        var path = UtilSvc.mapToValueArray(response.data.result);
                        $ctrl.mappe = path;
                        DebugSvc.log("aggiornaMappe", path);
                    });
                };

                $ctrl.isMappaVisibile = function (nomeMappa) {
                    switch (nomeMappa) {
                        case 'M12':
                            return $ctrl.mappe.indexOf('M12') > -1;
                            break;
                        case 'M13':
                            return $ctrl.mappe.indexOf('M14') > -1 && $ctrl.tempSegnalazione.nveicoli > 1;
                            break;
                        case 'M14':
                            return $ctrl.mappe.indexOf('M14') > -1;
                            break;
                        default:
                            return false;
                            break;
                    }
                };

                $scope.aggiornaMappe = function () {
                    $ctrl.aggiornaMappe();
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