(function () {
    "use strict";

    app.component('msaDenunciaContainer', {
        templateUrl: '../../app/component/denuncia-sinistro/denuncia-container/components/templates/denuncia-container-tpl.html',
        bindings: {
            valoriRicerca: '=',
            numeroSinistroProvvisorio: "="
        },
        controller: ("denunciaContainerController", ['$rootScope', '$scope', 'SinistriSvc', 'UtilSvc',
            function ($rootScope, $scope, SinistriSvc, UtilSvc) {

                var ctrl = this;

                ctrl.contraente = undefined;
                ctrl.datiContraente = {};           // Viene passato a step-cmd.js

                ctrl.getSinistroProvvisorio = function (numeroSinistroProvvisorio) {
                    SinistriSvc.cercaSinistroProvvisorio(37, numeroSinistroProvvisorio).then(function (response) {
                        //FIXME rimuovere il 37, mockup
                        if (UtilSvc.arrayHasElements(response.data.result)) {
                            var result = response.data.result[0];
                            ctrl.contraente = result;

                            ctrl.datiContraente.nome = result.contraente.nome + " " + result.contraente.cognome;
                            ctrl.datiContraente.nascita = result.contraente.descComuneNascita + ", " + result.contraente.dataNascita;
                            ctrl.datiContraente.cf = result.contraente.cf;

                        }
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