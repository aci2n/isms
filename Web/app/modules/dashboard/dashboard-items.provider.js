(function () {
	'use strict';

	function DashboardItemsProvider() {
        const items = {};

        this.$get = DashboardItems;
        this.register = register;

        function register(name, label) {
            this.items['dashboard.' + name] = { label };
        }

        class DashboardItems {
            all() {
                return items;
            }

            has(state) {
                return state in items;
            }
        }
    }
    DashboardItems.$inject = [];

	angular.module('isms.dashboard').provider('DashboardItems', DashboardItemsProvider);
}());
