(function () {
	'use strict';

    class Monitor {
        constructor($http, WebsocketHelper) {
        	this.$http = $http;
            this.endpoint = '/api/monitor';
            this.WebsocketHelper = WebsocketHelper;
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
                    free: ws.close.bind(ws)
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
	            	
	            	point.location = item.location || {
	            		locationId: null,
	            		section: null
	            	};
	            	
	                return point;
	            });
        	} else {
        		data = [];
        	}
        	        	
        	return data;
        }

        openSocket(type, onMessage) {
            const ws = this.WebsocketHelper.open(`monitor/${type}`);
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
    Monitor.$inject = ['$http', 'WebsocketHelper'];

	angular.module('isms.dashboard').service('Monitor', Monitor);
}());
