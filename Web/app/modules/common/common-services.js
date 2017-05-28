(function () {
	'use strict';

	function UserService($resource, $q) {
		let service = this;
		let resource = $resource('/api/users');

		service.current = function () {
			return $q.when({
				id: 'avalon'
			});
		};
	}
	UserService.$inject = ['$resource', '$q'];

	angular.module('isms.common.services', []).service('UserService', UserService);
}());
