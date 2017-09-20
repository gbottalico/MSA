(function () {
    "use strict";

    app.component('msaRiepilogo', {
        templateUrl: '../../app/component/denuncia-sinistro/riepilogo/components/templates/riepilogo-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<"
        },
        controller: ("riepilogoController", ['_', '$MSAC', '$rootScope', '$scope', '$routeParams', '$debugMode', '$timeout', '$filter', '$location', 'toastr', 'SinistriSvc', 'DebugSvc', 'PathSvc', 'PlacesSvc',
            function (_, $MSAC, $rootScope, $scope, $routeParams, $debugMode, $timeout, $filter, $location, toastr, SinistriSvc, DebugSvc, PathSvc, PlacesSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;

                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M27';
                $ctrl.sinistroProvvisorio = undefined;
                $ctrl.tipoSinistro = undefined;

                $ctrl.getNomeComune = function (codComune) {
                    PlacesSvc.getComuneById(codComune).then(function (response) {
                        var result = response.data.result;
                        $ctrl.nomeComune = result;

                    });
                };

                $ctrl.getSinistroProvvisorio = function (numeroSinistroProvvisorio) {
                    SinistriSvc.cercaSinistroProvvisorio(numeroSinistroProvvisorio).then(function (response) {
                        DebugSvc.log("getSinistroProvvisorio", response);
                        var result = response.data.result;
                        if (_.isObject(result)) {
                            $ctrl.sinistroProvvisorio = result;
                            $ctrl.getNomeComune(result.segnalazione.codComune); //TODO refactor
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
                    $ctrl.luogoSinistro = $ctrl.sinistroProvvisorio.segnalazione.indirizzo;
                    //TODO VEDERE PERCHE E UNDEFINED
                    if ($ctrl.nomeComune !== undefined) {
                        $ctrl.luogoSinistro = $ctrl.luogoSinistro + ", " + $ctrl.nomeComune;
                    }
                    $ctrl.cellulare = $ctrl.sinistroProvvisorio.segnalazione.denunciante.tracking.cellulare;
                    $ctrl.email = $ctrl.sinistroProvvisorio.segnalazione.denunciante.tracking.mail;
                    $ctrl.numveicoli = $ctrl.sinistroProvvisorio.eventoRca.numVeicoli;
                    //TODO PER LA GARANZIA BISOGNA VEDERE LATO MSA COME VERRA' GESTITA
                    $ctrl.garanzia = $ctrl.sinistroProvvisorio.segnalazione.garanziaSelected;
                    if ($ctrl.sinistroProvvisorio.eventoRca.codAutorita === null) {
                        $ctrl.autorita = false;
                    }
                    else {
                        $ctrl.autorita = true;
                    }

                    $ctrl.constatazione = $ctrl.sinistroProvvisorio.constatazioneAmichevole.caCompilata;

                    $ctrl.legali = $ctrl.sinistroProvvisorio.legali;


                    if ($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente === undefined) {
                        $ctrl.conducente = $ctrl.sinistroProvvisorio.contraente;
                        $ctrl.targaConducente = $ctrl.sinistroProvvisorio.targa;
                    }
                    else $ctrl.conducente = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente;


                    if ($ctrl.sinistroProvvisorio.eventoRca.numVeicoli > 1) {
                        $ctrl.contropartePresente = true;
                        $ctrl.controparti = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniControparte;
                    }
                    else $ctrl.contropartePresente = false;


                    $ctrl.perito = $ctrl.sinistroProvvisorio.perito;

                    $ctrl.centroconv = $ctrl.sinistroProvvisorio.centroConvenzionato;
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