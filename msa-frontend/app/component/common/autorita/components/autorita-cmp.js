(function () {
    "use strict";

    /**
     * Selettore di data.
     * Permette la selezione di date solamente nel passato, ma il comportamento è evitabile omettendo
     * il campo maxDate.
     *
     */

    app.component('msaAutorita', {
        templateUrl: '../../app/component/common/autorita/components/templates/autorita-tpl.html',
        bindings: {
            result: "=",
            name: "<"
        },
        controller: ("autoritaController", ['_', '$MSAC', '$scope', 'DebugSvc', 'DomainSvc', function (_, $MSAC, $scope, DebugSvc, DomainSvc) {

            var $ctrl = this;
            $scope.$MSAC = $MSAC;

            DomainSvc.getAutorita().then(function (response) {
                $ctrl.autorita = response.data.result;
            });

            $scope.$watch(
                function watchScope(scope) {
                    return {};
                },
                function handleChanges(newValues, oldValues) {},
                true
            );

        }])
    });

}());