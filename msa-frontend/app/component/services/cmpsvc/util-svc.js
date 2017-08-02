angular.module('msa').service(
    'UtilSvc',
    [function () {

            var $svc = this;

            $svc.isDefined = function (obj) {
                return obj !== undefined;
            };


        }
    ]
);