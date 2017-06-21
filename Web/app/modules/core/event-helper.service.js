(function () {
	'use strict';
	
    class Event {
        constructor() {
        	this.currentId = 0;
            this.delegates = {};
        }

        subscribe(delegate) {
            const id = this.currentId++;
            this.delegates[id] = delegate;

            return () => this.unsubscribe(id);
        }
        
        unsubscribe(...ids) {
        	for (const id of ids) {
        		delete this.delegates[id];
        	}
        }

        trigger(t) {
        	const failures = [];
        	
            for (const id in this.delegates) {
            	try {
            		this.delegates[id](t);
            	} catch (e) {
            		failures.push(id);
            	}
            }
            
            if (failures.length > 0) {
            	this.unsubscribe(...failures);
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
