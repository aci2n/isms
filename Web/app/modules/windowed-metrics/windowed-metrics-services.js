(function () {
	'use strict';

	function WindowedMetricsService($http, UserService) {
		const service = this;
		const endpoint = '/api/windowed-metrics';
		
		service.getByOwnerAndWindowSize = function (ownerId, windowSize) {
			return $http.get(endpoint + '/' + ownerId + '/' + windowSize);
		};
		
		service.getByWindowSize = function (windowSize) {
			return UserService.current().then(user => service.getByOwnerAndWindowSize(user.id, windowSize));
		};
	}
	WindowedMetricsService.$inject = ['$http', 'UserService'];

	angular.module('isms.windowedMetrics.services', []).service('WindowedMetricsService',
			WindowedMetricsService);
}());
