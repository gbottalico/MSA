(function () {
    "use strict";

    app.component('msaDannoRca', {
        templateUrl: '../../app/component/denuncia-sinistro/danno-rca/components/templates/danno-rca-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("dannoRcaContoller", ['_', '$MSAC', '$rootScope', '$scope', '$debugMode', '$filter', '$uibModal', '$timeout', '$ngBootbox', 'toastr', 'DomainSvc', 'SinistriSvc', 'DebugSvc', 'UtilSvc', 'ConvertSvc', 'RegexSvc',
            function (_, $MSAC, $rootScope, $scope, $debugMode, $filter, $uibModal, $timeout, $ngBootbox, toastr, DomainSvc, SinistriSvc, DebugSvc, UtilSvc, ConvertSvc, RegexSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                var TIPO_AUTOVEICOLO = 1; // todo spostare
                var TIPO_CICLOMOTORE = 6;
                var TIPO_MOTOCICLO = 5;
                $scope.$debugMode = $debugMode;
                $scope.$regex = RegexSvc;
                $scope.$MSAC = $MSAC;

                $ctrl.mapId = 'M15';
                $ctrl.isInputConsumed = false;
                $ctrl.persistence = {};

                $ctrl.dannoRca = {
                    dannoCliente: undefined,
                    danniControparte: undefined,
                    conducente: {},
                    veicoloControparte: {},
                    veicoloCliente: {},
                    controparti: []
                };
                $ctrl.dannoRca.lesioniConducente = undefined;
                $ctrl.dannoRca.conducenteIsNotContraente = undefined;

                $ctrl.tipoVeicoli = undefined;

                DomainSvc.getTipoVeicoli().then(function (response) {
                    $ctrl.tipoVeicoli = response.data.result;
                });

                $ctrl.checkConducenteIsNotContraente = function () {
                    DebugSvc.log("conducenteIsNotContraente", !!$ctrl.dannoRca.conducenteIsNotContraente);
                    if(!$ctrl.dannoRca.conducenteIsNotContraente && ($ctrl.dannoRca.conducente.nome || $ctrl.dannoRca.conducente.cognome)) {

                        var opts = UtilSvc.buildBootBoxOptions(
                            $translate('global.generic.attenzione'),
                            $translate('global.generic.daticonducente'),
                            function success() {
                                $ctrl.dannoRca.conducente = {};
                            }, function cancel () {
                                $ctrl.dannoRca.conducenteIsNotContraente = true;
                            }
                        );
                        $ngBootbox.customDialog(opts);
                    }

                };


                $ctrl.bindDannoRca = function () {

                    if (_.isObject($ctrl.sinistroProvvisorio.dannoRca)) {
                        $ctrl.dannoRca.lesioniConducente = $ctrl.sinistroProvvisorio.dannoRca.lesioniConducente;
                        $ctrl.dannoRca.conducenteIsNotContraente = $ctrl.sinistroProvvisorio.dannoRca.conducenteDiverso;
                        if (_.isObject($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente)) {

                            $ctrl.persistence.dannoCliente = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente.danni;
                            $ctrl.dannoRca.descrizioneDannoCliente = $ctrl.persistence.dannoCliente.descrizioneDanno;

                            $ctrl.dannoRca.conducenteIsNotContraente = $ctrl.sinistroProvvisorio.dannoRca.conducenteDiverso;

                            if ($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente.anagrafica) {
                                var anagrafica = _.cloneDeep($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente.anagrafica);
                                $ctrl.dannoRca.conducente = anagrafica;

                                if (anagrafica.veicolo) {
                                    $ctrl.dannoRca.veicoloCliente.veicolo = anagrafica.veicolo;
                                    $ctrl.dannoRca.veicoloCliente.targa = anagrafica.targa;
                                    $ctrl.dannoRca.veicoloCliente.speciale = _.toString(anagrafica.targaEstera);
                                    $ctrl.dannoRca.veicoloCliente.estera = _.toString(anagrafica.targaSpeciale);
                                }
                            }

                        }

                        if (_.isObject($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniControparte)) {

                            var firstElem = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniControparte[0];
                            $ctrl.persistence.dannoControparte = firstElem.danni;
                            $ctrl.dannoRca.descrizioneDannoControparte = firstElem.danni.descrizioneDanno;
                            $ctrl.dannoRca.veicoloControparte.veicolo = firstElem.anagrafica.veicolo;
                            $ctrl.dannoRca.veicoloControparte.targa = firstElem.anagrafica.targa;
                            $ctrl.dannoRca.veicoloControparte.estera = _.toString(firstElem.anagrafica.targaEstera);
                            $ctrl.dannoRca.veicoloControparte.speciale = _.toString(firstElem.anagrafica.targaSpeciale);


                            $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniControparte.forEach(function (element, index) {
                                $ctrl.dannoRca.controparti.push(ConvertSvc.dtoToAnagrafica(element.anagrafica));
                            });

                        }

                        $timeout(function () {
                            $scope.dannoRcaForm.$setPristine();
                        });

                    }
                };

                $ctrl.salvaDannoRca = function () {

                    var dannoRca = $ctrl.dannoRca;
                    if (!$ctrl.dannoRca.conducenteIsNotContraente) {
                        dannoRca.conducente = undefined;
                    }

                    SinistriSvc.salvaDannoRcaCliente($ctrl.numeroSinistroProvvisorio, dannoRca)
                        .then(function (response) {
                            DebugSvc.log("salvaDannoRcaCliente", response);
                            if (response.data.status === 200) {
                                if ($ctrl.tempSegnalazione.nveicoli > 1) {
                                    return SinistriSvc.salvaDannoRcaControparti($ctrl.numeroSinistroProvvisorio, $ctrl.dannoRca);
                                } else {
                                    return UtilSvc.createSuccessStatePromise();
                                }
                            } else {
                                return UtilSvc.createErrorStatePromise();
                            }
                        }).then(function (response) {
                        DebugSvc.log("salvaDannoRcaControparti", response);
                        if (response.data.status === 200) {
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.dannoRcaForm.$setPristine();
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });

                };

                $ctrl.aggiungiControparte = function (index) {
                    var modalInstance = $uibModal.open({
                        animation: true,
                        backdrop: 'static', // Evita che il modal sia chiuso cliccando sullo sfondo.
                        windowClass: 'msaModal',
                        size: 'lg',
                        component: 'msaAnagraficaModal',
                        resolve: {
                            hasCompagnia: function () {
                                return true;
                            },
                            input: function () {
                                try {
                                    $ctrl.dannoRca.controparti[index].index = index;
                                } catch (err) {
                                }
                                return $ctrl.dannoRca.controparti[index];
                            }
                        }
                    });

                    modalInstance.result.then(function (controparte) {
                        DebugSvc.log("aggiungiControparte", controparte);
                        if (_.isNumber(controparte.index)) {
                            $ctrl.dannoRca.controparti[controparte.index] = controparte;
                        } else {
                            $ctrl.dannoRca.controparti.push(controparte);
                        }
                        $scope.dannoRcaForm.$setDirty();
                    }, function () {
                        DebugSvc.log("aggiungiControparte dismiss.");
                    });
                };

                $ctrl.rimuoviControparte = function (index) {
                    $ctrl.dannoRca.controparti.splice(index, 1);
                    $scope.dannoRcaForm.$setDirty();
                };

                var testFunction = function (veicolo, targaInputName) {
                    /* Metodo per risolvere gli ng-pattern a runtime.
                    *  I chiamanti di questa funzione sono IIFE, quindi è importante che il veicolo passato sia un oggetto,
                    *  in modo che venga passato il riferimento.
                    *  Per lo stesso motivo è importante passare il nome del campo nella form e non il riferimento,
                    *  perché la form potrebbe non essere inizializzata al momento dell'esecuzione della IIFE.
                    * */
                    return {
                        test: function () {
                            if (veicolo.veicolo.toString() === TIPO_AUTOVEICOLO.toString()) {
                                return RegexSvc.getTargaRegex().test($scope.dannoRcaForm[targaInputName].$viewValue);
                            }
                            else if (veicolo.veicolo.toString() === TIPO_CICLOMOTORE.toString()) {
                                return RegexSvc.getCiclomotoreRegex().test($scope.dannoRcaForm[targaInputName].$viewValue);
                            }
                            else if (veicolo.veicolo.toString() === TIPO_MOTOCICLO.toString()) {
                                return RegexSvc.getMotocicloRegex().test($scope.dannoRcaForm[targaInputName].$viewValue);
                            }
                            else return true;
                        }
                    };
                };

                $ctrl.targaContropartePattern = (() => {
                    return testFunction($ctrl.dannoRca.veicoloControparte, "inputTargaControparte");
                })();

                $ctrl.targaClientePattern = (() => {
                    return testFunction($ctrl.dannoRca.veicoloCliente, "inputTargaCliente");
                })();

                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId);
                });

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                            targa: $ctrl.dannoRca.veicoloControparte.targa,
                            danniCliente: $ctrl.dannoRca.dannoCliente,
                            danniControparte: $ctrl.dannoRca.danniControparte
                        };
                    },
                    function handleChanges(newValues, oldValues) {


                        if (newValues.sinistroProvvisorio !== undefined && !$ctrl.isInputConsumed) {
                            $ctrl.bindDannoRca();
                            $ctrl.isInputConsumed = true;
                        }

                        if (newValues.targa !== undefined) {
                            $ctrl.tempSegnalazione.targa = newValues.targa;
                        }

                    }, true
                );


            }])
    });

}());