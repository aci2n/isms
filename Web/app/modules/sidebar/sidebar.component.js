(function () {
	'use strict';
	
	function SidebarController() {
		const ctrl = this;
		
		ctrl.menuChange = function (menu) {
			ctrl.onMenuChange(menu);
		};
	}
	SidebarController.$inject = [];

	angular.module('isms.sidebar').component('sidebar', {
		templateUrl: 'sidebar/sidebar.component.html',
		controller: SidebarController,
		bindings: {
			menus: '<',
			onMenuChange: '&'
		}
	});
}());
