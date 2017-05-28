(function () {
	'use strict';

	let deps = ['ui.router', 'ngResource', 'isms.templates', 'isms.dashboard'];

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
			url: '/windowed-metrics/{windowSize}'
		});
	}
	StateConfig.$inject = ['$stateProvider'];

	angular.module('isms', deps).config(LocationConfig).config(StateConfig);
}());