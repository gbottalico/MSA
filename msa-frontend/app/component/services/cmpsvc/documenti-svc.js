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

            }

        }
    ]
);