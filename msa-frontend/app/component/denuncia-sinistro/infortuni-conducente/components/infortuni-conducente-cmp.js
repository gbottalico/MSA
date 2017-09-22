(function () {
    "use strict";

    app.component('msaInfortuniConducente', {
        templateUrl: '../../app/component/denuncia-sinistro/infortuni-conducente/components/templates/infortuni-conducente-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("msaInfortuniConducente", ['_', '$MSAC', '$rootScope', '$scope', '$debugMode', '$filter', '$location', '$timeout', 'toastr', 'SinistriSvc', 'DebugSvc', 'RegexSvc',
            function (_, $MSAC, $rootScope, $scope, $debugMode, $filter, $location, $timeout, toastr, SinistriSvc, DebugSvc, RegexSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $scope.$MSAC = $MSAC;
                $scope.$regex = RegexSvc;
                $ctrl.mapId = 'M34';

                $ctrl.persistence = {};
                $ctrl.infortuni = {};
                $ctrl.isInputConsumed = false;


                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId);
                });


                $ctrl.salvaInfortuni = function () {
                    SinistriSvc.salvaInfortuni($ctrl.numeroSinistroProvvisorio, $ctrl.infortuni).then(function (response) {
                        DebugSvc.log("salvaInfortuni", response);
                        if (response.status === 200 && _.isObject(response.data) && response.data.status === 200) {
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.infortuniForm.$setPristine();
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $ctrl.bindInfortuni = function () {

                    $ctrl.infortuni.conducenteIsNotContraente = $ctrl.sinistroProvvisorio.conducenteDiverso;

                    if ($ctrl.infortuni.conducenteIsNotContraente) {
                        $ctrl.infortuni.conducente = $ctrl.sinistroProvvisorio.conducente;
                    }

                    $ctrl.infortuni.descrizioneDanni = $ctrl.sinistroProvvisorio.descrizioneDanni;
                    $ctrl.infortuni.osservazioniCliente = $ctrl.sinistroProvvisorio.osservazioniCliente;
                    $ctrl.infortuni.interventoAutorita = $ctrl.sinistroProvvisorio.interventoAutorita;

                    if ($ctrl.infortuni.interventoAutorita) {
                        $ctrl.infortuni.autoritaIntervenuta = $ctrl.sinistroProvvisorio.codAutorita;
                        $ctrl.infortuni.comandoAutorita = $ctrl.sinistroProvvisorio.comandoAutorita;
                        $ctrl.infortuni.dataDenuncia = new Date($ctrl.sinistroProvvisorio.dataDenuncia);

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
                            $ctrl.bindInfortuni();
                            $ctrl.isInputConsumed = true;
                        }

                    }, true
                );


            }])
    });

}());
