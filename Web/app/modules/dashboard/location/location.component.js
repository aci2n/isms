(function () {
	'use strict';
	
	class LocationController {
		isHighlighted(highlighted, section) {
			return (Array.isArray(highlighted) && highlighted.indexOf(section) !== -1) || highlighted === section;
		}
	}
	LocationController.$inject = [];

	const component = {
        templateUrl: 'dashboard/location/location.html',
        controller: LocationController,
        controllerAs: 'locationVm',
        bindings: {
            sections: '<',
            name: '<',
            highlighted: '<',
            onPress: '&'
        }
    };

	angular.module('isms.dashboard').component('location', component);
}());
