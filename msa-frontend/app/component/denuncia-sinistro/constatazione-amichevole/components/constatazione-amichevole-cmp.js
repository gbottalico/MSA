(function () {
    "use strict";

    app.component('msaConstatazioneAmichevole', {
        templateUrl: '../../app/component/denuncia-sinistro/constatazione-amichevole/components/templates/constatazione-amichevole-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("constatazioneAmichevoleController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location','toastr', 'SinistriSvc', 'DebugSvc', 'PathSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, toastr, SinistriSvc, DebugSvc, PathSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M13';

                $ctrl.isInputConsumed = false;
                $ctrl.ca = {};
                $ctrl.ca.constatazioneAmichevole = true;
                $ctrl.ca.constatazioneAmichevoleControparte = undefined;

                $ctrl.salvaCa = function () {
                    SinistriSvc.salvaCa($ctrl.numeroSinistroProvvisorio, $ctrl.ca).then(function (response) {
                        DebugSvc.log("salvaCa", response);
                        if (response.data.status === 200) {
                            // parent.aggiornaMappe();
                            toastr.success($translate('global.generic.saveok'));
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $ctrl.bindCa = function () {
                    if (_.isObject($ctrl.sinistroProvvisorio.constatazioneAmichevole)) {
                        $ctrl.ca.constatazioneAmichevole = $ctrl.sinistroProvvisorio.constatazioneAmichevole.caCompilata;
                        $ctrl.ca.constatazioneAmichevoleControparte = $ctrl.sinistroProvvisorio.constatazioneAmichevole.caCompilataControparte;
                    }
                };


                $ctrl.$onInit = function () {
                    parent.mappaCaricata($ctrl.mapId);
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                            constatazioneAmichevole: $ctrl.ca.constatazioneAmichevole
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.sinistroProvvisorio !== undefined && !$ctrl.isInputConsumed) {
                            $ctrl.bindCa();
                            $ctrl.isInputConsumed = true;
                        }

                        if(!newValues.constatazioneAmichevole) {
                            $ctrl.ca.constatazioneAmichevoleControparte = null;
                        }

                    }, true
                );


            }])
    });

}());