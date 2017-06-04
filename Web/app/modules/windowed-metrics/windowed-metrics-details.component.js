(function () {
	'use strict';

	function WindowedMetricsDetailsController(WindowedMetricsService, capitalizeFilter) {
		const ctrl = this;
		
		function loadDatasets() {
			WindowedMetricsService.getByWindowSize(ctrl.windowSize).then(response => {
				ctrl.datasets = getDatasets(response.data);
			});
		}
		
		function getDatasets(data) {
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
		
		ctrl.setCurrentDataset = function(type) {
			let chart = ctrl.chart;
			chart.data = ctrl.datasets[type];
			chart.options.label = capitalizeFilter(type);
		}
		
		ctrl.$onInit = function() {
			ctrl.currentDataset = null;
			ctrl.chart = {
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
			
			loadDatasets();
		};
	}
	WindowedMetricsDetailsController.$inject = ['WindowedMetricsService', 'capitalizeFilter'];
	
	angular.module('isms.windowedMetrics').component('windowedMetricsDetails', {
		templateUrl: 'windowed-metrics/windowed-metrics-details.component.html',
		controller: WindowedMetricsDetailsController,
		bindings: {
			windowSize: '<'
		}
	});
}());
