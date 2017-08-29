(function () {
    "use strict";

    app.component('msaStep', {
        templateUrl: '../../app/component/denuncia-sinistro/step/components/templates/step-tpl.html',
        bindings: {
            valoriRicerca: '=',
            datiContraente: '='
        },
        controller: ("stepController", ['$rootScope', '$scope', 'UtilSvc',
            function ($rootScope, $scope, UtilSvc) {

                var $ctrl = this;
                $ctrl.step = 1;

                $ctrl.user = {};

                $ctrl.bindUser = function () {

                    $ctrl.user.nome = $ctrl.datiContraente.nome + " " + $ctrl.datiContraente.cognome;
                    $ctrl.user.cf = $ctrl.datiContraente.cf;

                    console.log($ctrl.datiContraente);

                    if ($ctrl.datiContraente.luogoNascita !== undefined && $ctrl.datiContraente.luogoNascita !== null) {
                        if ($ctrl.datiContraente.luogoNascita.descrizioneComune !== undefined &&
                            $ctrl.datiContraente.luogoNascita.descrizioneComune !== null) {
                            $ctrl.user.nascita = $ctrl.datiContraente.luogoNascita.descrizioneComune;
                        } else {
                            $ctrl.user.nascita = $ctrl.datiContraente.luogoNascita.descrizioneNazione;
                        }
                    }

                    if ($ctrl.datiContraente.dataNascita !== undefined) {
                        $ctrl.user.nascita = $ctrl.user.nascita + ", " + UtilSvc.dateFormat($ctrl.datiContraente.dataNascita);
                    }

                    $ctrl.user.residenza = $ctrl.datiContraente.tracking.tipoStrada + " " + $ctrl.datiContraente.tracking.denominazioneStrada +
                        ", " + $ctrl.datiContraente.tracking.civicoStrada;

                    //TODO: aggiungere nazione o paese

                    $ctrl.user.recapiti = [$ctrl.datiContraente.tracking.cellulare, $ctrl.datiContraente.tracking.telefono, $ctrl.datiContraente.tracking.mail];
                    $ctrl.user.recapiti = $ctrl.user.recapiti.filter(function (e) {
                        return e === 0 || e;
                    }); //TODO: spostare in utilSvc?
                    $ctrl.user.recapiti = $ctrl.user.recapiti.join(", ");
                };


                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            datiContraente: $ctrl.datiContraente
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.datiContraente !== undefined) {
                            $ctrl.bindUser();
                        }

                    }, true
                );

            }])
    });

}());