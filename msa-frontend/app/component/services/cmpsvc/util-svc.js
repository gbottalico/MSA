angular.module('msa').service(
    'UtilSvc',
    [function () {

            var $svc = this;

            $svc.isDefined = function (obj) {
                return obj !== undefined;
            };

            $svc.arrayHasElements = function (array) {
                return (typeof array != "undefined" &&
                    array != null &&
                    array.length != null &&
                    array.length > 0);
            };

        }
    ]
);