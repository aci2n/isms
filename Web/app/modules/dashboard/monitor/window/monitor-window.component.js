(function () {
	'use strict';

	class MonitorWindowController {
	    constructor(Monitor, capitalizeFilter) {
	        this.Monitor = Monitor;
	        this.capitalizeFilter = capitalizeFilter;
        }

		loadDatasets() {
			this.Monitor.forWindowSize(this.windowSize).then(response => {
				this.datasets = this.getDatasets(response.data);
			});
		}
		
		getDatasets(data) {
			const datasets = data.reduce((prev, curr) => {
				let type = curr.key.type;
				let point = { x: curr.windowStart, y: curr.metric.average };
				
				if (!prev[type]) {
					prev[type] = [];
				}
				prev[type].push(point);
				
				return prev;
			}, {});
			
			return datasets;
		}
		
		setCurrentDataset(type) {
            this.chart.data = this.datasets[type];
            this.chart.options.label = this.capitalizeFilter(type);
		}

		defaultChart() {
            return {
                data: [],
                    options: {
                label: '',
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
			this.chart = this.defaultChart();
			this.loadDatasets();
		}
	}
	MonitorWindowController.$inject = ['Monitor', 'capitalizeFilter'];

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
