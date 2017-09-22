(function () {
    "use strict";

    app.component('msaAnagraficaConducente', {
        templateUrl: '../../app/component/common/anagrafica-conducente/components/templates/anagrafica-conducente-tpl.html',
        bindings: {
            result: "=",
            input: "<",
            name: "<"
        },
        controller: ("anagraficaConducenteController", ['_', '$MSAC', '$scope', 'DebugSvc', 'RegexSvc', 'UtilSvc', 'toastr',
            function (_, $MSAC, $scope, DebugSvc, RegexSvc, UtilSvc, toastr) {

                var $ctrl = this;
                $scope.$MSAC = $MSAC;
                $scope.$regex = RegexSvc;
                $scope.name = $ctrl.name || "anagraficaConducente" + Date.now();

                $ctrl.isInputConsumed = false;
                $ctrl.persistence = {};

                $ctrl.calcolaCf = function () {
                    var luogoNascita = $ctrl.result.nascita.comune ?
                        $ctrl.result.nascita.comune.descrizione :
                        $ctrl.result.nascita.nazione.descrizione;

                    UtilSvc.calcolaCf($ctrl.result.cognome, $ctrl.result.nome, $ctrl.result.sesso, $ctrl.result.nascita.data, luogoNascita).then(function (response) {
                        DebugSvc.log("calcolaCf", response);
                        if (response.data.status === 200) {
                            $ctrl.result.cf = response.data.result;
                        } else {
                            toastr.error($translate('global.generic.cfko'));
                        }
                    });
                };

                $ctrl.isCalcolaCfDisabled = function () {
                    try {
                        return !($ctrl.result.cognome && $ctrl.result.nome && $ctrl.result.sesso && $ctrl.result.nascita.data);
                    } catch (error) {
                        return false;
                    }
                };

                $ctrl.bindAnagrafica = function () {
                    
                    var anagrafica = _.cloneDeep($ctrl.input);

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
                    if(!$ctrl.isInputConsumed && _.isObject($ctrl.input)) {
                        DebugSvc.log('bindAnagraficaConducente', $ctrl.input);
                        $ctrl.bindAnagrafica();
                        $ctrl.isInputConsumed = true;
                    }
                };


            }])
    });

}());