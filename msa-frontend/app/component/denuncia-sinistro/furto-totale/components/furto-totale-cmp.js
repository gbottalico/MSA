(function () {
    "use strict";

    app.component('msaFurtoTotale', {
        templateUrl: '../../app/component/denuncia-sinistro/furto-totale/components/templates/furto-totale-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("furtoTotaleController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$timeout', 'toastr', 'DomainSvc', 'SinistriSvc', 'DebugSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $timeout, toastr, DomainSvc, SinistriSvc, DebugSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M30';


                $ctrl.furtoTotale = {
                    descrizionedanni: undefined,
                    autorita: undefined
                };

                $ctrl.bindFurtoTotale = function () {
                    $ctrl.furtoTotale.descrizioneDanni = $ctrl.sinistroProvvisorio.descrizioneDanni;
                    $ctrl.furtoTotale.interventoAutorita = $ctrl.sinistroProvvisorio.interventoAutorita;
                    $ctrl.furtoTotale.autoritaIntervenuta = $ctrl.sinistroProvvisorio.codAutorita;
                    $ctrl.furtoTotale.comandoAutorita = $ctrl.sinistroProvvisorio.comandoAutorita;
                    $ctrl.furtoTotale.dataDenuncia = $ctrl.sinistroProvvisorio.dataDenuncia;
                };

                $ctrl.salvaFurtoTotale = function () {
                    SinistriSvc.salvaFurtoTotale($ctrl.numeroSinistroProvvisorio, $ctrl.furtoTotale).then(function (response) {
                        DebugSvc.log("salvaFurtoTotale", response);
                        if (response.status === 200 && _.isObject(response.data) && response.data.status === 200) {
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.furtoTotaleForm.$setPristine();
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
                            $ctrl.bindFurtoTotale();
                            $ctrl.isInputConsumed = true;
                        }

                    }, true
                );

            }])
    });

}());