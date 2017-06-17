(function () {
	'use strict';

	class MonitorTypeController {
	    constructor(Monitor, capitalizeFilter, dateFilter) {
	        this.Monitor = Monitor;
	        this.capitalizeFilter = capitalizeFilter;
	        this.dateFilter = dateFilter;
        }

		loadDataset(windowSize, type) {
			const resource = this.Monitor.forWindowAndType(windowSize, type, points => {
				return (this.chart.data = this.chart.data.concat(points));
			});

			if (angular.isFunction(resource.free)) {
			    this.free = resource.free;
            }
		}

		defaultChart(label) {
			// Value comes as a UNIX timestamp (seconds), js Date takes millis.
			const tick = value => this.dateFilter(value * 1000, 'dd-MM-yyyy hh:mm:ss');
			
            return {
                data: [],
                options: {
                    label: label,
                    scales: {
                        xAxes: [{
                            type: 'linear',
                            position: 'bottom',
                            ticks: {
                                callback: tick
                            }
                        }]
                    },
                    animation: {
                    	duration: 0
                    },
                    elements: {
                        line: {
                        	borderColor: '#87D37C',
                            fill: false
                        }
                    },
                    tooltips: {
                    	enabled: false
                    }
                }
            };
        }
		
		hasEnoughData() {
			return this.chart.data.length > 2;
		}

		$onInit() {
	        this.chart = this.defaultChart(this.capitalizeFilter(this.type));
			this.loadDataset(this.windowSize, this.type);
		}

		$onDestroy() {
	        if (angular.isFunction(this.free)) {
	        	this.free();
	        }
        }
	}
	MonitorTypeController.$inject = ['Monitor', 'capitalizeFilter', 'dateFilter'];

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
