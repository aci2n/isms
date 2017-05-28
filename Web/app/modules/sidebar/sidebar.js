(function () {
	'use strict';

	angular.module('isms.sidebar', []).component('sidebar', {
		templateUrl: 'sidebar/sidebar.html',
		bindings: {
			menus: '<'
		}
	});
}());
