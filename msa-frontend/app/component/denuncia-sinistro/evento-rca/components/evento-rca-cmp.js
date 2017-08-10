(function () {
    "use strict";

    app.component('msaEventoRca', {
        templateUrl: '../../app/component/denuncia-sinistro/evento-rca/components/templates/evento-rca-tpl.html',
        bindings: {},
        controller: ("eventoRcaController", ['$rootScope', '$scope',
            function ($rootScope, $scope) {

                var ctrl = this;
                ctrl.date = {};

                $scope.$watch(
                    function watchScope(scope) {
                        return {

                        };
                    },
                    function handleChanges(newValues, oldValues) {

                    }, true
                );


            }])
    });

}());