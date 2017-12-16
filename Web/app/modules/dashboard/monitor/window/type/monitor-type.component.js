(function () {
	'use strict';

	class MonitorTypeController {
	    constructor(Monitor, capitalizeFilter, dateFilter, $timeout) {
	        this.Monitor = Monitor;
	        this.capitalizeFilter = capitalizeFilter;
	        this.dateFilter = dateFilter;
	        this.$timeout = $timeout;	        
        }

		loadDataset(windowSize, type) {
			const realtime = windowSize === 0;
			const resource = this.Monitor.forWindowAndType(windowSize, type, points => {
				this.updateData(this.chart.data, points);
				
				if (!realtime && !this.hasEnoughData()) {
					this.inactivate();
				}
				
				return this.chart.data;
			});

			if (realtime) {
			    this.free = resource.free;
				this.$timeout(() => {
					if (!this.hasEnoughData()) {
						this.inactivate();
						this.free();
						delete this.free;
					}
				}, 5000);
            }
		}
		
		updateData(data, points) {
			points.forEach(function(point) {
				const location = point.location;
				
				let locationIndex = data[location.locationId]; 
				if (!locationIndex) {
					locationIndex = data[location.locationId] = {};
				}
				
				let sectionPoints = locationIndex[location.section];
				if (!sectionPoints) {
					sectionPoints = locationIndex[location.section] = [];
				}
				
				sectionPoints.push({x: point.x, y: point.y});
			});
		}

		defaultChart(label) {
			// Value comes as a UNIX timestamp (seconds), js Date takes millis.
			const tick = value => this.dateFilter(value * 1000, 'dd-MM-yyyy hh:mm:ss');
			
            return {
                data: {},
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
			return Object.keys(this.chart.data).length > 0;
		}
		
		inactivate() {
			return this.inactive = true;
		}
		
		$onInit() {
			this.windowSize = Number.parseInt(this.windowSize);
	        this.chart = this.defaultChart(this.capitalizeFilter(this.type));
			this.loadDataset(this.windowSize, this.type);
		}

		$onDestroy() {
	        if (angular.isFunction(this.free)) {
	        	this.free();
	        }
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
