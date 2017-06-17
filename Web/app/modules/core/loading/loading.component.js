(function () {
	'use strict';
	
	class LoadingController {
		$onInit() {
			if (!this.alt) {
				this.alt = 'Loading...';
			}
		}
	}
	LoadingController.$inject = [];

	const component = {
        templateUrl: 'core/loading/loading.html',
        controller: LoadingController,
        controllerAs: 'loadingVm',
        bindings: {
            alt: '<?'
        }
    };

	angular.module('isms.core').component('loading', component);
}());
