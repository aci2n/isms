(function(window, angular) {
	'use strict';

	let app = angular.module('app', [ 'ngRoute' ]);

	app.config([ '$routeProvider', '$locationProvider',
			function($routeProvider, $locationProvider) {
				$routeProvider.when('/hello', {
					templateUrl : 'hello/hello.html',
					controller : 'HelloController'
				});

				$locationProvider.html5Mode(true);
			} ]);

	window.app = app;
}(window, window.angular));