(function () {
    'use strict';

    function Config(DashboardItemsProvider, $stateProvider) {
        DashboardItemsProvider.register('monitor', 'Monitor');

        $stateProvider.state({
            name: 'dashboard.monitor',
            url: '/monitor',
            component: 'monitor'
        });
    }
    Config.$inject = ['DashboardItemsProvider', '$stateProvider'];

    angular.module('isms.dashboard').config(Config);
}());