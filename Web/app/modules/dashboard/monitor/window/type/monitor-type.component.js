(function () {
	'use strict';

	class MonitorTypeController {
	    constructor(Monitor, capitalizeFilter, dateFilter, $timeout) {
	        this.Monitor = Monitor;
	        this.capitalizeFilter = capitalizeFilter;
	        this.dateFilter = dateFilter;
	        this.$timeout = $timeout;
	        
	        this.startInactivityTimer();
        }

		loadDataset(windowSize, type) {
			const resource = this.Monitor.forWindowAndType(windowSize, type, points => {
				return (this.chart.data = this.chart.data.concat(points));
			});

			if (angular.isFunction(resource.free)) {
			    this.closeSocket = resource.free;
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
		
		startInactivityTimer() {
			this.$timeout(() => {
				if (!this.hasEnoughData()) {
					this.inactive = true;
					this.cleanup();
				}
			}, 7500);
		}
		
		cleanup() {
			if (angular.isFunction(this.closeSocket)) {
				this.closeSocket();
				delete this.closeSocket;
			}
		}

		$onInit() {
	        this.chart = this.defaultChart(this.capitalizeFilter(this.type));
			this.loadDataset(this.windowSize, this.type);
		}

		$onDestroy() {
	        this.cleanup();
        }
	}
	MonitorTypeController.$inject = ['Monitor', 'capitalizeFilter', 'dateFilter', '$timeout'];

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
