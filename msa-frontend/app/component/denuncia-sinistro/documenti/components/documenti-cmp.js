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

                $ctrl.documenti = [];

                $ctrl.$onInit = function () {
                    parent.mappaCaricata($ctrl.mapId);
                    DocumentiSvc.getLista($ctrl.numeroSinistroProvvisorio).then(function (response) {
                        //TODO ceccare errori
                        response.data.result.forEach(function (element, index) {
                            element = DocumentiSvc.getName(element);
                            $ctrl.documenti.push(element);
                        });
                    });
                };

                $ctrl.aggiungiFile = function () {

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