(function () {
	'use strict';

	class MonitorController {
	    constructor(Monitor, $state) {
	        this.Monitor = Monitor;
	        this.$state = $state;
        }

		setState(windowSize) {
			this.$state.go('dashboard.monitor.window', { windowSize });
		}

	    $onInit() {
	    	this.currentWindowSize = Number.parseInt(this.$state.params.windowSize, 10);
			this.Monitor.sizes().then(response => {
				return this.availableWindowSizes = response.data;
			});
		}
	}
    MonitorController.$inject = ['Monitor', '$state'];

	const component = {
        templateUrl: 'dashboard/monitor/monitor.html',
        controller: MonitorController,
        controllerAs: 'monitorVm'
    };

	angular.module('isms.dashboard').component('monitor', component);
}());
