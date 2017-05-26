'use strict';

const gulp = require('gulp');
const concat = require('gulp-concat');
const ngHtml2Js = require("gulp-ng-html2js");
const sass = require('gulp-sass');

const dist = './WebContent/dist/';
const app = './app/**/';
const modules = './node_modules/';

gulp.task('default', [ 'build' ]);

gulp.task('build', [ 'sass', 'html2js', 'concat' ]);

gulp.task('concat', function() {
	return standardProcessor([ modules + 'angular/angular.js', app + '*.js' ],
			'output.js');
});

gulp.task('html2js', function() {
	return standardProcessor([ app + '*.html' ], 'templates.js', ngHtml2Js({
		stripPrefix : 'modules/'
	}));
});

gulp.task('sass', function() {
	return standardProcessor([ app + '*.scss' ], 'style.css', sass());
});

function standardProcessor(src, output, processor) {
	let line = gulp.src(src);
	if (processor) {
		line = line.pipe(processor);
	}
	return line.pipe(concat(output)).pipe(gulp.dest(dist));
}