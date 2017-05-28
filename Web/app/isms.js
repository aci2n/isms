(function () {
	'use strict';

	const deps = ['ui.router', 'isms.templates', 'isms.common', 'isms.dashboard', 'isms.sidebar', 'isms.windowedMetrics'];

	function LocationConfig($locationProvider) {
		$locationProvider.html5Mode(true);
	}
	LocationConfig.$inject = ['$locationProvider'];

	function StateConfig($stateProvider) {
		$stateProvider.state({
			name: 'dashboard',
			component: 'dashboard',
			url: '/dashboard'
		}).state({
			name: 'dashboard.windowedMetrics',
			component: 'windowedMetrics',
			url: '/windowed-metrics'
		}).state({
			name: 'dashboard.windowedMetrics.details',
			component: 'windowedMetricsDetails',
			url: '/{windowSize}',
			resolve: {
				windowSize: ['$stateParams', $stateParams => Number.parseInt($stateParams.windowSize)]
			}
		});
	}
	StateConfig.$inject = ['$stateProvider'];

	angular.module('isms', deps).config(LocationConfig).config(StateConfig);
}());
