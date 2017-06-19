(function () {
	'use strict';

    class Event {
        constructor() {
            this.listeners = {};
            this.id = 0;
        }

        register(listener) {
            const id = this.id++;
            this.listeners[id] = listener;
            const unregister = () => delete this.listeners[id];

            return unregister;
        }

        notify(data) {
            for (const id in this.listeners) {
                this.listeners[id](data);
            }
        }
    }

    class EventHelper {
        constructor() {}

        create() {
            return new Event();
        }
    }
    EventHelper.$inject = [];

	angular.module('isms.core').service('EventHelper', EventHelper);
}());
