(function () {
	'use strict';

	let modules = ['ui.router', 'ngResource', 'isms.templates', 'isms.hello'];

	function LocationConfig($locationProvider) {
		$locationProvider.html5Mode(true);
	}
	LocationConfig.$inject = ['$locationProvider'];

	function StateConfig($stateProvider) {
		$stateProvider.state({
			name: 'hello',
			component: 'hello',
			url: '/hello'
		});
	}
	StateConfig.$inject = ['$stateProvider'];

	angular.module('isms', modules).config(LocationConfig).config(StateConfig);
}());