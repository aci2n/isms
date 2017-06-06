(function () {
	'use strict';
	
	class SidebarController {
		itemSelect(state, details) {
			this.onItemSelect({ state, details });
		}
	}
	SidebarController.$inject = [];

	const component = {
        templateUrl: 'sidebar/sidebar.html',
        controller: SidebarController,
        controllerAs: 'sidebarVm',
        bindings: {
            items: '<',
            onItemSelect: '&'
        }
    };

	angular.module('isms.sidebar').component('sidebar', component);
}());
