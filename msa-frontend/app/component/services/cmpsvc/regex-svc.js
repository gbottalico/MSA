angular.module('msa').service(
    'RegexSvc',
    [
        function () {

            var $svc = this;

            $svc.reCodiceFiscale = /^(?:[B-DF-HJ-NP-TV-Z](?:[AEIOU]{2}|[AEIOU]X)|[AEIOU]{2}X|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}[\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[1256LMRS][\dLMNP-V])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM])(?:[A-MZ][1-9MNP-V][\dLMNP-V]{2}|[A-M][0L](?:[\dLMNP-V][1-9MNP-V]|[1-9MNP-V][0L]))[A-Z]$/;
            $svc.rePartitaIva = /^[0-9]{11}$/;
            $svc.reEmail = /^[\w\-\.]*[\w\.]\@[\w\.]*[\w\-\.]+[\w\-]+[\w]\.+[\w]+[\w $]/;
            $svc.reTelefono = /^[+]{0,1}[0-9 -]{5,20}$/;
            $svc.reTarga = /^(([a-zA-Z]{2}\d{3}[a-zA-Z]{2})|(([a-zA-Z]{2}|[a-zA-Z]{2}|roma)(\d{5}|[a-zA-Z]\d{5}|\d{5}[a-zA-Z]|\d{6})))$/;
            //$svc.reOra = /^([01]?[0-9]|2[0-3]).[0-5][0-9]$/;
            $svc.reOra = /^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;


            $svc.getTargaRegex = function () {
                return $svc.reTarga;
            };
            $svc.getCodiceFiscaleRegex = function () {
                return $svc.reCodiceFiscale;
            };

            $svc.getPartitaIvaRegex = function () {
                return $svc.rePartitaIva;
            };

            $svc.getEmailRegex = function () {
                return $svc.reEmail;
            };

            $svc.getTelefonoRegex = function () {
                return $svc.reTelefono;
            };

            $svc.getOraRegex = function () {
                return $svc.reOra;
            };

            $svc.isCodiceFiscaleValid = function (codiceFiscale) {
                return $svc.reCodiceFiscale.test(codiceFiscale);
            };

            $svc.isPartitaIvaValid = function (partitaIva) {
                return $svc.rePartitaIva.test(partitaIva);
            };

            $svc.isEmailValid = function (email) {
                return $svc.reEmail.test(email);
            };

            $svc.isTelefonoValid = function (telefono) {
                return $svc.reTelefono.test(telefono);
            };
            $svc.isTargaValid = function (targa) {
                return $svc.reTarga.test(targa);
            };
            $svc.isCfOrPi = function (cfOrPi) {
                return ($svc.isCodiceFiscaleValid(cfOrPi) || $svc.isPartitaIvaValid(cfOrPi));
            };

        }
    ]
);