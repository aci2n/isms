(function () {
	'use strict';

	class DashboardController {
	    constructor(DashboardItems) {
	        this.DashboardItems = DashboardItems;
        }

	    $onInit() {
	        this.items = this.DashboardItems.all();
        }
    }
	DashboardController.$inject = ['DashboardItems'];

	const component = {
        templateUrl: 'dashboard/dashboard.html',
        controller: DashboardController,
        controllerAs: 'dashboardVm'
    };

	angular.module('isms.dashboard').component('dashboard', component);
}());
