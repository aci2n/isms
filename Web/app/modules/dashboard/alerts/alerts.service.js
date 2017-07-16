(function () {
	'use strict';

    class Alerts {
        constructor($http, WebsocketHelper, Notifications, EventHelper) {
        	this.$http = $http;
            this.endpoint = '/api/alert';
            this.WebsocketHelper = WebsocketHelper;
            this.Notifications = Notifications;
            this.onAlert = EventHelper.create();
            this.enableNotifications();
        }

        enableNotifications() {
            this.notificationsEnabled = true;
        }

        disableNotifications() {
            this.notificationsEnabled = false;
        }
        
        createNotification(alert) {
        	let notification = null;
        	
        	if (alert.data) {
	        	const data = alert.data.toFixed(3);
	        	
	        	notification = {
	    			text: `[${alert.threshold.type}] Received ${alert.type} reading of ${data}.`,
	    			type: alert.threshold.type,
	    			state: 'dashboard.alerts',
	    			dismiss: () => this.markRead(alert.id)
	        	};
        	}
        	
        	return notification;
        }

        onMessage(event) {
            const alerts = angular.fromJson(event.data);
            
            if (angular.isArray(alerts)) {
	            for (const alert of alerts) {
		            if (angular.isObject(alert)) {
		                if (this.notificationsEnabled) {
		                	const notification = this.createNotification(alert);
		                	
		                	if (notification) {
		                		this.Notifications.trigger(notification);
		                	}
		                }
		                this.onAlert.trigger(alert);
		            }
	            }
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
        
        markRead(id) {
        	return this.$http.post(`${this.endpoint}/${id}`);
        }
    }
    Alerts.$inject = ['$http', 'WebsocketHelper', 'Notifications', 'EventHelper'];

	angular.module('isms.dashboard').service('Alerts', Alerts);
}());
