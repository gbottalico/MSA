(function () {
    "use strict";

    app.component('msaConstatazioneAmichevole', {
        templateUrl: '../../app/component/denuncia-sinistro/constatazione-amichevole/components/templates/constatazione-amichevole-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("constatazioneAmichevoleController", ['$rootScope', '$scope', '$debugMode', 'SinistriSvc',
            function ($rootScope, $scope, $debugMode, SinistriSvc) {

                var $ctrl = this;
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;

                $ctrl.ca = {};
                $ctrl.ca.constatazioneAmichevole = true;
                $ctrl.ca.constatazioneAmichevoleControparte = undefined;

                $ctrl.salvaCa = function () {
                    SinistriSvc.salvaCa($ctrl.numeroSinistroProvvisorio, $ctrl.ca).then(function (response) {
                        parent.aggiornaMappe();
                    });
                };

                $ctrl.bindCa = function () {
                    if ($ctrl.sinistroProvvisorio.constatazioneAmichevole !== undefined && $ctrl.sinistroProvvisorio.constatazioneAmichevole !== null) {
                        $ctrl.ca.constatazioneAmichevole = $ctrl.sinistroProvvisorio.constatazioneAmichevole.caCompilata;
                        $ctrl.ca.constatazioneAmichevoleControparte = $ctrl.sinistroProvvisorio.constatazioneAmichevole.caCompilataControparte;
                    }
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.sinistroProvvisorio !== undefined) {
                            $ctrl.bindCa();
                        }

                    }, true
                );


            }])
    });

}());