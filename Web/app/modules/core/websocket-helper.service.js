(function () {
	'use strict';

    class WebsocketHelper {
        constructor($websocket, $window) {
        	this.$websocket = $websocket;
            this.endpoint = `ws://${$window.location.host}/api/ws/`;
        }

        open(path) {
            return this.$websocket(this.endpoint + path);
        }
    }
    WebsocketHelper.$inject = ['$websocket', '$window'];

	angular.module('isms.core').service('WebsocketHelper', WebsocketHelper);
}());
