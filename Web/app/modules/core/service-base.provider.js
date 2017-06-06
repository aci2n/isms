(function () {
    'use strict';

    function ServiceBaseProvider($http) {
        this.$get = ServiceBase;

        class ServiceBase {
            constructor(endpoint) {
                this.endpoint = `/api/${endpoint}/`;
            }

            get(path = '') {
                return $http.get(this.endpoint + path);
            }

            post(path = '', data = null) {
                return $http.post(this.endpoint + path, data);
            }
        }
    }
    ServiceBaseProvider.$inject = ['$http'];

    angular.module('isms.core').provider('ServiceBase', ServiceBaseProvider);
}());
