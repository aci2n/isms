(function () {
	'use strict';

	function WindowedMetricsController(WindowedMetricsService, $state) {
		const ctrl = this;
		
		ctrl.updateWindowSizeState = function (windowSize) {
			$state.go('dashboard.windowedMetrics.details', { windowSize });
		};
		
		ctrl.$onInit = function () {
			WindowedMetricsService.getAvailableSizes().then(response => {
				ctrl.availableWindowSizes = response.data;
			});
		};
	}
	WindowedMetricsController.$inject = ['WindowedMetricsService', '$state'];

	angular.module('isms.windowedMetrics').component('windowedMetrics', {
		templateUrl: 'windowed-metrics/windowed-metrics.component.html',
		controller: WindowedMetricsController
	});
}());
