(function () {
    "use strict";

    app.component('msaJsonModal', {
        templateUrl: '../../app/component/common/json-modal/components/templates/json-modal-tpl.html',
        bindings: {
            resolve: '<',
            close: '&',
            dismiss: '&'
        },
        controller: ("headerController", ['$rootScope', '$scope', '$debugMode', 'UtilSvc', 'DebugSvc', 'DomainSvc', 'RegexSvc',
            function ($rootScope, $scope, $debugMode, UtilSvc, DebugSvc, DomainSvc, RegexSvc) {
        $ctrl.json={

                "nome" : "ciaoooo",
                "cognome" : "ffasfa",
                "cf" : "abc",

                "codComuneNascita" : "",
                "descComuneNascita" : "",
                "dataNascita" : null,
                "tracking" : {
                    "nazione" : "",
                    "provincia" : "",
                    "comune" : "",
                    "cap" : "",
                    "indirizzo" : "",
                    "telefono" : "",
                    "cellulare" : "",
                    "mail" : ""
                },
                "note" : ""

        };
            }])
    });

}());
 