(function () {
    "use strict";

    app.component('msaStep', {
        templateUrl: '../../app/component/denuncia-sinistro/step/components/templates/step-tpl.html',
        bindings: {
            datiContraente: '<',
            mappe: '<',
            percentuale: '<'
        },
        controller: ("stepController", ['_', '$rootScope', '$scope', '$filter', '$document', '$location', '$anchorScroll', 'UtilSvc', 'DebugSvc',
            function (_, $rootScope, $scope, $filter, $document, $location, $anchorScroll, UtilSvc, DebugSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;

                $ctrl.MAX_STEPS = 8;
                $ctrl.step = 1;
                $ctrl.user = {};

                $ctrl.calcolaStep = function (percentuale) {
                    $ctrl.step = Math.ceil(percentuale * ($ctrl.MAX_STEPS - 1) / 100) + 1;
                    DebugSvc.log("step", $ctrl.step);
                };

                $ctrl.bindUser = function () {

                    $ctrl.user.nome = $ctrl.datiContraente.nome + " " + $ctrl.datiContraente.cognome;
                    $ctrl.user.cf = $ctrl.datiContraente.cf;

                    if ($ctrl.datiContraente.luogoNascita !== undefined && $ctrl.datiContraente.luogoNascita !== null) {
                        if ($ctrl.datiContraente.luogoNascita.descrizioneComune !== undefined &&
                            $ctrl.datiContraente.luogoNascita.descrizioneComune !== null) {
                            $ctrl.user.nascita = $ctrl.datiContraente.luogoNascita.descrizioneComune;
                        } else {
                            $ctrl.user.nascita = $ctrl.datiContraente.luogoNascita.descrizioneNazione;
                        }
                    }

                    if ($ctrl.datiContraente.dataNascita !== undefined && $ctrl.datiContraente.dataNascita !== null) {
                        $ctrl.user.nascita = $ctrl.user.nascita + ", " + UtilSvc.dateFormat($ctrl.datiContraente.dataNascita);
                    }

                    var citta = undefined;
                    if(_.isObject($ctrl.datiContraente.tracking.residenza)) {
                        citta = $ctrl.datiContraente.tracking.residenza.descrizioneComune ?
                                $ctrl.datiContraente.tracking.residenza.descrizioneComune :
                                $ctrl.datiContraente.tracking.residenza.descrizioneNazione;
                    }

                    //TODO creare metodo
                    $ctrl.user.residenza = $ctrl.datiContraente.tracking.indirizzo + (citta ? ", " + citta : "");

                    $ctrl.user.recapiti = [$ctrl.datiContraente.tracking.cellulare, $ctrl.datiContraente.tracking.telefono, $ctrl.datiContraente.tracking.mail];
                    $ctrl.user.recapiti = $ctrl.user.recapiti.filter(function (e) {
                        return e === 0 || e;
                    });
                    $ctrl.user.recapiti = $ctrl.user.recapiti.join(", ") || $translate("global.people.nessunrecapito");
                };


                $ctrl.scrollTo = function (divId) {
                    parent.scrollTo(divId);
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            datiContraente: $ctrl.datiContraente,
                            percentuale: $ctrl.percentuale
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (_.isObject(newValues.datiContraente)) {
                            $ctrl.bindUser();
                        }

                        if(_.isNumber(newValues.percentuale)){
                            $ctrl.calcolaStep(newValues.percentuale);
                        }

                    }, true
                );

            }])
    });

}());