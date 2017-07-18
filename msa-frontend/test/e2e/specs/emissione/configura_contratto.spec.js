describe('EMISSIONE: Test nfcCcContainer', function () {

    var componentController, scope;
    var tBindigs = {ccContainerData: {xxx: 'cia'}};

    beforeEach(module('nfc'));

    beforeEach(inject(function ($componentController, $rootScope) {
        scope = $rootScope.$new();
        componentController = $componentController('nfcCcContainer', {$scope: scope}, tBindigs);
    }));

    it('esiste xxx', function () {
        expect(componentController.ccContainerData).toBeDefined();
    });

    it('xxx è uguale a \'cia\'', function () {
        expect(componentController.ccContainerData.xxx).toBe('cia');
    });

});