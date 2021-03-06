(function () {
    "use strict";

    app.component('msaDocumenti', {
        templateUrl: '../../app/component/denuncia-sinistro/documenti/components/templates/documenti-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("documentiController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location', '$uibModal', '$timeout', 'toastr', 'SinistriSvc', 'DebugSvc', 'DocumentiSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, $uibModal, $timeout, toastr, SinistriSvc, DebugSvc, DocumentiSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M26';

                $ctrl.documenti = [];

                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId);
                    DocumentiSvc.getLista($ctrl.numeroSinistroProvvisorio).then(function (response) {
                        //TODO ceccare errori
                        response.data.result.forEach(function (element, index) {
                            element = DocumentiSvc.getName(element);
                            $ctrl.documenti.push(element);
                        });
                    });
                });

                $ctrl.aggiungiFile = function () {
                    var modalInstance = $uibModal.open({
                        animation: true,
                        backdrop: 'static', // Evita che il modal sia chiuso cliccando sullo sfondo.
                        windowClass: 'msaModal',
                        size: 'lg',
                        component: 'msaUploadModal',
                        resolve: {
                            numeroSinistroProvvisorio: function () {
                                return $ctrl.numeroSinistroProvvisorio;
                            }
                        }
                    });

                    modalInstance.result.then(function (documento) {
                        DebugSvc.log("aggiungiFile", documento);
                        documento = DocumentiSvc.getName(documento);
                        $ctrl.documenti.push(documento);
                        $scope.documentiForm.$setDirty();
                    }, function () {
                        DebugSvc.log("aggiungiFile dismiss.");
                    });
                };

                $ctrl.cancellaFile = function (index) {
                    var idDocumento = $ctrl.documenti[index].idDocumento;
                    DocumentiSvc.delete(idDocumento).then(function (response) {
                        DebugSvc.log("cancellaFile", response);
                        if (response.data.status === 200) {
                            $ctrl.documenti.splice(index, 1);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.documentiForm.$setDirty();

                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $ctrl.salvaDocumenti = function () {
                    //TODO
                    parent.aggiornaMappe($ctrl.mapId);
                    $scope.documentiForm.$setPristine();
                };

                $ctrl.getDocumento = function (index) {
                    var idDocumento = $ctrl.documenti[index].idDocumento;
                    var nome = $ctrl.documenti[index].name;
                    DocumentiSvc.getDocumento(idDocumento, nome);
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {};
                    },
                    function handleChanges(newValues, oldValues) {
                    }, true
                );


            }])
    });

}());