(function () {
	'use strict';

	function ChartDataFilterFactory() {
		function getIndexSections(index) {
			let sections = [];
			
			for (const section in index) {
				const sectionPoints = index[section];
				
				if (Array.isArray(sectionPoints)) {
					sections.push(sectionPoints);
				}
			}
				
			return sections;
		}
		
		function ChartDataFilter(data, location, section) {
			let filtered = [];
			const locationIndex = data[location];
			
			if (location !== 'all' && locationIndex) {
				const sectionPoints = locationIndex[section];
				
				if (Array.isArray(sectionPoints)) {
					filtered.push(sectionPoints);
				} else {
					filtered = getIndexSections(locationIndex);
				}
			} else {
				for (const locationId in data) {
					filtered.push(...getIndexSections(data[locationId]));
				}
			}
			
			return filtered;
		}

		return ChartDataFilter;
	}
	ChartDataFilterFactory.$inject = [];

	angular.module('isms.dashboard').filter('chartData', ChartDataFilterFactory);
}());
