(function () {
	'use strict';

	function UserService($http, $q) {
		const service = this;
		const endpoint = '/api/users';

		service.current = function () {
			return $q.when({
				id: 'avalon'
			});
		};
	}
	UserService.$inject = ['$http', '$q'];

	angular.module('isms.common.services', []).service('UserService', UserService);
}());
