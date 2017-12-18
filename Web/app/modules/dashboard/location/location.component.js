(function () {
	'use strict';
	
	class LocationController {

	}
	LocationController.$inject = [];

	const component = {
        templateUrl: 'dashboard/location/location.html',
        controller: LocationController,
        controllerAs: 'locationVm',
        bindings: {
            sections: '<',
            name: '<'
        }
    };

	angular.module('isms.dashboard').component('location', component);
}());
