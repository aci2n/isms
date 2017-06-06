(function () {
	'use strict';

	class MonitorTypeController {
	    constructor(Monitor, capitalizeFilter) {
	        this.Monitor = Monitor;
	        this.capitalizeFilter = capitalizeFilter;
        }

		loadDataset(windowSize, type) {
			const resource = this.Monitor.forWindowAndType(windowSize, type, points => {
				return (this.chart.data = this.chart.data.concat(points));
			});

			if (resource.shouldClose === true) {
			    this.liberator = resource.handle.close;
            }
		}

		defaultChart(label) {
            return {
                data: [],
                options: {
                    label: label,
                    scales: {
                        xAxes: [{
                            type: 'linear',
                            position: 'bottom'
                        }]
                    }
                }
            };
        }

		$onInit() {
	        this.chart = this.defaultChart(this.capitalizeFilter(this.type));
			this.loadDataset(this.windowSize, this.type);
		}

		$onDestroy() {
	        this.liberator && this.liberator();
        }
	}
	MonitorTypeController.$inject = ['Monitor', 'capitalizeFilter'];

	const component = {
        templateUrl: 'dashboard/monitor/window/type/monitor-type.html',
        controller: MonitorTypeController,
        controllerAs: 'monitorTypeVm',
        bindings: {
            windowSize: '<',
            type: '<'
        }
    };
	
	angular.module('isms.dashboard').component('monitorType', component);
}());
