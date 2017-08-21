(function () {
    "use strict";

    app.component('msaConstatazioneAmichevole', {
        templateUrl: '../../app/component/denuncia-sinistro/constatazione-amichevole/components/templates/constatazione-amichevole-tpl.html',
        bindings: {},
        controller: ("constatazioneAmichevoleController", ['$rootScope', '$scope',
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