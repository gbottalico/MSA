(function () {
    "use strict";

    app.component('msaDannoRca', {
        templateUrl: '../../app/component/denuncia-sinistro/danno-rca/components/templates/danno-rca-tpl.html',
        bindings: {},
        controller: ("dannoRcaContoller", ['$rootScope', '$scope',
            function ($rootScope, $scope) {

                var ctrl = this;

                ctrl.lesioniConducente = undefined;
                ctrl.conducenteIsNotContraente = undefined;

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