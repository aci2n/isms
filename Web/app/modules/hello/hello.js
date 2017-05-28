(function () {
	'use strict';

	function HelloController($resource) {
		let ctrl = this;
		let WindowedMetric = $resource('/api/windowed-metrics/:ownerId/:windowSize');
		
		WindowedMetric.query({ownerId: 'avalon', windowSize: 10}, data => {
			ctrl.msg = angular.toJson(data);
		});
		ctrl.msg = 'loading';
	}
	HelloController.$inject = ['$resource'];

	angular.module('isms.hello', []).component('hello', {
		templateUrl: 'hello/hello.html',
		controller: HelloController
	});
}());
