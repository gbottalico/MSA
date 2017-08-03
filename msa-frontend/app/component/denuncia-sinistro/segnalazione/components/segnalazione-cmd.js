(function () {
    "use strict";

    app.component('msaSegnalazione', {
        templateUrl: '../../app/component/denuncia-sinistro/segnalazione/components/templates/segnalazione-tpl.html',
        bindings: {
            valoriRicerca: '=',
            numeroSinistroProvvisorio: "="
        },
        controller: ("segnalazioneController", ['$scope', '$rootScope', '$translate', '$log', 'AccountUserSvc', 'MezziComunicazioneSvc', 'RuoliSvc', 'PlacesSvc', 'SinistriSvc', 'UtilSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage',
            function ($scope, $rootScope, $translate, $log, AccountUserSvc, MezziComunicazioneSvc, RuoliSvc, PlacesSvc, SinistriSvc, UtilSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {

                var ctrl = this;

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

                /* Gestione calendari */
                ctrl.today = function () {
                    ctrl.dataDenuncia = new Date();
                    ctrl.dataSinistro = new Date();

                };

                ctrl.today();

                ctrl.dateOptions = {
                    formatYear: 'yy',
                    startingDay: 1
                };
                ctrl.openDataDenuncia = function () {
                    ctrl.popup1.opened = true;

                };
                ctrl.openDataSinistro = function () {
                    ctrl.popup2.opened = true;

                };
                ctrl.setDataDenuncia = function (year, month, day) {
                    ctrl.dataDenuncia = new Date(year, month, day);

                };
                ctrl.setDataSinistro = function (year, month, day) {
                    ctrl.dataSinistro = new Date(year, month, day);

                };

                ctrl.format = "dd-MM-yyyy";

                ctrl.popup1 = {
                    opened: false
                };

                ctrl.popup2 = {
                    opened: false
                };

                /* Utilities */

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