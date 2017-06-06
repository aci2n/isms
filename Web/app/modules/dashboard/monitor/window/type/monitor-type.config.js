(function () {
    'use strict';

    function Config($stateProvider) {
        $stateProvider.state({
            name: 'dashboard.monitor.window.type',
            url: '/{type}',
            component: 'monitorType',
            resolve: {
                windowSize: ['$stateParams', $stateParams => $stateParams.windowSize],
                type: ['$stateParams', $stateParams => $stateParams.type]
            }
        });
    }
    Config.$inject = ['$stateProvider'];

    angular.module('isms.dashboard').config(Config);
}());