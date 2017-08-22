angular.module('msa').service(
    'BaremesSvc',
    [
        '$http',
        'msaServicesApiUrls',
        'UtilSvc',
        function ($http, msaServicesApiUrls, UtilSvc) {

            var $svc = this;

            $svc.getBaremes = function () {
                return $http.get(msaServicesApiUrls.baremes);
            };

            $svc.saveBaremesAndGetResponsabilita = function (numeroSinistroProvvisorio, baremeCliente, baremeControparte, noteCliente, noteControparte) {

                var data = {};
                data.baremesCliente = {};
                data.baremesCliente.id = baremeCliente;

                data.baremesControparte = {};
                data.baremesControparte.id = baremeControparte;

                data.noteCliente = noteCliente;
                data.noteControparte = noteControparte;

                var url = UtilSvc.stringFormat(msaServicesApiUrls.cai, numeroSinistroProvvisorio);

                return $http.post(url, data);

            };

        }
    ]
);