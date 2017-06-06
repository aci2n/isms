(function () {
    'use strict';

    function RouteConfig($stateProvider) {
        $stateProvider.state({
            name: 'dashboard',
            component: 'dashboard',
            url: '/dashboard'
        });
    }
    RouteConfig.$inject = ['$stateProvider'];

    angular.module('isms.dashboard').config(RouteConfig);
}());