(function () {
	'use strict';

	class NotificationsController {
	    constructor(Notifications, $q) {
			this.Notifications = Notifications;
			this.$q = $q;
        }

		addNotification(notification) {
			this.items.push(notification);
		}

		removeNotification(index) {
			this.items.splice(index, 1);
		}
		
		dismiss(notification, index, event) {
			event.stopPropagation();
			return this.$q.when(notification.dismiss()).then(() => this.removeNotification(index));
		}

	    $onInit() {
			this.items = [];
			this.unsubscribe = this.Notifications.subscribe(this.addNotification.bind(this));
		}

		$onDestroy() {
			this.unsubscribe();
		}
    }
	NotificationsController.$inject = ['Notifications', '$q'];

	const component = {
        templateUrl: 'dashboard/notifications/notifications.html',
        controller: NotificationsController,
        controllerAs: 'notificationsVm'
    };

	angular.module('isms.dashboard').component('notifications', component);
}());
