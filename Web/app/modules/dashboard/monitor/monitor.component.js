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
			this.Monitor.sizes().then(response => {
				return this.availableWindowSizes = response.data;
			});
		}
	}
    MonitorController.$inject = ['Monitor', '$state'];

	const component = {
        templateUrl: 'monitor/monitor.html',
        controller: MonitorController,
        controllerAs: 'monitorVm'
    };

	angular.module('isms.dashboard').component('monitor', component);
}());
