(function () {
	'use strict';

	function WindowedMetricsController($stateParams) {
		let ctrl = this;
		ctrl.msg = angular.toJson($stateParams);
	}
	WindowedMetricsController.$inject = ['$stateParams'];

	angular.module('isms.windowedMetrics', []).component('windowedMetrics', {
		templateUrl: 'windowed-metrics/windowed-metrics.html',
		controller: WindowedMetricsController
	});
}());
