(function () {
	'use strict';

	function ChartDataFilterFactory() {
		function getAllLocationPoints(index) {
			let points = [];
			
			for (const section in index) {
				const sectionPoints = index[section];
				
				if (Array.isArray(sectionPoints)) {
					points.push(...sectionPoints);
				}
			}
			
			points.sort((a, b) => a.x - b.x);
				
			return points;
		}
		
		function ChartDataFilter(data, location, section) {
			let filtered = [];
			const locationIndex = data[location];
			
			if (location !== 'all' && locationIndex) {
				const sectionPoints = locationIndex[section];
				
				if (Array.isArray(sectionPoints)) {
					filtered.push(sectionPoints);
				} else {
					filtered.push(getAllLocationPoints(locationIndex));
				}
			} else {
				for (const locationId in data) {
					filtered.push(getAllLocationPoints(data[locationId]));
				}
			}
			
			return filtered.filter(a => a.length > 0);
		}

		return ChartDataFilter;
	}
	ChartDataFilterFactory.$inject = [];

	angular.module('isms.dashboard').filter('chartData', ChartDataFilterFactory);
}());
