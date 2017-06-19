(function () {
	'use strict';

	function Run(Alerts) {
		Alerts.start();
	}
	Run.$inject = ['Alerts'];

	angular.module('isms.dashboard').run(Run);
}());