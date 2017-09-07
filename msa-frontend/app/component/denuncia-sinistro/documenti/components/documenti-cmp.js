(function () {
    "use strict";

    app.component('msaDocumenti', {
        templateUrl: '../../app/component/denuncia-sinistro/documenti/components/templates/documenti-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("documentiController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location', 'toastr', 'SinistriSvc', 'DebugSvc', 'DocumentiSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, toastr, SinistriSvc, DebugSvc, DocumentiSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M26';

                $ctrl.file = null;

                $ctrl.salvaFile = function () {
                  DocumentiSvc.upload($ctrl.numeroSinistroProvvisorio, $ctrl.file).then(function (response) {
                     DebugSvc.log("salvaFile", response);
                  });
                };

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
    })

}());