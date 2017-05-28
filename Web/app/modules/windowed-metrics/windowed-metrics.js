(function () {
	'use strict';

	function WindowedMetricsController() {
		const ctrl = this;
	}

	function WindowedMetricsDetailsController(WindowedMetricsService) {
		const ctrl = this;
		
		ctrl.$onInit = function() {
			WindowedMetricsService.getByWindowSize(ctrl.windowSize).then(data => {
				ctrl.metrics = data;
			});
		};
	}
	WindowedMetricsDetailsController.$inject = ['WindowedMetricsService'];
	
	angular.module('isms.windowedMetrics', ['isms.windowedMetrics.services']).component('windowedMetrics', {
		templateUrl: 'windowed-metrics/windowed-metrics.html',
		controller: WindowedMetricsController
	}).component('windowedMetricsDetails', {
		templateUrl: 'windowed-metrics/windowed-metrics-details.html',
		controller: WindowedMetricsDetailsController,
		bindings: {
			windowSize: '<'
		}
	});
}());
