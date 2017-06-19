(function () {
	'use strict';

	class NotificationsController {
	    constructor(Notifications) {
			this.Notifications = Notifications;
        }

		addNotification(notification) {
			this.items.push(notification);
		}

		removeNotification(index) {
			this.items.splice(index, 1);
		}

	    $onInit() {
			this.items = [];
			this.unregiser = this.Notifications.register(this.addNotification);
		}

		$onDestroy() {
			this.unregister();
		}
    }
	NotificationsController.$inject = ['Notifications'];

	const component = {
        templateUrl: 'dashboard/notifications/notifications.html',
        controller: NotificationsController,
        controllerAs: 'notificationsVm'
    };

	angular.module('isms.dashboard').component('notifications', component);
}());
