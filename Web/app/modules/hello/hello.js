(function (app) {
	'use strict';
	
	function HelloController($scope) {
		$scope.msg = 'Hello';
	}
	
	HelloController.$inject = ['$scope'];
	app.controller('HelloController', HelloController);
}(window.app));

