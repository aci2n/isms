(function () {
    'use strict';

    function Config($stateProvider) {
        $stateProvider.state({
            name: 'dashboard.monitor.window',
            url: '/{windowSize}',
            component: 'monitorWindow',
            resolve: {
                windowSize: ['$stateParams', $stateParams => $stateParams.windowSize]
            }
        });
    }
    Config.$inject = ['$stateProvider'];

    angular.module('isms.dashboard').config(Config);
}());