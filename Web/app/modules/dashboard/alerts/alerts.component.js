(function () {
	'use strict';

	class AlertsController {
	    constructor(Alerts) {
	        this.Alerts = Alerts;
        }

	    $onInit() {

		}
	}
	AlertsController.$inject = ['Alerts'];

	const component = {
        templateUrl: 'dashboard/alerts/alerts.html',
        controller: AlertsController,
        controllerAs: 'alertsVm'
    };

	angular.module('isms.dashboard').component('alerts', component);
}());
