(function () {
    "use strict";

    app.component('msaFurtoParziale', {
        templateUrl: '../../app/component/denuncia-sinistro/furto-parziale/components/templates/furto-parziale-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("furtoParzialeController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$timeout', 'toastr', 'DomainSvc', 'SinistriSvc', 'DebugSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $timeout, toastr, DomainSvc, SinistriSvc, DebugSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M31';


                $ctrl.furtoParziale = {};

                $ctrl.bindFurtoParziale = function () {
                    $ctrl.furtoParziale.descrizioneDanni = $ctrl.sinistroProvvisorio.descrizioneDanni;
                    $ctrl.furtoParziale.osservazioniCliente = $ctrl.sinistroProvvisorio.osservazioniCliente;
                    $ctrl.furtoParziale.interventoAutorita = $ctrl.sinistroProvvisorio.interventoAutorita;
                    $ctrl.furtoParziale.autoritaIntervenuta = $ctrl.sinistroProvvisorio.codAutorita;
                    $ctrl.furtoParziale.comandoAutorita = $ctrl.sinistroProvvisorio.comandoAutorita;
                    $ctrl.furtoParziale.dataDenuncia = $ctrl.sinistroProvvisorio.dataDenuncia;
                };

                $ctrl.salvaFurtoParziale = function () {
                    SinistriSvc.salvaFurto($ctrl.numeroSinistroProvvisorio, $ctrl.furtoParziale).then(function (response) {
                        DebugSvc.log("salvaFurto", response);
                        if (response.status === 200 && _.isObject(response.data) && response.data.status === 200) {
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.furtoParzialeForm.$setPristine();
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (_.isObject(newValues.sinistroProvvisorio) && !$ctrl.isInputConsumed) {
                            $ctrl.bindFurtoParziale();
                            $ctrl.isInputConsumed = true;
                        }

                    }, true
                );

            }])
    });

}());