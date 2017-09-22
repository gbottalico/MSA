(function () {
    "use strict";

    app.component('msaKasko', {
        templateUrl: '../../app/component/denuncia-sinistro/kasko/components/templates/kasko-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("kaskoController", ['_', '$MSAC', '$rootScope', '$scope', '$debugMode', '$filter', '$location', '$timeout', 'toastr', 'SinistriSvc', 'DebugSvc', 'RegexSvc',
            function (_, $MSAC, $rootScope, $scope, $debugMode, $filter, $location, $timeout, toastr, SinistriSvc, DebugSvc, RegexSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $scope.$MSAC = $MSAC;
                $scope.$regex = RegexSvc;
                $ctrl.mapId = 'M32';

                $ctrl.persistence = {};
                $ctrl.kasko = {};
                $ctrl.isInputConsumed = false;


                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId);
                });


                $ctrl.salvaKasko = function () {
                    SinistriSvc.salvaKasko($ctrl.numeroSinistroProvvisorio, $ctrl.kasko).then(function (response) {
                        DebugSvc.log("salvaKasko", response);
                        if (response.status === 200 && _.isObject(response.data) && response.data.status === 200) {
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.kaskoForm.$setPristine();
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $ctrl.bindKasko = function () {

                    $ctrl.persistence.danniKasko = $ctrl.sinistroProvvisorio.danniKasko;

                    $ctrl.kasko.conducenteIsNotContraente = $ctrl.sinistroProvvisorio.conducenteDiverso;
                    $ctrl.kasko.lesioniConducente = $ctrl.sinistroProvvisorio.lesioniConducente;

                    if ($ctrl.kasko.conducenteIsNotContraente) {
                        $ctrl.kasko.conducente = $ctrl.sinistroProvvisorio.conducente;
                    }

                    $ctrl.kasko.descrizioneDanni = $ctrl.sinistroProvvisorio.descrizioneDanni;
                    $ctrl.kasko.osservazioniCliente = $ctrl.sinistroProvvisorio.osservazioniCliente;
                    $ctrl.kasko.interventoAutorita = $ctrl.sinistroProvvisorio.interventoAutorita;

                    if ($ctrl.kasko.interventoAutorita) {
                        $ctrl.kasko.autoritaIntervenuta = $ctrl.sinistroProvvisorio.codAutorita;
                        $ctrl.kasko.comandoAutorita = $ctrl.sinistroProvvisorio.comandoAutorita;
                        $ctrl.kasko.dataDenuncia = new Date($ctrl.sinistroProvvisorio.dataDenuncia);

                    }
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (_.isObject(newValues.sinistroProvvisorio) && !$ctrl.isInputConsumed) {
                            $ctrl.bindKasko();
                            $ctrl.isInputConsumed = true;
                        }

                    }, true
                );


            }])
    });

}());
