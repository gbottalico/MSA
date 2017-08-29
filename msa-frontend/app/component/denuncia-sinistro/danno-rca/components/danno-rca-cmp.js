(function () {
    "use strict";

    app.component('msaDannoRca', {
        templateUrl: '../../app/component/denuncia-sinistro/danno-rca/components/templates/danno-rca-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("dannoRcaContoller", ['$rootScope', '$scope', '$debugMode', 'VeicoliSvc', 'SinistriSvc',
            function ($rootScope, $scope,$debugMode, VeicoliSvc, SinistriSvc) {

                var $ctrl = this;
                $ctrl.dannoRca = {
                    dannoCliente: undefined,
                    danniControparte: undefined,
                    veicoloControparte: undefined,
                    terzeParti: []
                };
                $ctrl.dannoRca.lesioniConducente = undefined;
                $ctrl.dannoRca.conducenteIsNotContraente = undefined;

                $ctrl.tipoVeicoli = undefined;

                VeicoliSvc.getTipoVeicoli().then(function (response) {
                    $ctrl.tipoVeicoli = response.data.result;
                });

                $ctrl.dannoRca.terzeParti = [
                    {nome: "Corrado", cognome: "Dello Russo", note: "Ciao"},
                    {nome: "Michele", cognome: "Giangiacomo", note: "LOL!"}
                ];

                $ctrl.salvaDannoRca = function () {
                    SinistriSvc.salvaDannoRcaCliente($ctrl.numeroSinistroProvvisorio, $ctrl.dannoRca).then(function (response) {
                        console.log(response);
                    });
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {};
                    },
                    function handleChanges(newValues, oldValues) {

                    }, true
                );


            }])
    });

}());