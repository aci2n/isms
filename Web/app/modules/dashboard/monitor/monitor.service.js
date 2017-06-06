(function () {
	'use strict';

	function MonitorProvider(ServiceBase, $websocket) {
	    this.$get = Monitor;

        class Monitor extends ServiceBase {
            constructor() {
                super('monitor');
                this.wsEndpoint = 'ws://localhost/ws/realtime/';
            }

            forWindowAndType(windowSize, type, onMessage) {
                let resource = null;

                function onNormalizedMessage(message) {
                    return onMessage(this.normalizeMessage(message));
                }

                if (windowSize !== 0) {
                    resource = {
                        handle: super.get(`/${windowSize}/${type}`).then(onNormalizedMessage),
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
                return super.get(`/sizes`);
            }

            types() {
                return super.get(`/types`);
            }
        }
    }
    MonitorProvider.$inject = ['ServiceBase', '$websocket'];

	angular.module('isms.dashboard').provider('Monitor', MonitorProvider);
}());
