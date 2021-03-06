(function () {
    "use strict";

    app.component('msaCristalli', {
        templateUrl: '../../app/component/denuncia-sinistro/cristalli/components/templates/cristalli-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("cristalliController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location', '$timeout', 'toastr', 'SinistriSvc', 'DebugSvc', 'DomainSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, $timeout, toastr, SinistriSvc, DebugSvc, DomainSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M33';

                $ctrl.isInputConsumed = false;
                $ctrl.causeRotturaCristalli = undefined;

                $ctrl.cristalli = {};

                DomainSvc.getCauseRotturaCristalli().then(function (response) {
                   $ctrl.causeRotturaCristalli = response.data.result;
                });

                $ctrl.salvaCristalli = function () {
                    SinistriSvc.salvaCristalli($ctrl.numeroSinistroProvvisorio, $ctrl.cristalli).then(function (response) {
                        DebugSvc.log("salvaCristalli", response);
                        if(response.status === 200 && _.isObject(response.data) && response.data.status === 200) {
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.cristalliForm.$setPristine();
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $ctrl.bindCristalli = function () {
                    $ctrl.cristalli.desCristalloRotto = $ctrl.sinistroProvvisorio.desCristalloRotto;
                    $ctrl.cristalli.codRotturaCristalli = _.toString($ctrl.sinistroProvvisorio.codRotturaCristalli);
                    $ctrl.cristalli.flagRiparazione = $ctrl.sinistroProvvisorio.flagRiparazione;
                    $ctrl.cristalli.flagFattura = $ctrl.sinistroProvvisorio.flagFattura;
                    $ctrl.cristalli.interventoAutorita = $ctrl.sinistroProvvisorio.interventoAutorita;
                    $ctrl.cristalli.autoritaIntervenuta = $ctrl.sinistroProvvisorio.codAutorita;
                    $ctrl.cristalli.comandoAutorita = $ctrl.sinistroProvvisorio.comandoAutorita;
                    $ctrl.cristalli.dataDenuncia = $ctrl.sinistroProvvisorio.dataDenuncia;
                };

                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId);
                });

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (_.isObject(newValues.sinistroProvvisorio) && !$ctrl.isInputConsumed) {
                            $ctrl.bindCristalli();
                            $ctrl.isInputConsumed = true;
                        }

                    }, true
                );


            }])
    });

}());
