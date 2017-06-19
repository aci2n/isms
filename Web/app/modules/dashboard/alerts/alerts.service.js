(function () {
	'use strict';

    class Alerts {
        constructor($http, WebsocketHelper) {
        	this.$http = $http;
            this.endpoint = '/api/alerts';
            this.WebsocketHelper = WebsocketHelper;
        }
    }
    Alerts.$inject = ['$http', 'WebsocketHelper'];

	angular.module('isms.dashboard').service('Alerts', Alerts);
}());
