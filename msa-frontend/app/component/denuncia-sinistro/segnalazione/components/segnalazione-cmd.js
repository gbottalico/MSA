(function () {
    "use strict";

    app.component('msaSegnalazione', {
        templateUrl: '../../app/component/denuncia-sinistro/segnalazione/components/templates/segnalazione-tpl.html',
        bindings: {
            valoriRicerca: '=',
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "<"
        },
        controller: ("segnalazioneController", ['$scope', '$rootScope', '$routeParams', '$translate', '$log', 'AccountUserSvc', 'MezziComunicazioneSvc', 'RuoliSvc', 'PlacesSvc', 'SinistriSvc', 'UtilSvc', 'RegexSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage',
            function ($scope, $rootScope, $routeParams, $translate, $log, AccountUserSvc, MezziComunicazioneSvc, RuoliSvc, PlacesSvc, SinistriSvc, UtilSvc, RegexSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {

                var ctrl = this;
                var parent = $scope.$parent;
                ctrl.luogo = undefined;


                ctrl.mapId = "M11";

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

                PlacesSvc.getTipiStrada().then(function (response) {
                    ctrl.tipiStrada =  response.data.result;
                });

                ctrl.apriSegnalazione = function () {
                    SinistriSvc.apriSegnalazione(ctrl.numeroSinistroProvvisorio, ctrl.sinistro).then(function (response) {
                        console.log(response.data.result);
                        parent.aggiornaMappe();
                    });
                };

                ctrl.bindSinitroProvvisorio = function (sinitroProvvisorio) {
                    ctrl.sinistro.segnalazione.nome = sinitroProvvisorio.segnalazione.denunciante.nome;
                    ctrl.sinistro.segnalazione.cognome = sinitroProvvisorio.segnalazione.denunciante.cognome;
                    ctrl.sinistro.segnalazione.telefono = sinitroProvvisorio.segnalazione.denunciante.telefono;
                    ctrl.sinistro.segnalazione.ruolo = sinitroProvvisorio.segnalazione.denunciante.codRuolo;

                    ctrl.sinistro.tracking.cellulare = sinitroProvvisorio.segnalazione.denunciante.tracking.cellulare;
                    ctrl.sinistro.tracking.email = sinitroProvvisorio.segnalazione.denunciante.tracking.mail;

                    ctrl.sinistro.provenienza.mezzoComunicazione = sinitroProvvisorio.segnalazione.codMezzo;

                    if(sinitroProvvisorio.segnalazione.dataDenuncia !== undefined && sinitroProvvisorio.segnalazione.dataDenuncia !== null) {
                        ctrl.sinistro.provenienza.dataDenuncia.date = new Date(sinitroProvvisorio.segnalazione.dataDenuncia);
                    } else {
                        ctrl.sinistro.provenienza.dataDenuncia.date = new Date();
                    }

                    if(sinitroProvvisorio.segnalazione.dataOraSinistro !== undefined && sinitroProvvisorio.segnalazione.dataOraSinistro !== null) {
                        ctrl.sinistro.provenienza.dataSinistro.date = new Date(sinitroProvvisorio.segnalazione.dataOraSinistro);
                    } else {
                        ctrl.sinistro.provenienza.dataSinistro.date = new Date();
                    }

                    var tempLuogo = {};
                    tempLuogo.idNazione = sinitroProvvisorio.segnalazione.codNazione;
                    tempLuogo.idProvincia = sinitroProvvisorio.segnalazione.codProvincia;
                    tempLuogo.idComune = sinitroProvvisorio.segnalazione.codComune;
                    ctrl.luogo = tempLuogo;

                    ctrl.sinistro.provenienza.oraSinistro = sinitroProvvisorio.segnalazione.oraSinistro;

                    ctrl.sinistro.garanzia = sinitroProvvisorio.segnalazione.garanziaSelected;
                    }
                ;

                ctrl.back = function () {
                    ctrl.valoriRicerca = undefined;
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.sinistroProvvisorio !== undefined &&
                            newValues.sinistroProvvisorio !== oldValues.sinistroProvvisorio) {
                            ctrl.bindSinitroProvvisorio(newValues.sinistroProvvisorio);
                        }

                    }, true
                );

            }])
    });

}());