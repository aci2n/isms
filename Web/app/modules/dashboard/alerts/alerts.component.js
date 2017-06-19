(function () {
	'use strict';

	class AlertsController {
	    constructor(Alerts) {
	        this.Alerts = Alerts;
        }

		addAlert(alert) {
			this.items.push(alert);
		}

	    $onInit() {
			this.items = [];
			Alerts.unread().then(response => {
				return (this.items = this.items.concat(response.data));
			});
			Alerts.disableNotifications();
			this.unregister = Alerts.register(this.addAlert);
		}

		$onDestroy() {
			Alerts.enableNotifications();
			this.unregister();
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
