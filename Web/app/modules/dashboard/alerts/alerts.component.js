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
			this.Alerts.unread().then(response => {
				return (this.items = this.items.concat(response.data));
			});
			this.Alerts.disableNotifications();
			this.unregister = this.Alerts.subscribe(this.addAlert.bind(this));
		}

		$onDestroy() {
			this.Alerts.enableNotifications();
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
