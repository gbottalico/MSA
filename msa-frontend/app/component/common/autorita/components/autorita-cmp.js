(function () {
    "use strict";

    app.component('msaAutorita', {
        templateUrl: '../../app/component/common/autorita/components/templates/autorita-tpl.html',
        bindings: {
            result: "=",
            minDate: "<",
            name: "<"
        },
        controller: ("autoritaController", ['_', '$MSAC', '$scope', 'DebugSvc', 'DomainSvc', function (_, $MSAC, $scope, DebugSvc, DomainSvc) {

            var $ctrl = this;
            $scope.$MSAC = $MSAC;
            $scope.name = $ctrl.name || "autorita" + Date.now();
            $ctrl.$form = $scope[$scope.name];

            DomainSvc.getAutorita().then(function (response) {
                $ctrl.autorita = response.data.result;
            });

            $ctrl.dateOptions = _.cloneDeep($MSAC.DEFAULT_DATE_OPTIONS.options);
            if($ctrl.minDate) {
                $ctrl.dateOptions.minDate = new Date($ctrl.minDate);
            }

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