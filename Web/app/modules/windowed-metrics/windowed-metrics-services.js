(function () {
	'use strict';

	function WindowedMetricsService($resource) {
		let service = this;
		let resource = $resource('/api/windowed-metrics/:ownerId/:windowSize');
		
		service.getByOwnerAndWindowSize = function (ownerId, windowSize, cb) {
			return resource.query({ownerId, windowSize}, cb);
		};
	}
	WindowedMetricsService.$inject = ['$resource'];

	angular.module('isms.windowedMetrics.services', []).service('WindowedMetricsService',
			WindowedMetricsService);
}());
