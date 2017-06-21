(function () {
	'use strict';

    class Alerts {
        constructor($http, WebsocketHelper, Notifications, EventHelper) {
        	this.$http = $http;
            this.endpoint = '/api/alerts';
            this.WebsocketHelper = WebsocketHelper;
            this.Notifications = Notifications;
            this.onAlert = EventHelper.create();
            this.notificationsEnabled = true;
        }

        enableNotifications() {
            this.notificationsEnabled = true;
        }

        disableNotifications() {
            this.notificationsEnabled = false;
        }

        onMessage(event) {
            const alert = angular.fromJson(event.data);
            if (alert) {
                if (this.notificationsEnabled) {
                    this.Notifications.trigger(alert);
                }
                this.onAlert.trigger(alert);
            }
        }

        subscribe(delegate) {
            return this.onAlert.subscribe(delegate);
        }

        start() {
            const handle = this.WebsocketHelper.open('alert');
            handle.onMessage(this.onMessage.bind(this));
        }

        unread() {
            return this.$http.get(`${this.endpoint}`);
        }
    }
    Alerts.$inject = ['$http', 'WebsocketHelper', 'Notifications', 'EventHelper'];

	angular.module('isms.dashboard').service('Alerts', Alerts);
}());
