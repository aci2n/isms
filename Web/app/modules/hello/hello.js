(function () {
	'use strict';

	function HelloController() {
		this.msg = 'Hello';
	}

	angular.module('isms.hello', []).component('hello', {
		templateUrl: 'hello/hello.html',
		controller: HelloController
	});
}());
