(function () {
	'use strict';

	function WindowedMetricsController() {
		let ctrl = this;
	}

	function WindowedMetricsDetailsController(UserService, WindowedMetricsService) {
		let ctrl = this;
		
		ctrl.$onInit = function() {
			UserService.current().then(user => {
				WindowedMetricsService.getByOwnerAndWindowSize(user.id, ctrl.windowSize, data => {
					ctrl.metrics = data;
				});
			});
		};
	}
	WindowedMetricsDetailsController.$inject = ['UserService', 'WindowedMetricsService'];
	
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
