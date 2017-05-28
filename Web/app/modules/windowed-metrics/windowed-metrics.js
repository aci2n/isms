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
			
			const $scope = ctrl;
			$scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
			$scope.data = [
			  [65, 59, 80, 81, 56, 55, 40],
			  [28, 48, 40, 19, 86, 27, 90]
			];
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
