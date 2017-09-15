(function () {
    "use strict";

    app.component('msaIncendioVeicolo', {
        templateUrl: '../../app/component/denuncia-sinistro/incendio-veicolo/components/templates/incendio-veicolo-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("incendioVeicoloController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location', '$timeout', 'toastr', 'SinistriSvc', 'DebugSvc', 'DomainSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, $timeout, toastr, SinistriSvc, DebugSvc, DomainSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M28';

                $ctrl.isInputConsumed = false;
                $ctrl.incendio = {};
                $ctrl.persistence = {};

                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId);
                });

                $ctrl.salvaIncendio = function () {
                    SinistriSvc.salvaIncendio($ctrl.numeroSinistroProvvisorio, $ctrl.incendio).then(function (response) {
                        DebugSvc.log("salvaIncendio", response);
                        if(response.status === 200 && _.isObject(response.data) && response.data.status === 200) {
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.formIncendioVeicolo.$setPristine();
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $ctrl.bindIncendio = function () {
                    $ctrl.incendio.sviluppoFiamme = $ctrl.sinistroProvvisorio.sviluppoFiamme;
                    $ctrl.incendio.responsabilitaTerzi = $ctrl.sinistroProvvisorio.responsabilita;
                    $ctrl.incendio.descrizioneDanni = $ctrl.sinistroProvvisorio.descrizioneDanni;
                    $ctrl.incendio.osservazioniCliente = $ctrl.sinistroProvvisorio.osservazioniCliente;
                    $ctrl.incendio.interventoAutorita = $ctrl.sinistroProvvisorio.interventoAutorita;
                    $ctrl.incendio.autoritaIntervenuta = $ctrl.sinistroProvvisorio.codAutorita;
                    $ctrl.incendio.comandoAutorita = $ctrl.sinistroProvvisorio.comandoAutorita;
                    $ctrl.incendio.dataDenuncia = $ctrl.sinistroProvvisorio.dataDenuncia;
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (_.isObject(newValues.sinistroProvvisorio) && !$ctrl.isInputConsumed) {
                            $ctrl.bindIncendio();
                            $ctrl.isInputConsumed = true;
                        }

                    }, true
                );


            }])
    });

}());