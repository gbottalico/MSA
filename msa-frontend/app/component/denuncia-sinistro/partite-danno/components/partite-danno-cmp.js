(function () {
    "use strict";

    app.component('msaPartiteDanno', {
        templateUrl: '../../app/component/denuncia-sinistro/partite-danno/components/templates/partite-danno-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("partiteDannoController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location', '$timeout', 'toastr', 'SinistriSvc', 'DebugSvc', 'PathSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, $timeout, toastr, SinistriSvc, DebugSvc, PathSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M22';

                $ctrl.isInputConsumed = false;
                $ctrl.pd = [];

                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId);
                    SinistriSvc.getPartiteDanno($ctrl.numeroSinistroProvvisorio).then(function (response) {
                        if (response.status === 200 && _.isObject(response.data) && response.data.status === 200) {
                            $ctrl.pd = response.data.result;
                        }
                    });

                });

                $ctrl.salvaPd = function () {
                    parent.aggiornaMappe($ctrl.mapId);
                    toastr.success($translate('global.generic.saveok'));
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (_.isObject(newValues.sinistroProvvisorio) && !$ctrl.isInputConsumed) {
                            $ctrl.isInputConsumed = true;
                        }

                    }, true
                );


            }])
    });

}());
