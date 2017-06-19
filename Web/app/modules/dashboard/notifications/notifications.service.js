(function () {
	'use strict';

    class Notifications {
        constructor(EventHelper) {
            this.event = EventHelper.create();
        }

        register(listener) {
            return this.event.register(listener);
        }

        notify(notification) {
            this.event.notify(notification);
        }
    }
    Notifications.$inject = ['EventHelper'];

	angular.module('isms.dashboard').service('Notifications', Notifications);
}());
