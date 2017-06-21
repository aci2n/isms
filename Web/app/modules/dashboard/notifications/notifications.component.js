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
			this.unsubscribe = this.Notifications.subscribe(this.addNotification.bind(this));
		}

		$onDestroy() {
			this.unsubscribe();
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
