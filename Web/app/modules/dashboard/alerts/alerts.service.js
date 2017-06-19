(function () {
	'use strict';

    class Alerts {
        constructor($http, WebsocketHelper, Notifications, EventHelper) {
        	this.$http = $http;
            this.endpoint = '/api/alerts';
            this.WebsocketHelper = WebsocketHelper;
            this.Notifications = Notifications;
            this.event = EventHelper.create();
            this.notificationsEnabled = true;
        }

        enableNotifications() {
            this.notificationsEnabled = true;
        }

        disableNotifications() {
            this.notificationsEnabled = false;
        }

        onMessage(alert) {
            if (this.notificationsEnabled) {
                this.Notifications.notify(alert);
            }
            this.event.notify(alert);
        }

        register(listener) {
            return this.event.register(listener);
        }

        start() {
            // Start websocket here.
        }
    }
    Alerts.$inject = ['$http', 'WebsocketHelper', 'EventHelper'];

	angular.module('isms.dashboard').service('Alerts', Alerts);
}());
