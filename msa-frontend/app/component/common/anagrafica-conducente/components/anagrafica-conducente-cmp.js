(function () {
    "use strict";

    app.component('msaAnagraficaConducente', {
        templateUrl: '../../app/component/common/anagrafica-conducente/components/templates/anagrafica-conducente-tpl.html',
        bindings: {
            result: "=",
            input: "<",
            name: "<"
        },
        controller: ("anagraficaConducenteController", ['_', '$MSAC', '$scope', 'DebugSvc', 'RegexSvc', 'toastr',
            function (_, $MSAC, $scope, DebugSvc, RegexSvc, toastr) {

                var $ctrl = this;
                $scope.$MSAC = $MSAC;
                $scope.$regex = RegexSvc;

                $ctrl.persistence = {};

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

                $ctrl.bind = function () {
                    
                    var anagrafica = $ctrl.input;

                    $ctrl.result.cognome = anagrafica.cognome;
                    $ctrl.result.nome = anagrafica.nome;
                    $ctrl.result.sesso = anagrafica.sesso;
                    $ctrl.result.cf = anagrafica.cf;
                    $ctrl.result.nascita = {};

                    if (anagrafica.dataNascita) {
                        $ctrl.result.nascita.data = new Date(anagrafica.dataNascita);
                    }

                    $ctrl.persistence.luogoNascita = anagrafica.luogoNascita;

                    if (_.isObject(anagrafica.tracking)) {
                        $ctrl.result.telefono = anagrafica.tracking.telefono;
                        $ctrl.result.mail = anagrafica.tracking.mail;
                        $ctrl.persistence.residenza = anagrafica.tracking.residenza;

                        $ctrl.result.residenza = {};
                        $ctrl.result.residenza.indirizzo = anagrafica.tracking.indirizzo;
                    }  
                };
                
                $ctrl.$doCheck = function () {
                    DebugSvc.log('doCheck:' + $ctrl.input);
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