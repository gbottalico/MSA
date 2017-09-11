angular.module('msa').service(
    'RegexSvc',
    [
        function () {

            var $svc = this;

            var reCodiceFiscale = /^(?:[B-DF-HJ-NP-TV-Z](?:[AEIOU]{2}|[AEIOU]X)|[AEIOU]{2}X|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}[\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[1256LMRS][\dLMNP-V])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM])(?:[A-MZ][1-9MNP-V][\dLMNP-V]{2}|[A-M][0L](?:[\dLMNP-V][1-9MNP-V]|[1-9MNP-V][0L]))[A-Z]$/;
            var rePartitaIva = /^[0-9]{11}$/;
            var reEmail = /^[\w\-\.]*[\w\.]\@[\w\.]*[\w\-\.]+[\w\-]+[\w]\.+[\w]+[\w $]/;
            var reTelefono = /^[+]{0,1}[0-9 -]{5,20}$/;
            var reTarga = /^(([a-zA-Z]{2}\d{3}[a-zA-Z]{2})|(([a-zA-Z]{2}|[a-zA-Z]{2}|roma)(\d{5}|[a-zA-Z]\d{5}|\d{5}[a-zA-Z]|\d{6})))$/;

            $svc.getTargaRegex = function () {
                return reTarga;
            };
            $svc.getCodiceFiscaleRegex = function () {
                return reCodiceFiscale;
            };

            $svc.getPartitaIvaRegex = function () {
                return rePartitaIva;
            };

            $svc.getEmailRegex = function () {
                return reEmail;
            };

            $svc.getTelefonoRegex = function () {
                return reTelefono;
            };

            $svc.isCodiceFiscaleValid = function (codiceFiscale) {
                return reCodiceFiscale.test(codiceFiscale);
            };

            $svc.isPartitaIvaValid = function (partitaIva) {
                return rePartitaIva.test(partitaIva);
            };

            $svc.isEmailValid = function (email) {
                return reEmail.test(email);
            };

            $svc.isTelefonoValid = function (telefono) {
                return reTelefono.test(telefono);
            };
            $svc.isTargaValid = function (targa) {
                return reTarga.test(targa);
            };
            $svc.isCfOrPi = function (cfOrPi) {
                return ($svc.isCodiceFiscaleValid(cfOrPi) || $svc.isPartitaIvaValid(cfOrPi));
            };

        }
    ]
);