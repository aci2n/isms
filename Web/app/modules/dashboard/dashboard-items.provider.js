(function () {
	'use strict';

	function DashboardItemsProvider() {
        const items = {};
        
        this.register = register;

        function register(name, label) {
            items['dashboard.' + name] = { label };
        }

        this.$get = class DashboardItems {
            all() {
                return items;
            }

            has(state) {
                return state in items;
            }
        }
    }
	DashboardItemsProvider.$inject = [];

	angular.module('isms.dashboard').provider('DashboardItems', DashboardItemsProvider);
}());
