angular.module('msa').service(
    'UtilSvc',
    [function () {

        var $svc = this;

        $svc.isDefined = function (obj) {
            return obj !== undefined;
        };

        $svc.arrayHasElements = function (array) {
            return (typeof array != "undefined" &&
                array !== null &&
                array.length !== null &&
                array.length > 0);
        };

        /**
         * Porting dello string format di Java.
         * Registra la funzione se non è presente, una volta presente la utilizza.
         *
         * @param string
         * @param args
         * @returns {*}
         */

        $svc.stringFormat = function (string, args) {

            String.prototype.formatUnicorn = String.prototype.formatUnicorn ||
                function () {
                    "use strict";
                    var str = this.toString();
                    if (arguments.length) {
                        var t = typeof arguments[0];
                        var key;
                        var args = ("string" === t || "number" === t) ?
                            Array.prototype.slice.call(arguments)
                            : arguments[0];

                        for (key in args) {
                            str = str.replace(new RegExp("\\{" + key + "\\}", "gi"), args[key]);
                        }
                    }

                    return str;
                };

            return string.formatUnicorn(args);

        };

        $svc.hasId = function (obj) {
            return (
                obj !== undefined &&
                obj !== null &&
                obj.id !== undefined &&
                obj.id !== null);
        };
    }
    ]
);