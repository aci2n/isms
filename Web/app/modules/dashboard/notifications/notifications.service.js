(function () {
	'use strict';

    class Notifications {
        constructor(EventHelper) {
            this.onNotification = EventHelper.create();
        }

        subscribe(delegate) {
            return this.onNotification.subscribe(delegate);
        }

        trigger(notification) {
            this.onNotification.trigger(notification);
        }
    }
    Notifications.$inject = ['EventHelper'];

	angular.module('isms.dashboard').service('Notifications', Notifications);
}());
