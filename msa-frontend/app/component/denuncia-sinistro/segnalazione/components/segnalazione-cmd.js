(function () {
    "use strict";

    app.component('msaSegnalazione', {
        templateUrl: '../../app/component/denuncia-sinistro/segnalazione/components/templates/segnalazione-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("segnalazioneController", ['$scope', '$rootScope', '$debugMode', '$filter', 'toastr', 'DomainSvc', 'PlacesSvc', 'SinistriSvc', 'UtilSvc', 'RegexSvc', 'DebugSvc',
            function ($scope, $rootScope, $debugMode, $filter, toastr, DomainSvc, PlacesSvc, SinistriSvc, UtilSvc, RegexSvc, DebugSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;

                $ctrl.mapId = "M11";

                // Variabile utilizzata per gestire gli input da DB che devono essere passati
                // alle componenti che richiedono ulteriori elaborazioni.
                $ctrl.persistence = {};
                $ctrl.persistence.luogo = undefined;
                $ctrl.persistence.dataDenuncia = undefined;
                $ctrl.persistence.dataSinistro = undefined;

                $ctrl.reEmail = RegexSvc.getEmailRegex();
                $ctrl.reTelefono = RegexSvc.getTelefonoRegex();

                $ctrl.mezzicomunicazione = undefined;
                $ctrl.ruoli = undefined;

                // Inizializzazione delle variabili bindate ai campi della form;
                // Luogo non è gestita qui perché è gestita da msaPlaces;
                $ctrl.sinistro = {
                    segnalazione: {},
                    tracking: {},
                    provenienza: {},
                };

                DomainSvc.getMezziComunicazione().then(function (response) {
                    $ctrl.mezzicomunicazione = response.data.result;
                });

                DomainSvc.getRuoli().then(function (response) {
                    $ctrl.ruoli = response.data.result;
                });

                $ctrl.apriSegnalazione = function () {
                    SinistriSvc.apriSegnalazione($ctrl.numeroSinistroProvvisorio, $ctrl.sinistro).then(function (response) {
                        DebugSvc.log("apriSegnalazione", response);
                        if (response.data.status === 200) {
                            $ctrl.tempSegnalazione.tipoSinistro = response.data.result;
                            $ctrl.tempSegnalazione.garanzia = $ctrl.sinistro.garanzia;
                            parent.aggiornaMappe();
                            toastr.success($translate('global.generic.saveok'));
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $ctrl.bindSinitroProvvisorio = function (sinitroProvvisorio) {

                    if (sinitroProvvisorio.segnalazione !== undefined && sinitroProvvisorio.segnalazione !== null) {

                        $ctrl.sinistro.segnalazione.nome = sinitroProvvisorio.segnalazione.denunciante.nome;
                        $ctrl.sinistro.segnalazione.cognome = sinitroProvvisorio.segnalazione.denunciante.cognome;
                        $ctrl.sinistro.segnalazione.telefono = sinitroProvvisorio.segnalazione.denunciante.telefono;
                        $ctrl.sinistro.segnalazione.ruolo = sinitroProvvisorio.segnalazione.denunciante.codRuolo;

                        $ctrl.sinistro.tracking.cellulare = sinitroProvvisorio.segnalazione.denunciante.tracking.cellulare;
                        $ctrl.sinistro.tracking.email = sinitroProvvisorio.segnalazione.denunciante.tracking.mail;

                        $ctrl.sinistro.provenienza.mezzoComunicazione = sinitroProvvisorio.segnalazione.codMezzo;

                        if (sinitroProvvisorio.segnalazione.dataDenuncia !== undefined && sinitroProvvisorio.segnalazione.dataDenuncia !== null) {
                            $ctrl.persistence.dataDenuncia = new Date(sinitroProvvisorio.segnalazione.dataDenuncia);
                        } else {
                            $ctrl.persistence.dataDenuncia = new Date();
                        }

                        if (sinitroProvvisorio.segnalazione.dataOraSinistro !== undefined && sinitroProvvisorio.segnalazione.dataOraSinistro !== null) {
                            $ctrl.persistence.dataSinistro = new Date(sinitroProvvisorio.segnalazione.dataOraSinistro);
                        } else {
                            $ctrl.persistence.dataSinistro = new Date();
                        }

                        $ctrl.sinistro.provenienza.oraSinistro = sinitroProvvisorio.segnalazione.oraSinistro;

                        var tempLuogo = {};
                        tempLuogo.idNazione = sinitroProvvisorio.segnalazione.codNazione;
                        tempLuogo.idProvincia = sinitroProvvisorio.segnalazione.codProvincia;
                        tempLuogo.idComune = sinitroProvvisorio.segnalazione.codComune;
                        tempLuogo.cap = sinitroProvvisorio.segnalazione.cap;
                        $ctrl.persistence.luogo = tempLuogo;

                        $ctrl.sinistro.luogo.indirizzo = sinitroProvvisorio.segnalazione.indirizzo;
                        $ctrl.sinistro.garanzia = sinitroProvvisorio.segnalazione.garanziaSelected;
                    }

                };

                $ctrl.$onInit = function () {
                    parent.mappaCaricata($ctrl.mapId);
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.sinistroProvvisorio !== undefined &&
                            newValues.sinistroProvvisorio !== oldValues.sinistroProvvisorio) {
                            $ctrl.bindSinitroProvvisorio(newValues.sinistroProvvisorio);
                        }

                    }, true
                );

            }])
    });

}());