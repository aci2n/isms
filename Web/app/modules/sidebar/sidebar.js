(function () {
	'use strict';
	
	function SidebarController() {
		const ctrl = this;
		
		ctrl.menuChange = function (menu) {
			ctrl.onMenuChange(menu);
		};
	}

	angular.module('isms.sidebar', []).component('sidebar', {
		templateUrl: 'sidebar/sidebar.html',
		controller: SidebarController,
		bindings: {
			menus: '<',
			onMenuChange: '&'
		}
	});
}());
