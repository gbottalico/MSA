angular.module('msa').service(
    'DocumentiSvc',
    [
        '$http',
        'msaServicesApiUrls',
        'UtilSvc',
        'DebugSvc',
        'ConvertSvc',
        'Upload',
        function ($http, msaServicesApiUrls, UtilSvc, DebugSvc, ConvertSvc, Upload) {

            var $svc = this;

            $svc.upload = function(idSinistroProvvisorio, file) {
                var fd = new FormData();
                fd.append('file', file);
                var url = UtilSvc.stringFormat(msaServicesApiUrls.upload, idSinistroProvvisorio, 1); // TODO 1 è mock
                return $http.post(url, fd, {

                    transformRequest: angular.identity,
                    headers: {
                        'Content-Type': undefined,
                        "user": '{"idUser": 1,"amministratore": true}'
                    }
                });

            };

            $svc.delete = function(idDocumento) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.delete, idDocumento);
                return $http.get(url);
            };

            $svc.getLista = function (idSinistroProvvisorio) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.listadoc, idSinistroProvvisorio);
                return $http.get(url);
            };

            $svc.getName = function (documento) {
                documento.name = documento.path.split("\\");
                documento.name = documento.name[documento.name.length - 1];
                return documento;
            };
        }
    ]
);