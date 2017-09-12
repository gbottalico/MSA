(function () {
    "use strict";

    app.component('msaRiepilogo', {
        templateUrl: '../../app/component/denuncia-sinistro/riepilogo/components/templates/riepilogo-tpl.html',
        bindings: {},
        controller: ("riepilogoController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location','toastr', 'SinistriSvc', 'DebugSvc', 'PathSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, toastr, SinistriSvc, DebugSvc, PathSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M27';

                $ctrl.$onInit = function () {
                    parent.mappaCaricata($ctrl.mapId);
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {};
                    },
                    function handleChanges(newValues, oldValues) {

                    }, true
                );


            }])
    });

}());