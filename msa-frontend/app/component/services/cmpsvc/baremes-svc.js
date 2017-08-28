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

            //TODO spostare in sinistriSvc
            $svc.saveBaremesAndGetResponsabilita = function (numeroSinistroProvvisorio, baremeCliente, baremeControparte, noteCliente, noteControparte) {

                var data = {};
                data.baremesCliente = {};
                data.baremesCliente.id = baremeCliente;


                data.baremesControparte = {};
                if (baremeControparte !== null) {
                    data.baremesControparte.id = baremeControparte;
                } else {
                    data.baremesControparte = null;
                }

                data.noteCliente = noteCliente;
                data.noteControparte = noteControparte;

                var url = UtilSvc.stringFormat(msaServicesApiUrls.cai, numeroSinistroProvvisorio);

                return $http.post(url, data);

            };

        }
    ]
);