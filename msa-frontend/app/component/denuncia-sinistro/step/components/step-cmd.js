(function () {
    "use strict";

    app.component('msaStep', {
        templateUrl: '../../app/component/denuncia-sinistro/step/components/templates/step-tpl.html',
        bindings: {
            valoriRicerca: '=',
            datiContraente: '='
        },
        controller: ("stepController", ['$rootScope', '$scope', '$filter', '$document', '$location', '$anchorScroll', 'UtilSvc', 'PlacesSvc',
            function ($rootScope, $scope, $filter, $document, $location, $anchorScroll, UtilSvc, PlacesSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $ctrl.step = 1;
                $ctrl.user = {};


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

                    if ($ctrl.datiContraente.dataNascita !== undefined) {
                        $ctrl.user.nascita = $ctrl.user.nascita + ", " + UtilSvc.dateFormat($ctrl.datiContraente.dataNascita);
                    }

                    if ($ctrl.datiContraente.tracking !== undefined && $ctrl.datiContraente.tracking !== null) {

                        var promise = undefined;

                        if ($ctrl.datiContraente.tracking.comune !== undefined && $ctrl.datiContraente.tracking.comune !== null) {
                            promise = PlacesSvc.getComuneById($ctrl.datiContraente.tracking.comune);
                        } else {
                            promise = PlacesSvc.getNazioneById($ctrl.datiContraente.tracking.nazione);
                        }

                        promise.then(function (response) {
                            $ctrl.user.residenza = $ctrl.datiContraente.tracking.indirizzo + ", " + response.data.result;
                        });

                    }

                    $ctrl.user.recapiti = [$ctrl.datiContraente.tracking.cellulare, $ctrl.datiContraente.tracking.telefono, $ctrl.datiContraente.tracking.mail];
                    $ctrl.user.recapiti = $ctrl.user.recapiti.filter(function (e) {
                        return e === 0 || e;
                    });
                    $ctrl.user.recapiti = $ctrl.user.recapiti.join(", ") || $translate("global.step.labels.nessunrecapito");
                };


                $ctrl.scrollTo = function (id) {
                    $location.hash(id);
                    var elem = angular.element(document.getElementById(id));
                    $document.scrollToElement(elem, 30, 500);
                    //$anchorScroll();
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