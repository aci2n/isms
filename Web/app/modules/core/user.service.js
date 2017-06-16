(function () {
	'use strict';

    class User {
        constructor($q) {
        	this.$q = $q;
            this.endpoint = 'api/users;'
        }

        current() {
            return this.$q.when({ id: 'avalon' });
        }
    }
    User.$inject = ['$q'];

	angular.module('isms.core').service('User', User);
}());
