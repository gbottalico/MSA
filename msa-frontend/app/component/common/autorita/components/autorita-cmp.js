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
        controller: ("autoritaController", ['_', '$scope', 'DebugSvc', 'DomainSvc', function (_, $scope, DebugSvc, DomainSvc) {

            var $ctrl = this;

            $ctrl.persistence = {

            };

            DomainSvc.getAutorita().then(function (response) {
                $ctrl.autorita = response.data.result;
            });

            $scope.$watch(
                function watchScope(scope) {
                    return {
                        result: $ctrl.result,
                    };
                },
                function handleChanges(newValues, oldValues) {

                    if (_.isObject(newValues.result) && newValues.result.dataDenuncia) {
                        $ctrl.persistence.dataDenuncia = newValues.result.dataDenuncia;
                    }

                }, true
            );

        }])
    });

}());