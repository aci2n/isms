(function () {
	'use strict';

	function DashboardController($timeout, MenusService) {
		const ctrl = this;
		
		ctrl.$onInit = function () {
			MenusService.all().then(menus => {
				ctrl.menus = menus;
			});
		};
	}
	DashboardController.$inject = ['$timeout', 'MenusService'];

	angular.module('isms.dashboard').component('dashboard', {
		templateUrl: 'dashboard/dashboard.component.html',
		controller: DashboardController
	});
}());
