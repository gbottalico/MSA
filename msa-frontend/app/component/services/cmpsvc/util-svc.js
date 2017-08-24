angular.module('msa').service(
    'UtilSvc',
    ['$http', 'msaServicesApiUrls', function ($http, msaServicesApiUrls) {

        var $svc = this;

        $svc.calcolaCf = function (nome, cognome, sesso, isoDataNascita, descrizioneComune) {

            var dataObj = {};
            dataObj.nome = nome;
            dataObj.cognome = cognome;
            dataObj.sesso = sesso;
            dataObj.dataNascita = isoDataNascita;
            dataObj.luogoNascita = {};
            dataObj.luogoNascita.descrizioneComune = descrizioneComune;

            return $http.post(msaServicesApiUrls.cf, dataObj);

        };

        $svc.isDefined = function (obj) {
            return obj !== undefined;
        };

        $svc.arrayHasElements = function (array) {
            return (typeof array != "undefined" &&
                array !== null &&
                array.length !== null &&
                array.length > 0);
        };

        $svc.mapToValueArray = function (map) {
            var values = new Array();
            for(var key in map) {
                values.push(map[key]);
            }
            return values;
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

        $svc.dateFormat = function (dateInMillis, format) {

            Date.prototype.customFormat = Date.prototype.customFormat || function (formatString) {
                var YYYY, YY, MMMM, MMM, MM, M, DDDD, DDD, DD, D, hhhh, hhh, hh, h, mm, m, ss, s, ampm, AMPM, dMod, th;
                YY = ((YYYY = this.getFullYear()) + "").slice(-2);
                MM = (M = this.getMonth() + 1) < 10 ? ('0' + M) : M;
                MMM = (MMMM = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"][M - 1]).substring(0, 3);
                DD = (D = this.getDate()) < 10 ? ('0' + D) : D;
                DDD = (DDDD = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"][this.getDay()]).substring(0, 3);
                th = (D >= 10 && D <= 20) ? 'th' : ((dMod = D % 10) == 1) ? 'st' : (dMod == 2) ? 'nd' : (dMod == 3) ? 'rd' : 'th';
                formatString = formatString.replace("#YYYY#", YYYY).replace("#YY#", YY).replace("#MMMM#", MMMM).replace("#MMM#", MMM).replace("#MM#", MM).replace("#M#", M).replace("#DDDD#", DDDD).replace("#DDD#", DDD).replace("#DD#", DD).replace("#D#", D).replace("#th#", th);
                h = (hhh = this.getHours());
                if (h == 0) h = 24;
                if (h > 12) h -= 12;
                hh = h < 10 ? ('0' + h) : h;
                hhhh = hhh < 10 ? ('0' + hhh) : hhh;
                AMPM = (ampm = hhh < 12 ? 'am' : 'pm').toUpperCase();
                mm = (m = this.getMinutes()) < 10 ? ('0' + m) : m;
                ss = (s = this.getSeconds()) < 10 ? ('0' + s) : s;
                return formatString.replace("#hhhh#", hhhh).replace("#hhh#", hhh).replace("#hh#", hh).replace("#h#", h).replace("#mm#", mm).replace("#m#", m).replace("#ss#", ss).replace("#s#", s).replace("#ampm#", ampm).replace("#AMPM#", AMPM);
            };

            var date = new Date(dateInMillis);
            format = format || "#DD#/#MM#/#YYYY#";

            return date.customFormat(format);

        };


    }]);