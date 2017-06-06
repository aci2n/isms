(function () {
    'use strict';

    function Config($stateProvider) {
        $stateProvider.state({
            name: 'dashboard.monitor.window',
            url: '/window',
            component: 'monitorWindow'
        });
    }
    Config.$inject = ['$stateProvider'];

    angular.module('isms.dashboard').config(Config);
}());