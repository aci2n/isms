(function () {
	'use strict';

	let deps = ['isms.sidebar', 'isms.windowedMetrics'];
	
	function DashboardController($timeout) {
		let ctrl = this;
		$timeout(() => {
			ctrl.menus = [{
				state: 'windowedMetrics',
				classSuffix: 'windowed-metrics',
				label: 'Windowed Metrics'
			}];
		}, 1000);
	}
	DashboardController.$inject = ['$timeout'];

	angular.module('isms.dashboard', deps).component('dashboard', {
		templateUrl: 'dashboard/dashboard.html',
		controller: DashboardController
	});
}());
