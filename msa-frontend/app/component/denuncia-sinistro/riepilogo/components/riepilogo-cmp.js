(function () {
    "use strict";

    app.component('msaRiepilogo', {
        templateUrl: '../../app/component/denuncia-sinistro/riepilogo/components/templates/riepilogo-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<"
        },
        controller: ("riepilogoController", ['_', '$MSAC', '$rootScope', '$scope', '$routeParams', '$debugMode', '$timeout', '$filter', '$location', '$uibModal', 'toastr', 'SinistriSvc', 'DebugSvc', 'PlacesSvc',
            function (_, $MSAC, $rootScope, $scope, $routeParams, $debugMode, $timeout, $filter, $location, $uibModal, toastr, SinistriSvc, DebugSvc, PlacesSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;

                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M27';
                $ctrl.sinistroProvvisorio = undefined;
                $ctrl.tipoSinistro = undefined;


                $ctrl.getSinistroProvvisorio = function (numeroSinistroProvvisorio) {
                    SinistriSvc.cercaSinistroProvvisorio(numeroSinistroProvvisorio).then(function (response) {
                        DebugSvc.log("getSinistroProvvisorio", response);
                        var result = response.data.result;
                        if (_.isObject(result)) {
                            $ctrl.sinistroProvvisorio = result;
                            $ctrl.bindRiepilogo();
                        }

                    });
                };

                $ctrl.getTipoSinistro = function (numeroSinistroProvvisorio) {
                    SinistriSvc.getTipoSinistro(numeroSinistroProvvisorio).then(function (response) {
                        DebugSvc.log("getTipoSinistro", response);
                        $ctrl.tipoSinistro = response.data.result;
                    });
                };

                $ctrl.mostraJson = function () {
                    var modalInstance = $uibModal.open({
                        animation: true,
                        backdrop: 'static', // Evita che il modal sia chiuso cliccando sullo sfondo.
                        windowClass: 'msaModal',
                        size: 'lg',
                        component: 'msaJsonModal',
                        resolve: {
                            sinistroProvvisorio: function () {
                                return $ctrl.sinistroProvvisorio;
                            }
                        }
                    });
                };

                $ctrl.bindRiepilogo = function () {
                    $ctrl.nomeCognome = $ctrl.sinistroProvvisorio.segnalazione.denunciante.nome + " " + $ctrl.sinistroProvvisorio.segnalazione.denunciante.cognome;
                    $ctrl.luogoSinistro = PlacesSvc.buildIndirizzo($ctrl.sinistroProvvisorio.segnalazione.luogoSinistro, $ctrl.sinistroProvvisorio.segnalazione.indirizzo);

                    $ctrl.cellulare = $ctrl.sinistroProvvisorio.segnalazione.denunciante.tracking.cellulare;
                    $ctrl.email = $ctrl.sinistroProvvisorio.segnalazione.denunciante.tracking.mail;
                    $ctrl.numveicoli = $ctrl.sinistroProvvisorio.eventoRca.numVeicoli;
                    $ctrl.garanzia = $ctrl.sinistroProvvisorio.segnalazione.garanziaSelected;
                    $ctrl.autorita = !!$ctrl.sinistroProvvisorio.eventoRca.codAutorita;
                    $ctrl.constatazione = $ctrl.sinistroProvvisorio.constatazioneAmichevole.caCompilata;

                    if (!_.isObject($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente)) {
                        $ctrl.conducente = $ctrl.sinistroProvvisorio.contraente;
                    } else {
                        $ctrl.conducente = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente;
                        $ctrl.conducente.targa = $ctrl.conducente.anagrafica.targa;
                    }

                    if ($ctrl.sinistroProvvisorio.eventoRca.numVeicoli > 1) {
                        $ctrl.contropartePresente = true;
                        $ctrl.controparti = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniControparte;
                    } else {
                        $ctrl.contropartePresente = false;
                    }

                    $ctrl.legali = $ctrl.sinistroProvvisorio.legali;
                    $ctrl.perito = $ctrl.sinistroProvvisorio.perito;

                    $ctrl.carrozzeria = $ctrl.sinistroProvvisorio.centroConvenzionato;
                };

                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId);
                    $ctrl.getSinistroProvvisorio($ctrl.numeroSinistroProvvisorio);
                    $ctrl.getTipoSinistro($ctrl.numeroSinistroProvvisorio);
                });

                $scope.$on($MSAC.EVENTS.MAPPA_SALVATA, function (event, message) {
                    DebugSvc.log("Aggiornamento riepilogo.");
                    $ctrl.getSinistroProvvisorio($ctrl.numeroSinistroProvvisorio);
                    $ctrl.getTipoSinistro($ctrl.numeroSinistroProvvisorio);
                });

            }])
    });

}());