(function () {
	'use strict';

	function Config(DashboardItemsProvider, $stateProvider) {
		DashboardItemsProvider.register('alerts', 'Alerts');

		$stateProvider.state({
			name: 'dashboard.alerts',
			url: '/alerts',
			component: 'alerts'
		});
	}
	Config.$inject = ['DashboardItemsProvider', '$stateProvider'];

	angular.module('isms.dashboard').config(Config);
}());