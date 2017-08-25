(function () {
    "use strict";

    app.component('msaDenunciaContainer', {
        templateUrl: '../../app/component/denuncia-sinistro/denuncia-container/components/templates/denuncia-container-tpl.html',
        bindings: {
            valoriRicerca: '=',
        },
        controller: ("denunciaContainerController", ['$rootScope', '$scope', 'SinistriSvc', 'UtilSvc', 'PathSvc',
            function ($rootScope, $scope, SinistriSvc, UtilSvc, PathSvc) {

                var ctrl = this;

                ctrl.tempSegnalazione = {};

                ctrl.mappe = [];

                ctrl.sinistroProvvisorio = undefined;
                ctrl.datiContraente = {};           // Viene passato a step-cmd.js


                ctrl.caricaMappe = function () {
                  PathSvc.getPath(ctrl.sinistroProvvisorio.numSinistroProvv).then(function (response) {
                      var path = UtilSvc.mapToValueArray(response.data.result);
                      ctrl.mappe = path;
                      console.log("Mappe");
                      console.log(ctrl.mappe);
                  });
                };

                ctrl.aggiornaMappe = function () {
                    PathSvc.getNextPath(ctrl.sinistroProvvisorio.segnalazione.garanziaSelected, ctrl.sinistroProvvisorio.numSinistroProvv)
                        .then(function (response) {
                        var path = UtilSvc.mapToValueArray(response.data.result);
                        ctrl.mappe = path;
                        console.log("Mappe");
                        console.log(ctrl.mappe);
                    });
                };

                $scope.aggiornaMappe = function () {
                  ctrl.aggiornaMappe();
                };

                ctrl.getSinistroProvvisorio = function (numeroSinistroProvvisorio) {
                    SinistriSvc.cercaSinistroProvvisorio(numeroSinistroProvvisorio).then(function (response) {
                        var result = response.data.result;
                        console.log(result);
                        ctrl.sinistroProvvisorio = result;
                        ctrl.datiContraente = result.contraente;

                        ctrl.caricaMappe();

                    });
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            numeroSinistroProvvisorio: ctrl.numeroSinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {
                        // Non controllo che il numeroSinistroProvvisorio, altrimenti non posso aggiornare il modulo dei dati in step-cmd.js
                        if (newValues.numeroSinistroProvvisorio !== undefined && !(isNaN(newValues.numeroSinistroProvvisorio))) {

                            ctrl.getSinistroProvvisorio(newValues.numeroSinistroProvvisorio);
                        }
                    }, true
                );


            }])
    });

}());