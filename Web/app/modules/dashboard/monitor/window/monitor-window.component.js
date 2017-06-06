(function () {
	'use strict';

	class MonitorWindowController {
	    constructor(Monitor, $state) {
	        this.Monitor = Monitor;
	        this.$state = $state;
        }

        setState(type) {
	        this.$state.go('dashboard.monitor.window.type', { windowSize: this.windowSize, type });
        }

        loadTypes() {
	        this.Monitor.types().then(response => {
	            this.types = response.data;
            });
        }

		$onInit() {
			this.loadTypes();
		}
	}
	MonitorWindowController.$inject = ['Monitor', '$state'];

	const component = {
        templateUrl: 'dashboard/monitor/window/monitor-window.html',
        controller: MonitorWindowController,
        controllerAs: 'monitorWindowVm',
        bindings: {
            windowSize: '<'
        }
    };
	
	angular.module('isms.dashboard').component('monitorWindow', component);
}());
