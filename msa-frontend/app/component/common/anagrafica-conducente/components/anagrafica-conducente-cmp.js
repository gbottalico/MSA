(function () {
    "use strict";

    app.component('msaAnagraficaConducente', {
        templateUrl: '../../app/component/common/anagrafica-conducente/components/templates/anagrafica-conducente-tpl.html',
        bindings: {
            result: "=",
            name: "<"
        },
        controller: ("anagraficaConducenteController", ['_', '$MSAC', '$scope', 'DebugSvc', 'RegexSvc', 'toastr',
            function (_, $MSAC, $scope, DebugSvc, RegexSvc, toastr) {

            var $ctrl = this;
            $scope.$MSAC = $MSAC;
            $scope.$regex = RegexSvc;

            $ctrl.calcolaCf = function () {
                var luogoNascita = $ctrl.nascita.comune ?
                    $ctrl.nascita.comune.descrizione :
                    $ctrl.nascita.nazione.descrizione;

                UtilSvc.calcolaCf($ctrl.cognome, $ctrl.nome, $ctrl.sesso, $ctrl.nascita.data, luogoNascita).then(function (response) {
                    DebugSvc.log("calcolaCf", response);
                    if (response.data.status === 200) {
                        $ctrl.cf = response.data.result;
                    } else {
                        toastr.error($translate('global.generic.cfko'));
                    }
                });
            };

            $ctrl.isCalcolaCfDisabled = function () {
                try {
                    return !($ctrl.cognome && $ctrl.nome && $ctrl.sesso && $ctrl.nascita.data);
                } catch (error) {
                    return false;
                }
            };

            $scope.$watch(
                function watchScope(scope) {
                    return {};
                },
                function handleChanges(newValues, oldValues) {
                },
                true
            );

        }])
    });

}());