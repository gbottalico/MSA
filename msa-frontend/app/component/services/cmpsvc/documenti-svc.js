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

            $svc.getDocumento = function (idDocumento, nome) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.getdocumento, idDocumento);
                return $http({method: 'GET', url: url, responseType: 'arraybuffer'}).success(function (data, status, headers) {
                    headers = headers();

                    var filename = nome || headers['x-filename'];
                    var contentType = headers['content-type'];

                    var linkElement = document.createElement('a');
                    try {
                        var blob = new Blob([data], { type: contentType });
                        var url = window.URL.createObjectURL(blob);
                        linkElement.setAttribute('href', url);
                        linkElement.setAttribute("download", filename);
                        var clickEvent = new MouseEvent("click", {
                            "view": window,
                            "bubbles": true,
                            "cancelable": false
                        });
                        linkElement.dispatchEvent(clickEvent);
                    } catch (ex) {
                        DebugSvc.log("Exception", ex);
                    }
                }).error(function (data) {
                    DebugSvc.log("Impossibile scaricare il file.", data);
                });
            }
        }
    ]
);