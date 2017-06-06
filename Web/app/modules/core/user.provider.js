(function () {
	'use strict';

	function UserProvider(ServiceBase, $q) {
	    this.$get = User;

	    class User extends ServiceBase {
	        constructor() {
	            super('users');
            }

            current() {
	            return $q.when({ id: 'avalon' });
            }
        }
    }
    UserProvider.$inject = ['ServiceBase', '$q'];

	angular.module('isms.core').service('User', UserProvider);
}());
