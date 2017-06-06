(function () {
	'use strict';

	function LocationConfig($locationProvider) {
		$locationProvider.html5Mode(true);
	}
	LocationConfig.$inject = ['$locationProvider'];

	angular.module('isms').config(LocationConfig);
}());
