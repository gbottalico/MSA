(function () {
    "use strict";

    app.component('msaDannoRca', {
        templateUrl: '../../app/component/denuncia-sinistro/danno-rca/components/templates/danno-rca-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("dannoRcaContoller", ['$rootScope', '$scope', 'VeicoliSvc',
            function ($rootScope, $scope, VeicoliSvc) {

                var ctrl = this;
                ctrl.dannoRca = {
                    dannoCliente: undefined,
                    danniControparte: undefined,
                    veicoloControparte: undefined
                };
                ctrl.dannoRca.lesioniConducente = undefined;
                ctrl.dannoRca.conducenteIsNotContraente = undefined;

                ctrl.tipoVeicoli = undefined;

                VeicoliSvc.getTipoVeicoli().then(function (response) {
                    ctrl.tipoVeicoli = response.data.result;
                });

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