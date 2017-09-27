(function () {
    "use strict";

    app.component('msaBannerDenuncia', {
        templateUrl: '../../app/component/common/banner-denuncia/components/templates/banner-denuncia-tpl.html',
        bindings: {},
        controller: ("bannerDenunciaController", ['$scope', '$routeParams',
            function ($scope, $routeParams) {

                var $ctrl = this;
                $scope.numeroSinistroProvvisorio = $routeParams.idSinistroProvvisorio;

            }])
    });

}());
