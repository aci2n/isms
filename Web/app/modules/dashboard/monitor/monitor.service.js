(function () {
	'use strict';

    class Monitor {
        constructor($http, $websocket, $window) {
        	this.$http = $http;
        	this.$websocket = $websocket;
            this.endpoint = '/api/monitor';
            this.wsEndpoint = `ws://${$window.location.host}/api/ws/monitor`;
        }

        forWindowAndType(windowSize, type, onMessage) {
        	windowSize = Number.parseInt(windowSize, 10);
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
        	let data = message.data;
        	
        	if (angular.isString(data)) {
        		data = angular.fromJson(message.data);
        	} 
        	
        	if (angular.isArray(data)) {
	            data = data.map(item => {
	            	const point = {};
	            	
	            	if (item.metric) {
	            		point.x = item.windowStart;
	            		point.y = item.metric.average;
	            	} else if (item.data) {
	            		point.x = item.time;
	            		point.y = item.data;
	            	}
	            	
	                return point;
	            });
        	} else {
        		data = [];
        	}
        	
        	return data;
        }

        openSocket(type, onMessage) {
            const ws = this.$websocket(`${this.wsEndpoint}`);
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
    Monitor.$inject = ['$http', '$websocket', '$window'];

	angular.module('isms.dashboard').service('Monitor', Monitor);
}());
