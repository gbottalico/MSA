(function () {
    "use strict";

    app.component('msaDannoRca', {
        templateUrl: '../../app/component/denuncia-sinistro/danno-rca/components/templates/danno-rca-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("dannoRcaContoller", ['$rootScope', '$scope', '$debugMode', '$filter', '$uibModal', 'toastr', 'VeicoliSvc', 'SinistriSvc', 'DebugSvc', 'UtilSvc',
            function ($rootScope, $scope, $debugMode, $filter, $uibModal, toastr, VeicoliSvc, SinistriSvc, DebugSvc, UtilSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;

                $ctrl.mapId = 'M16';
                $ctrl.isInputConsumed = false;

                $ctrl.persistence = {};

                $ctrl.dannoRca = {
                    dannoCliente: undefined,
                    danniControparte: undefined,
                    veicoloControparte: undefined,
                    terzeParti: []
                };
                $ctrl.dannoRca.lesioniConducente = undefined;
                $ctrl.dannoRca.conducenteIsNotContraente = undefined;

                $ctrl.tipoVeicoli = undefined;

                VeicoliSvc.getTipoVeicoli().then(function (response) {
                    $ctrl.tipoVeicoli = response.data.result;
                });

                //TODO mockup
                $ctrl.dannoRca.terzeParti = [
                    {"tipoPersona":"PF","residenza":{"cap":"70025","$valid":true,"nazione":{"id":"1","inizioValidita":-2208988800000,"fineValidita":null,"descrizione":"ITALIA","sigla":"I","codFornitore":null},"provincia":{"id":"5971b860f2f59717a813994c","codNazione":1,"codProvincia":79,"iniValidita":-2208988800000,"finValidita":null,"descProvincia":"BARI","siglaProv":"BA","codFornitore":null},"comune":{"id":"597206bbf2f59737b8e25155","codNazione":"1","codProvincia":"79","codComune":"18510","fineValidita":null,"descrizione":"GRUMO APPULA","codFornitore":null,"cap":["70025"]},"indirizzo":"Grumpppp!!"},"nascita":{"data":{"date":"2017-08-24T22:00:00.000Z","$valid":true},"cap":"70020","$valid":true,"nazione":{"id":"1","inizioValidita":-2208988800000,"fineValidita":null,"descrizione":"ITALIA","sigla":"I","codFornitore":null},"provincia":{"id":"5971b860f2f59717a813994c","codNazione":1,"codProvincia":79,"iniValidita":-2208988800000,"finValidita":null,"descProvincia":"BARI","siglaProv":"BA","codFornitore":null},"comune":{"id":"597206bbf2f59737b8e25147","codNazione":"1","codProvincia":"79","codComune":"18491","fineValidita":null,"descrizione":"BINETTO","codFornitore":null,"cap":["70020"]}},"nome":"Dello Russo","cognome":"Corrado","sesso":"m","cf":"DLLCRD17M25A874G","telefono":"£45","mail":"@@@"}
                ];

                $ctrl.bindDannoRca = function () {

                    $ctrl.dannoRca.lesioniConducente = $ctrl.sinistroProvvisorio.dannoRca.lesioniConducente;

                    if ($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente !== undefined && $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente !== null) {

                        $ctrl.persistence.dannoCliente = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente.danni;
                        $ctrl.dannoRca.descrizioneDannoCliente = $ctrl.persistence.dannoCliente.descrizioneDanno;

                    }

                };

                $ctrl.salvaDannoRca = function () {

                    SinistriSvc.salvaDannoRcaCliente($ctrl.numeroSinistroProvvisorio, $ctrl.dannoRca)
                        .then(function (response) {
                            DebugSvc.log("salvaDannoRcaCliente", response);
                            if (response.data.status === 200) {
                                return SinistriSvc.salvaDannoRcaTerzeParti($ctrl.numeroSinistroProvvisorio, $ctrl.dannoRca);
                            } else {
                                //ERRORE e promise con erroee
                                return UtilSvc.createErrorStatePromise();
                            }
                        }).then(function (response) {
                            DebugSvc.log("salvaDannoRcaTerzeParti", response);
                            if (response.data.status === 200) {
                                //TODO ritorna la promessa successiva
                            } else {
                                //ERRORE e promise con erroee
                                return UtilSvc.createErrorStatePromise();
                            }
                        })

                };

                $ctrl.aggiungiTerzaParte = function () {
                    var modalInstance = $uibModal.open({
                        animation: true,
                        backdrop: 'static', // Evita che il modal sia chiuso cliccando sullo sfondo.
                        windowClass: 'msaModal',
                        size: 'lg',
                        component: 'msaAnagraficaModal',
                        resolve: {
                            items: function () {
                                return [{id: 1}, {id: 2}];
                            }
                        }
                    });

                    modalInstance.result.then(function (terzaParte) {
                        DebugSvc.log("aggiungiTerzaParte", terzaParte);
                        $ctrl.dannoRca.terzeParti.push(terzaParte);
                    }, function () {
                        DebugSvc.log("aggiungiTerzaParte dismiss.");
                    });
                };


                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.sinistroProvvisorio !== undefined && !$ctrl.isInputConsumed) {
                            $ctrl.bindDannoRca();
                            $ctrl.isInputConsumed = true;
                        }

                    }, true
                );


            }])
    });

}());