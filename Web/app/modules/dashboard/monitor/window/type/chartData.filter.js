(function () {
	'use strict';

	function ChartDataFilterFactory() {
		function getPointsInIndex(index) {
			let points = [];
			
			for (const section in index) {
				const sectionPoints = index[section];
				
				if (Array.isArray(sectionPoints)) {
					points.push(...index[section]);
				}
			}
				
			return points;
		}
		
		function ChartDataFilter(data, location, section) {
			let filtered = [];
			const locationIndex = data[location];
			
			if (locationIndex) {
				const sectionPoints = locationIndex[section];
				
				if (Array.isArray(sectionPoints)) {
					filtered = sectionPoints;
				} else {
					filtered = getPointsInIndex(locationIndex);
				}
			} else {
				for (const locationId in data) {
					filtered.push(...getPointsInIndex(data[locationId]));
				}
			}
			
			return filtered;
		}

		return ChartDataFilter;
	}
	ChartDataFilterFactory.$inject = [];

	angular.module('isms.dashboard').filter('chartData', ChartDataFilterFactory);
}());
