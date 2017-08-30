angular.module('msa')
    .service('DebugSvc', ['$debugMode', function ($debugMode) {

        var $svc = this;

        $svc.log = function (name, obj) {
            if($debugMode) {
                console.log(name + ": ");
                console.log(obj);
            }
        };

        $svc.stringify = function (name, obj) {
            if($debugMode) {
                console.log(name + ": " + JSON.stringify(obj));
            }
        };

    }]
);