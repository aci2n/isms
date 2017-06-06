(function () {
	'use strict';

	function MonitorProvider(ServiceBase) {
	    this.$get = Monitor;

        class Monitor extends ServiceBase {
            constructor() {
                super('monitor');
            }

            forWindowSize(windowSize) {
                return super.get(`/${windowSize}`);
            }

            sizes() {
                return super.get(`/sizes`);
            }
        }
    }
    MonitorProvider.$inject = ['ServiceBase'];

	angular.module('isms.dashboard').provider('Monitor', MonitorProvider);
}());
