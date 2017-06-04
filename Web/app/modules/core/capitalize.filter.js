(function () {
	'use strict';

	function CapitalizeFilterFactory() {
		function CapitalizeFilter(input) {
			let filtered = null;
			
			if (angular.isString(input) && input.length > 0) {
				filtered = input.charAt(0).toUpperCase() + input.substring(1).toLowerCase();
			}
			
			return filtered;
		}

		return CapitalizeFilter;
	}
	CapitalizeFilterFactory.$inject = [];

	angular.module('isms.core').filter('capitalize', CapitalizeFilterFactory);
}());
