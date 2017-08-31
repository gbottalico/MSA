(function () {
    "use strict";

    app.component('msaDannoRca', {
        templateUrl: '../../app/component/denuncia-sinistro/danno-rca/components/templates/danno-rca-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("dannoRcaContoller", ['$rootScope', '$scope', '$debugMode', '$filter', 'toastr', 'VeicoliSvc', 'SinistriSvc', 'DebugSvc', 'UtilSvc',
            function ($rootScope, $scope, $debugMode, $filter, toastr, VeicoliSvc, SinistriSvc, DebugSvc, UtilSvc) {

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
                    {nome: "Corrado", cognome: "Dello Russo", note: "Ciao"},
                    {nome: "Michele", cognome: "Giangiacomo", note: "LOL!"}
                ];

                $ctrl.bindDannoRca = function () {

                    $ctrl.dannoRca.lesioniConducente = $ctrl.sinistroProvvisorio.dannoRca.lesioniConducente;

                    if($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente !== undefined && $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente !== null) {

                        $ctrl.persistence.dannoCliente = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente.danni;
                        $ctrl.dannoRca.descrizioneDannoCliente = $ctrl.persistence.dannoCliente.descrizioneDanno;

                    }

                };

                $ctrl.salvaDannoRca = function () {

                    SinistriSvc.salvaDannoRcaCliente($ctrl.numeroSinistroProvvisorio, $ctrl.dannoRca)
                        .then(function (response) {
                            DebugSvc.log("salvaDannoRcaCliente", response);
                            if (response.data.status === 200) {
                                //TODO ritorna la promessa successiva
                            } else {
                                //ERRORE e promise con erroee
                                return UtilSvc.createErrorStatePromise();
                            }
                        });

                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if(newValues.sinistroProvvisorio !== undefined && !$ctrl.isInputConsumed) {
                            $ctrl.bindDannoRca();
                            $ctrl.isInputConsumed = true;
                        }

                    }, true
                );


            }])
    });

}());