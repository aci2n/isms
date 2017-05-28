(function () {
	'use strict';

	function MenusService($http, $q) {
		const service = this;
		const endpoint = '/api/menus';

		service.all = function () {
			return $q.when([{
				state: 'dashboard.windowedMetrics',
				label: 'Windowed Metrics'
			}, {
				state: 'dashboard.placeholder',
				label: 'Placeholder'
			}]);
		};
	}
	MenusService.$inject = ['$http', '$q'];

	angular.module('isms.dashboard.services', []).service('MenusService', MenusService);
}());
