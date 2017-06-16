(function () {
	'use strict';

    class Monitor {
        constructor($http) {
        	this.$http = $http;
            this.endpoint = '/api/monitor';
            this.wsEndpoint = 'ws://localhost/ws/monitor/';
        }

        forWindowAndType(windowSize, type, onMessage) {
            let resource = null;

            const onNormalizedMessage = message => onMessage(this.normalizeMessage(message));

            if (windowSize !== 0) {
                resource = {
                    handle: this.$http.get(`${this.endpoint}/${windowSize}/${type}`).then(onNormalizedMessage),
                    free: false
                };
            } else {
                const ws = this.openSocket(type, onNormalizedMessage);
                resource = {
                    handle: ws,
                    free: ws.close
                };
            }

            return resource;
        }

        normalizeMessage(message) {
            return message.data.map(item => {
                return { x: item.windowStart, y: item.metric.average };
            });
        }

        openSocket(type, onMessage) {
            const ws = $websocket(this.wsEndpoint + 'type');
            ws.onMessage(onMessage);

            return ws;
        }

        sizes() {
            return this.$http.get(`${this.endpoint}/sizes`);
        }

        types() {
            return this.$http.get(`${this.endpoint}/types`);
        }
    }
    Monitor.$inject = ['$http', '$websocket'];

	angular.module('isms.dashboard').service('Monitor', Monitor);
}());
