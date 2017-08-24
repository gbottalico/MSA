(function () {
    "use strict";

    app.component('msaEventoRca', {
        templateUrl: '../../app/component/denuncia-sinistro/evento-rca/components/templates/evento-rca-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "="
        },
        controller: ("eventoRcaController", ['$rootScope', '$scope', 'SinistriSvc',
            function ($rootScope, $scope, SinistriSvc) {

                var ctrl = this;
                var parent = $scope.$parent;

                ctrl.eventoRca = {};

                ctrl.eventoRca.collisione = true;
                ctrl.eventoRca.interventoAutorita = true;
                ctrl.eventoRca.nveicoli = 2;

                ctrl.eventoRca.date = {};

                ctrl.salvaEventoRca = function () {
                    SinistriSvc.salvaEventoRca(ctrl.numeroSinistroProvvisorio, ctrl.eventoRca).then(function (response) {
                        console.log(response);
                    })
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.sinistroProvvisorio !== undefined) {

                            ctrl.eventoRca.collisione = newValues.sinistroProvvisorio.eventoRca.collisione;
                            ctrl.eventoRca.interventoAutorita = newValues.sinistroProvvisorio.eventoRca.interventoAutorita;
                            ctrl.eventoRca.nveicoli = newValues.sinistroProvvisorio.eventoRca.numVeicoli;

                        }

                    }, true
                );


            }])
    });

}());