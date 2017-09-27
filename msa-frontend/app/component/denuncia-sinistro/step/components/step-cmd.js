(function () {
    "use strict";

    app.component('msaStep', {
        templateUrl: '../../app/component/denuncia-sinistro/step/components/templates/step-tpl.html',
        bindings: {
            datiContraente: '<',
            mappe: '<',
            percentuale: '<'
        },
        controller: ("stepController", ['_', '$rootScope', '$scope', '$filter', '$document', '$location', '$anchorScroll', 'UtilSvc', 'DebugSvc', 'PlacesSvc',
            function (_, $rootScope, $scope, $filter, $document, $location, $anchorScroll, UtilSvc, DebugSvc, PlacesSvc) {

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

                    if($ctrl.datiContraente.nome) {
                        $ctrl.user.nome = $ctrl.datiContraente.nome + " " + $ctrl.datiContraente.cognome;
                    } else {
                        $ctrl.user.nome = $ctrl.datiContraente.ragioneSociale;
                    }

                    $ctrl.user.cf = $ctrl.datiContraente.cf;

                    $ctrl.user.nascita = PlacesSvc.buildIndirizzo($ctrl.datiContraente.luogoNascita, null);
                    if ($ctrl.datiContraente.dataNascita) {
                        $ctrl.user.nascita = $ctrl.user.nascita + ", " + UtilSvc.dateFormat($ctrl.datiContraente.dataNascita);
                    }
                    $ctrl.user.nascita = $ctrl.user.nascita || $translate("global.people.nondisponibile");

                    $ctrl.user.residenza =
                        PlacesSvc.buildIndirizzo($ctrl.datiContraente.tracking.residenza, $ctrl.datiContraente.tracking.indirizzo) ||
                        $translate("global.people.nondisponibile");

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