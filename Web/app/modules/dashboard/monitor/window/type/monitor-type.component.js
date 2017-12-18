(function () {
	'use strict';

	class MonitorTypeController {
	    constructor(Monitor, capitalizeFilter, dateFilter, $timeout, chartDataFilter) {
	        this.Monitor = Monitor;
	        this.capitalizeFilter = capitalizeFilter;
	        this.dateFilter = dateFilter;
	        this.$timeout = $timeout;	        
	        this.chartDataFilter = chartDataFilter;
        }

		loadDataset(windowSize, type) {
			const realtime = windowSize === 0;
			const resource = this.Monitor.forWindowAndType(windowSize, type, points => {
				this.updateData(this.data, points);
				
				if (!realtime && !this.hasEnoughData()) {
					this.inactivate();
				}
				
				return this.data;
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
			this._updatedData = true;
			
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
		
		selectSection(section) {
			this.currentSection = section;
		}
		
		hasEnoughData() {
			return Object.keys(this.data).length > 1 || Array.isArray(this.data.all[null]);
		}
		
		inactivate() {
			return this.inactive = true;
		}
		
		refreshChartData() {
			this.chart.data = this.chartDataFilter(this.data, this.currentLocation, this.currentSection);
		}
		
		$doCheck() {
			if (this._updatedData) {
				this.refreshChartData();
				delete this._updatedData;
			}
		}
		
		$onInit() {
			this.windowSize = Number.parseInt(this.windowSize);
	        this.chart = this.defaultChart(this.capitalizeFilter(this.type));
	        this.data = {all: {}};
	        this.currentLocation = 'all';
	        this.currentSection = null;
			this.loadDataset(this.windowSize, this.type);
		}

		$onDestroy() {
	        if (angular.isFunction(this.free)) {
	        	this.free();
	        }
        }
	}
	MonitorTypeController.$inject = ['Monitor', 'capitalizeFilter', 'dateFilter', '$timeout', 'chartDataFilter'];

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
