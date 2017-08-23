(function () {
    "use strict";

    app.component('msaDenunciaContainer', {
        templateUrl: '../../app/component/denuncia-sinistro/denuncia-container/components/templates/denuncia-container-tpl.html',
        bindings: {
            valoriRicerca: '=',
        },
        controller: ("denunciaContainerController", ['$rootScope', '$scope', 'SinistriSvc', 'UtilSvc',
            function ($rootScope, $scope, SinistriSvc, UtilSvc) {

                var ctrl = this;

                ctrl.sinistroProvvisorio = undefined;
                ctrl.datiContraente = {};           // Viene passato a step-cmd.js

                ctrl.getSinistroProvvisorio = function (numeroSinistroProvvisorio) {
                    SinistriSvc.cercaSinistroProvvisorio(numeroSinistroProvvisorio).then(function (response) {
                        let result = response.data.result;
                        console.log(response);
                        console.log(result);
                        ctrl.sinistroProvvisorio = result;

                        //TODO spostare la roba che segue nel componente che la visualizza

                        ctrl.datiContraente.nome = result.contraente.nome + " " + result.contraente.cognome;

                        if (result.contraente.luogoNascita.descrizioneComune !== undefined &&
                            result.contraente.luogoNascita.descrizioneComune !== null) {
                            ctrl.datiContraente.nascita = result.contraente.luogoNascita.descrizioneComune;
                        } else {
                            ctrl.datiContraente.nascita = result.contraente.luogoNascita.descrizioneNazione;
                        }

                        ctrl.datiContraente.nascita = ctrl.datiContraente.nascita + ", " + UtilSvc.dateFormat(result.contraente.dataNascita);

                        ctrl.datiContraente.cf = result.contraente.cf;



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