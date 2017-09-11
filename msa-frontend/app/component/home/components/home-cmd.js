(function () {
    "use strict";

    app.component('msaHome', {
        templateUrl: '../../app/component/home/components/templates/home-tpl.html',
        bindings: {},
        controller: ("homeController", ['$MSAC', '$scope', '$filter', '$uibModal', '$location', 'toastr', 'DebugSvc', 'SinistriSvc',
            function ($MSAC, $scope, $filter, $uibModal, $location, toastr, DebugSvc, SinistriSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;

                $ctrl.numeroSinistroProvvisorio = undefined;

                $scope.openAnagrafica = function () {
                  $ctrl.openAnagrafica();
                };

                $ctrl.openAnagrafica = function () {
                    var modalInstance = $uibModal.open({
                        animation: true,
                        backdrop: 'static', // Evita che il modal sia chiuso cliccando sullo sfondo.
                        windowClass: 'msaModal',
                        size: 'lg',
                        component: 'msaAnagraficaModal',
                        resolve: {}
                    });

                    modalInstance.result.then(function (contraente) {
                        DebugSvc.log("openAnagrafica", contraente);
                        $ctrl.apriSinistroProvvisorio(contraente);
                    }, function () {
                        DebugSvc.log("openAnagrafica dismiss.");
                    });
                };

                //FIXME rimuovere 37, mockup
                $ctrl.apriSinistroProvvisorio = function (datiContraente, codiceCompagnia) {
                    codiceCompagnia = codiceCompagnia || 37;
                    SinistriSvc.apriSinistroProvvisorio(datiContraente, codiceCompagnia).then(function (response) {
                        DebugSvc.log("apriSinistroProvvisorio", response);
                        if(response.status === 200 && response.data.status === 200) {

                            $ctrl.numeroSinistroProvvisorio = response.data.result.numSinistroProvvisorio;
                            var path = $MSAC.PATHS.DENUNCIA;
                            path = path + "/" + $ctrl.numeroSinistroProvvisorio;
                            $location.path(path);

                            toastr.success($translate('global.generic.saveok'));

                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };


            }])
    });

}());
 