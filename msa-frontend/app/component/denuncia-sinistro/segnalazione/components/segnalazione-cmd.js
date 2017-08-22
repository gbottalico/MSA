(function () {
    "use strict";

    app.component('msaSegnalazione', {
        templateUrl: '../../app/component/denuncia-sinistro/segnalazione/components/templates/segnalazione-tpl.html',
        bindings: {
            valoriRicerca: '=',
            numeroSinistroProvvisorio: "="
        },
        controller: ("segnalazioneController", ['$scope', '$rootScope', '$routeParams', '$translate', '$log', 'AccountUserSvc', 'MezziComunicazioneSvc', 'RuoliSvc', 'PlacesSvc', 'SinistriSvc', 'UtilSvc', 'RegexSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage',
            function ($scope, $rootScope, $routeParams, $translate, $log, AccountUserSvc, MezziComunicazioneSvc, RuoliSvc, PlacesSvc, SinistriSvc, UtilSvc, RegexSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {

                var ctrl = this;

                ctrl.reEmail = RegexSvc.getEmailRegex();
                ctrl.reTelefono = RegexSvc.getTelefonoRegex();

                // Numero sinistro provvisorio da url
                ctrl.numeroSinistroProvvisorio = $routeParams.idSinistroProvvisorio;

                ctrl.mezzicomunicazione = undefined;
                ctrl.ruoli = undefined;

                // Inizializzazione delle variabili bindate ai campi della form;
                // Luogo non è gestita qui perché è gestita da msaPlaces;
                ctrl.sinistro = {
                    segnalazione: {},
                    tracking: {},
                    provenienza: {},
                };

                MezziComunicazioneSvc.getMezziComunicazione().then(function (response) {
                    ctrl.mezzicomunicazione = response.data.result;
                });

                RuoliSvc.getRuoli().then(function (response) {
                    ctrl.ruoli = response.data.result;
                });

                ctrl.tipiStrada = PlacesSvc.getTipiStrada();

                /* Utilities */

                ctrl.apriSegnalazione = function () {
                    SinistriSvc.apriSegnalazione(ctrl.numeroSinistroProvvisorio, ctrl.sinistro).then(function (response) {
                        console.log(response.data.result);
                    });
                };

                ctrl.back = function () {
                    ctrl.valoriRicerca = undefined;
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {

                        };
                    },
                    function handleChanges(newValues, oldValues) {

                    }, true
                );

            }])
    });

}());