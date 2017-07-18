// Karma configuration
// Generated on Thu Feb 02 2017 16:39:54 GMT+0100 (CET)

module.exports = function (config) {
    config.set({

        // base path that will be used to resolve all patterns (eg. files, exclude)
        basePath: '.',


        // frameworks to use
        // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
        frameworks: ['jasmine'],


        // list of files / patterns to load in the browser
        files: [
            // '../dist/config/**/*.json',
            // '../dist/resources/**/*.json',
            // '../dist/app/**/*.html',
            // '../dist/*.html',
            // '../dist/css/**/*.css',
            // '../dist/fonts/**/*.*',
            // '../dist/images/**/*.*',
            '../dist/scripts/vendors/angular.js',
            '../dist/scripts/vendors/jquery.js',
            // '../dist/scripts/vendors/angular_1_router.js',
            // '../dist/scripts/vendors/angular-animate.js',
            // '../dist/scripts/vendors/angular-busy.js',
            // '../dist/scripts/vendors/angular-cookies.js',
            // '../dist/scripts/vendors/angular-resource.js',
            // '../dist/scripts/vendors/angular-sanitize.js',
            '../dist/scripts/vendors/angular-translate.js',
            // '../dist/scripts/vendors/angular-translate-loader-static-files.js',
            // '../dist/scripts/vendors/angular-translate-storage-cookie.js',
            // '../dist/scripts/vendors/angular-translate-storage-local.js',
            // '../dist/scripts/vendors/bootstrap.js',
            // '../dist/scripts/vendors/es6-shim.js',
            // '../dist/scripts/vendors/loading-bar.js',
            // '../dist/scripts/vendors/ng-file-upload.js',
            // '../dist/scripts/vendors/ng-file-upload-shim.js',
            // '../dist/scripts/vendors/ng_route_shim.js',
            // '../dist/scripts/vendors/ng-stomp.js',
            // '../dist/scripts/vendors/ngStorage.js',
            // '../dist/scripts/vendors/select.js',
            // '../dist/scripts/vendors/showErrors.js',
            // '../dist/scripts/vendors/sockjs.js',
            // '../dist/scripts/vendors/stomp.min.js',
            // '../dist/scripts/vendors/tmhDynamicLocale.js',
            // '../dist/scripts/vendors/ui-bootstrap-tpls.js',
            // '../dist/scripts/vendors/angular-translate.js',
            '../dist/scripts/vendors/*.js',

            '../dist/scripts/app.js',

            '../dist/scripts/**/*.js',

            'e2e/**/configura_contratto.spec.js'
        ],


        // list of files to exclude
        exclude: [
            '**/*.swp'
        ],


        // preprocess matching files before serving them to the browser
        // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
        preprocessors: {},


        // test results reporter to use
        // possible values: 'dots', 'progress'
        // available reporters: https://npmjs.org/browse/keyword/karma-reporter
        reporters: ['progress'],


        // web server port
        port: 9876,


        // enable / disable colors in the output (reporters and logs)
        colors: true,


        // level of logging
        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,


        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: true,


        // start these browsers
        // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
        browsers: ['Chrome'],


        // Continuous Integration mode
        // if true, Karma captures browsers, runs the tests and exits
        singleRun: false,

        // Concurrency level
        // how many browser should be started simultaneous
        concurrency: Infinity
    })
}
