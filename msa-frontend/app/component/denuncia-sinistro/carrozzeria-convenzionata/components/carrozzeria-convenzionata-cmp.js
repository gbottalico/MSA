(function () {
    "use strict";

    app.component('msaCarrozzeriaConvenzionata', {
        templateUrl: '../../app/component/denuncia-sinistro/carrozzeria-convenzionata/components/templates/carrozzeria-convenzionata-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("carrozzeriaConvenzionataController", ['$rootScope', '$scope', '$debugMode',
            function ($rootScope, $scope, $debugMode) {

                var $ctrl = this;
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;


                /*

                <ui-gmap-google-map center="map.center" zoom="map.zoom"></ui-gmap-google-map>
                $scope.map = { center: { latitude: 45, longitude: -73 }, zoom: 8 };

                */


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