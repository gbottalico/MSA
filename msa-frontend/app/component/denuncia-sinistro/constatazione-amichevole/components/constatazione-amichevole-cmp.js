(function () {
    "use strict";

    app.component('msaConstatazioneAmichevole', {
        templateUrl: '../../app/component/denuncia-sinistro/constatazione-amichevole/components/templates/constatazione-amichevole-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("constatazioneAmichevoleController", ['$rootScope', '$scope', 'SinistriSvc',
            function ($rootScope, $scope, SinistriSvc) {

                var ctrl = this;
                var parent = $scope.$parent;

                ctrl.constatazioneAmichevole = true;
                ctrl.constatazioneAmichevoleControparte = undefined;

                ctrl.salvaCa = function () {

                    SinistriSvc.salvaCa(ctrl.numeroSinistroProvvisorio).then(function (response) {
                        console.log("SalvaCA");
                        console.log(response.data.result);
                    });
                };

                ctrl.bindCa = function () {
                    //TODO
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if(newValues.sinistroProvvisorio !== undefined) {
                            ctrl.bindCa();
                        }

                    }, true
                );


            }])
    });

}());