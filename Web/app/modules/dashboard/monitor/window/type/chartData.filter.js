(function () {
	'use strict';
	
	function ChartDataFilterFactory() {
		function average(array) {
			return array.reduce((a, b) => a + b, 0) / array.length;
		}
		
		function groupByTimestamp(points) {
			const map = {};
			
			for (const point of points) {
				if (!map[point.x]) {
					map[point.x] = [];
				}
				
				map[point.x].push(point.y);
			}
			
			return Object.keys(map).reduce((accum, timestamp) => {
				accum.push({
					x: timestamp,
					y: average(map[timestamp])
				});
				
				return accum;
			}, []);
		}
		
		function getAllLocationPoints(index) {
			let points = [];
			
			for (const section in index) {
				const sectionPoints = index[section];
				
				if (Array.isArray(sectionPoints)) {
					points.push(...sectionPoints);
				}
			}
			
			points = groupByTimestamp(points);
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
