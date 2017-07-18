process.env.DISABLE_NOTIFIER = true
/* get rid of obnoxious popup */

/**
 * Created by Marco on 07/10/2016.
 */
var gulp = require('gulp'),
    gutil = require('gulp-util'),
    plugins = require('gulp-load-plugins')(),
    del = require('del'),
    es = require('event-stream'),
    mainBowerFiles = require('main-bower-files'),
    Q = require('q'),
    watch = require('gulp-watch'),
    sourcemaps = require('gulp-sourcemaps'),
    templateCache = require('gulp-angular-templatecache'),
    notify = require("gulp-notify"),
    connect = require('gulp-connect'),
    cors = require('cors'),
    babel = require('gulp-babel'),
    zip = require('gulp-zip'),
    bower = require('gulp-bower'),
    beautify = require('gulp-beautify')
    ;
// Server = require('karma').server,

var fs = require('fs'),
    semver = require('semver'),
    bump = require('gulp-bump'),
    filter = require('gulp-filter');
// == PATH STRING
//

// do' priorita a jquery altrimenti angular continuerebbe ad usare jqlite
var globalVendor = ['bower_components/jquery/dist/jquery.js']
    .concat(mainBowerFiles({includeDev: true},
        [
            '**/*.js'
            , '!bower_components/jquery/**/*.js'
        ])
    );

var paths = {
    scripts: ['app/services/**/*.js',
        'app/component/**/components/**/*.js',
        'app/component/**/*.js',
        'app/factory/*.js',
        'assets/javascripts/**/*.js',
        'app/widget/**/*.js',
        'app/widget/*.js',
        'app/app.constants.js',
        'app/app.constants.url.js',
        'app/app.js'],
    resources: ['resources/**/*.json'],
    myResources: ['app/config/*.json'],
    styles: [
        './assets/stylesheets/scss/main.scss',
        './assets/stylesheets/scss/**/*.scss',
        './bower_components/angular-busy/dist/angular-busy.css',
        './bower_components/angular-loading-bar/build/loading-bar.css',
        './bower_components/components-font-awesome/css/font-awesome.css',
        './bower_components/angular-toastr/dist/angular-toastr.min.css'
        // './bower_components/components-font-awesome/scss/font-awesome.scss'
        // './bower_components/angular-ui-select/dist/select.css',
        // './bower_components/angular-dialog-service/dist/dialogs.css',
        // './bower_components/codemirror/lib/codemirror.css'
    ],
    assets: {
        fonts: [
            './assets/fonts/**/*',
            './bower_components/bootstrap-sass/assets/fonts/**/*'
        ],
        images: [
            'assets/images/**/*'
        ],
        resources: [
            'app/resources/*',
            'app/resources/**/*'
        ],
        resourcesFiltered: [
            'app/resources-filtered/**/*'
        ],
    },
    index: './html/index.html',
    partials: [
        'app/component/**/components/**/*-tpl.html',
        'app/component/**/*-tpl.html',
        'app/component/msa/msa-cg-tpl.html',
        'app/widget/**/*-tpl.html',
        'app/widget/*-tpl.html',
        'app/services/modal/*-tpl.html',
        'app/popover/*-tpl.html'
    ],
    distDev: './dist',
    distScripts: '/scripts',
    distScriptsVendor: '/scripts/vendors',
    distCss: '/css',
    distColl: './src/main/resources',
    bowerDir: './bower_components/',
    scriptsDevServer: 'dist/scripts',
    prod: './archive',
    distProd: './dist_prod',
    distPreProd: './dist_pre_prod',
    optionMode: {mode: "0777"}
}


function getAssetsPath() {
    var assetsPath =
        paths.assets.fonts.concat(
            paths.assets.images.concat(
                paths.assets.resources));


    /*  .concat(
     paths.assets.resourcesFiltered))); */

    return assetsPath;
};


// == PIPE SEGMENTS

var pipes = {};

pipes.orderedAppScripts = function () {
    return plugins.angularFilesort();
};

pipes.builtAppDev = function () {
    return es.merge(pipes.builtIndexDev(), pipes.builtPartialsDev(), pipes.processedAssets(), pipes.icons());
};


pipes.processedAssets = function () {

    var fontsStream = gulp.src(paths.assets.fonts)
        .pipe(gulp.dest(paths.distDev + '/fonts/', paths.optionMode));

    var imagesStream = gulp.src(paths.assets.images)
        .pipe(gulp.dest(paths.distDev + '/images/', paths.optionMode));

    var resourcesStream = gulp.src(paths.assets.resources)
        .pipe(gulp.dest(paths.distDev + '/resources/', paths.optionMode));

    var resourcesFilteredStream = gulp.src(paths.assets.resourcesFiltered)
        .pipe(gulp.dest(paths.distDev + '/', paths.optionMode));

    return es.merge(fontsStream, imagesStream, resourcesStream, resourcesFilteredStream);
};

pipes.icons = function () {
    return gulp.src(paths.bowerDir + '/components-font-awesome/fonts/*')
        .pipe(gulp.dest(paths.distDev + '/fonts', paths.optionMode));
};

pipes.configuration = function () {
    return gulp.src(paths.myResources)
        .pipe(gulp.dest(paths.distDev + '/config', paths.optionMode));
}

pipes.builtVendorScriptsProd = function () {
    return gulp.src(globalVendor)
        .pipe(plugins.concat('vendor.min.js'))
        .pipe(gulp.dest(paths.distRoot + paths.distScripts, paths.optionMode));
};

pipes.builtVendorScriptsDev = function () {
    return gulp.src(globalVendor)
    //        .pipe(plugins.uglify().on('error', gutil.log))
        .pipe(gulp.dest(paths.distDev + paths.distScriptsVendor, paths.optionMode));
};

pipes.builtLoadingBarScriptsDev = function () {
    return gulp.src(['./bower_components/angular-loading-bar/build/loading-bar.js'])
        .pipe(gulp.dest(paths.distDev + paths.distScriptsVendor, paths.optionMode));
};
pipes.builtCodeMirrorScriptsDev = function () {
    return gulp.src(['./bower_components/codemirror/mode/htmlmixed/htmlmixed.js',
        './bower_components/codemirror/mode/xml/xml.js',
        './bower_components/codemirror/mode/javascript/javascript.js',
        './bower_components/codemirror/mode/vbscript/vbscript.js',
        './bower_components/codemirror/mode/css/css.js',
        'addon/selection/selection-pointer.js'])
        .pipe(gulp.dest(paths.distDev + paths.distScriptsVendor, paths.optionMode));
};

pipes.validatedAppScripts = function () {
    return gulp.src(paths.scripts)
        .pipe(plugins.jshint())
        .pipe(plugins.jshint.reporter('jshint-stylish'));
};

pipes.builtAppScriptsDev = function () {

    var start = Date.now();

    return pipes.validatedAppScripts()
        .pipe(babel({
            presets: ['es2015']
        })) /* il supporto a babel modifica il codice sorgente rendendo impossibile il debug su IDE */
        .on("error", notify.onError("Error: <%= error.message %>"))
        .pipe(plugins.sourcemaps.init())
        .pipe(gulp.dest(paths.distDev + paths.distScripts, paths.optionMode))
        .pipe(notify('built in ' + (Date.now() - start) + 'ms'));

};

pipes.builtStylesDev = function () {
    return gulp.src(paths.styles)
        .pipe(plugins.sass(
            {
                includePaths: [
                    paths.bowerDir
                ],
                sourceComments: 'normal'
            }))
        .on('error', notify.onError("Error: <%= error.message %>"))
        .pipe(gulp.dest(paths.distDev + paths.distCss, paths.optionMode));
};


pipes.validatedIndex = function () {
    return gulp.src(paths.index)
        .pipe(plugins.htmlhint())
        .pipe(plugins.htmlhint.reporter());
};


pipes.builtIndexDev = function () {

    var orderedVendorScripts = pipes.builtVendorScriptsDev();

    var orderedCodeMirrorScripts = pipes.builtCodeMirrorScriptsDev();
    var orderedLoadingBarScripts = pipes.builtLoadingBarScriptsDev();


    var orderedAppScripts = pipes.builtAppScriptsDev()
        .pipe(pipes.orderedAppScripts());

    var appStyles = pipes.builtStylesDev();

    return pipes.validatedIndex()
        .pipe(gulp.dest(paths.distDev, paths.optionMode)) // write first to get relative path for inject
        .pipe(plugins.inject(orderedVendorScripts, {relative: true, name: 'bower'}))
        .pipe(plugins.inject(orderedCodeMirrorScripts, {relative: true, name: 'codemirror'}))
        .pipe(plugins.inject(orderedLoadingBarScripts, {relative: true, name: 'loadingBar'}))
        .pipe(plugins.inject(orderedAppScripts, {relative: true}))
        .pipe(plugins.inject(appStyles, {relative: true}))
        //  .pipe(plugins.inject(configurationApp ,{relative :true}))
        .pipe(gulp.dest(paths.distDev, paths.optionMode));
};

pipes.builtPartialsDev = function () {
    return pipes.validatedPartials()
        .pipe(gulp.dest(paths.distDev, paths.optionMode));
};

pipes.validatedPartials = function () {
    console.log("paths.partials *********************************************************************");
    console.log(paths.partials);
    return gulp.src(paths.partials, {base: './'})
        .pipe(plugins.htmlhint({'doctype-first': false}))
        .pipe(plugins.htmlhint.reporter());
};

pipes.validatedDevServerScripts = function () {
    return gulp.src(paths.scriptsDevServer)
        .pipe(plugins.jshint())
        // .pipe(beautify({indent_size: 2}))
        .pipe(plugins.jshint.reporter('jshint-stylish'));
};


// == GULP TASK

gulp.task('clean-dev', function () {
    var deferred = Q.defer();
    del(paths.distDev, function () {
        deferred.resolve();
    });
    return deferred.promise;
});

gulp.task('clean-build-app-dev', ['clean-dev'], pipes.builtAppDev);

gulp.task('validate-devserver-scripts', ['clean-build-app-dev'], pipes.validatedDevServerScripts);

gulp.task('addConfig-dev', ['validate-devserver-scripts'], pipes.configuration);

gulp.task('bower', function () {
    var opts = {
        "interactive": true,
    }

    return bower(opts);
});


gulp.task('templates', function () {
    return gulp.src('app/**/*.html')
        .pipe(templateCache({module: 'msa', root: 'app'}))
        .pipe(gulp.dest('.tmp', paths.optionMode));
});


pipes.processedAssets = function () {


    gulp.src('bower_components/bootstrap-sass/assets/fonts/**/*')
        .pipe(gulp.dest(paths.distDev + '/fonts/', paths.optionMode));


    gulp.src('assets/fonts/**/*')
        .pipe(gulp.dest(paths.distDev + '/fonts/', paths.optionMode));

    gulp.src('assets/images/**/*')
        .pipe(gulp.dest(paths.distDev + '/images/', paths.optionMode));

    gulp.src('app/resources/**/*')
        .pipe(gulp.dest(paths.distDev + '/resources/', paths.optionMode));

    return gulp.src('app/resources-filtered/**/*')
        .pipe(gulp.dest(paths.distDev + '/', paths.optionMode));

};

pipes.processedAssetsProd = function () {

    var configStream = gulp.src(paths.myResources)
        .pipe(gulp.dest(paths.distRoot + '/config', paths.optionMode));


    var fontsStream = gulp.src(paths.assets.fonts)
        .pipe(gulp.dest(paths.distRoot + '/fonts/', paths.optionMode));

    var imagesStream = gulp.src(paths.assets.images)
        .pipe(gulp.dest(paths.distRoot + '/images/', paths.optionMode));

    var resourcesStream = gulp.src(paths.assets.resources)
        .pipe(gulp.dest(paths.distRoot + '/resources/', paths.optionMode));

    var resourcesFilteredStream = gulp.src(paths.assets.resourcesFiltered)
        .pipe(gulp.dest(paths.distRoot + '/', paths.optionMode));

    return es.merge(configStream, fontsStream, imagesStream, resourcesStream, resourcesFilteredStream);


    //gulp.src(paths.assets)
    //    .pipe(gulp.dest(paths.distRoot + '/fonts/'));

    //gulp.src('assets/images/**/*')
    //    .pipe(gulp.dest(paths.distRoot + '/images/'));


    //return gulp.src('app/resources/**/*')
    //    .pipe(gulp.dest(paths.distRoot + '/resources/'));

};

pipes.builtIndexProd = function () {


    var vendorScripts = pipes.builtVendorScriptsProd();
    var appScripts = pipes.builtAppScriptsProd();
    var codeMirrorScripts = pipes.builtCodeMirrorScriptsProd();
    var loadingBarScripts = pipes.builtLoadingBarScriptsProd();
    var appStyles = pipes.builtStylesProd();
    // var properties = pipes.copyProperties();
    //  var appPartials = pipes.builtPartialsProd();

    return pipes.validatedIndex()
        .pipe(gulp.dest(paths.distRoot, paths.optionMode)) // write first to get relative path for inject
        .pipe(plugins.inject(vendorScripts, {relative: true, name: 'bower'}))
        .pipe(plugins.inject(codeMirrorScripts, {relative: true, name: 'codemirror'}))
        .pipe(plugins.inject(loadingBarScripts, {relative: true, name: 'loadingBar'}))
        // .pipe(plugins.inject(appScripts, {relative: true})) // doppione
        .pipe(plugins.inject(appScripts, {relative: true}))
        //        .pipe(plugins.inject(appPartials, {relative: true, name: 'properties'}))
        .pipe(plugins.inject(appStyles, {relative: true}))
        .pipe(plugins.htmlmin({collapseWhitespace: true, removeComments: true}))
        .pipe(gulp.dest(paths.distRoot, paths.optionMode));

};

pipes.builtPartialsProd = function () {
    return pipes.validatedPartials()
        .pipe(plugins.htmlhint.failReporter())
        .pipe(plugins.htmlmin({collapseWhitespace: true, removeComments: true}))
        .pipe(gulp.dest(paths.distRoot, paths.optionMode));
};
pipes.builtAppProd = function () {
    paths.scripts = [
        'app/component/**/**/*.js',
        'app/factory/*.js',
        'app/widget/**/*.js',

        'app/app.js',
        '.tmp/templates.js'];
    paths.distRoot = paths.distProd;
    return es.merge(pipes.builtIndexProd(), pipes.processedAssetsProd(), pipes.builtPartialsProd());
};


pipes.builtAppScriptsProd = function () {

    return pipes.validatedAppScripts()
        .pipe(babel({
            presets: ['es2015'],
            compact: true
        }))
        .pipe(pipes.orderedAppScripts())
        .pipe(plugins.sourcemaps.init())
        .pipe(plugins.concat('app.min.js'))
        //    .pipe(plugins.uglify().on('error', gutil.log))
        .pipe(plugins.sourcemaps.write())

        .pipe(gulp.dest(paths.distRoot + paths.distScripts, paths.optionMode));

};


pipes.copyProperties = function () {
    return gulp.src('app/app.constants.js')
        .pipe(gulp.dest(paths.distRoot + '/JS/', paths.optionMode));

};
pipes.minifiedFileName = function () {
    return plugins.rename(function (path) {
        path.extname = '.min' + path.extname;
    });
};

pipes.builtStylesProd = function () {
    return gulp.src(paths.styles)
        .pipe(plugins.sourcemaps.init())
        .pipe(plugins.sass(
            {
                includePaths: [
                    paths.bowerDir,
                    paths.directivesDir
                ],
                sourceComments: 'normal'
            }))
        //        .pipe(plugins.minifyCss())
        .pipe(plugins.sourcemaps.write())
        .pipe(pipes.minifiedFileName())
        .pipe(gulp.dest(paths.distRoot, paths.optionMode));
};

pipes.builtLoadingBarScriptsProd = function () {

    return gulp.src(['./bower_components/angular-loading-bar/build/loading-bar.js'])
        .pipe(plugins.concat('loading-bar.min.js'))
        //        .pipe(plugins.uglify().on('error', gutil.log))
        .pipe(gulp.dest(paths.distRoot + paths.distScripts, paths.optionMode));
};
pipes.builtCodeMirrorScriptsProd = function () {

    return gulp.src(['./bower_components/codemirror/mode/htmlmixed/htmlmixed.js',
        './bower_components/codemirror/mode/xml/xml.js',
        './bower_components/codemirror/mode/javascript/javascript.js',
        './bower_components/codemirror/mode/vbscript/vbscript.js',
        './bower_components/codemirror/mode/css/css.js',
        'addon/selection/selection-pointer.js'])
        .pipe(plugins.concat('codemirror.min.js'))
        //        .pipe(plugins.uglify().on('error', gutil.log))
        .pipe(gulp.dest(paths.distRoot + paths.distScripts, paths.optionMode));
};

pipes.builtAppPreProd = function () {
    paths.scripts = [
        'app/component/**/**/*.js',
        'app/factory/*.js',


        'app/widget/**/*.js',


        'app/app.js',
        '.tmp/templates.js'];
    paths.distRoot = paths.distPreProd;
    return es.merge(pipes.builtIndexProd(), pipes.processedAssetsProd(), pipes.builtPartialsProd());
};

pipes.builtAppColl = function () {
    console.log("sono in pipes.builtAppColl");
    paths.scripts = [
        'app/component/**/**/*.js',
        'app/factory/*.js',


        'app/widget/**/*.js',

        'app/app.js',
        '.tmp/templates.js'];
    paths.distRoot = paths.distColl;
    console.log("distRoot");
    console.log(paths.distRoot);

    return es.merge(pipes.builtIndexProd(), pipes.processedAssetsProd(), pipes.builtPartialsProd());
};

gulp.task('clean-prod', function () {

    var deferred = Q.defer();

    del(paths.distProd, function () {
        deferred.resolve();
    });

    return deferred.promise;
});

gulp.task('clean-coll', function () {

    var deferred = Q.defer();

    del(paths.distColl, function () {
        deferred.resolve();
    });

    return deferred.promise;
});

gulp.task('clean-pre-prod', function () {

    var deferred = Q.defer();

    del(paths.distPreProd, function () {
        deferred.resolve();
    });

    return deferred.promise;
});


gulp.task('clean-build-app-pre-prod', ['clean-pre-prod', 'templates'], pipes.builtAppPreProd);

gulp.task('clean-build-app-prod', ['clean-prod', 'templates'], pipes.builtAppProd);

gulp.task('clean-build-app-coll', ['clean-coll', 'templates'], pipes.builtAppColl);


gulp.task('zip', function () {
    gulp.src(paths.distColl + '/**/*')
        .pipe(zip('coll.zip'))
        .pipe(gulp.dest(paths.prod, paths.optionMode));

    gulp.src(paths.distProd + '/**/*')
        .pipe(zip('prod.zip'))
        .pipe(gulp.dest(paths.prod, paths.optionMode));

    gulp.src(paths.distPreProd + '/**/*')
        .pipe(zip('pre_prod.zip'))
        .pipe(gulp.dest(paths.prod, paths.optionMode));

});

gulp.task('prod', ['clean-build-app-coll', 'clean-build-app-pre-prod', 'clean-build-app-prod'], function ()
//gulp.task('prod', ['clean-build-app-coll' ], function()
{

    console.log("creazione zip  ");
    gulp.src(paths.distColl + '/**/*')
        .pipe(zip('coll.zip'))
        .pipe(gulp.dest(paths.prod, paths.optionMode));

    gulp.src(paths.distProd + '/**/*')
        .pipe(zip('prod.zip'))
        .pipe(gulp.dest(paths.prod, paths.optionMode));

    gulp.src(paths.distPreProd + '/**/*')
        .pipe(zip('pre_prod.zip'))
        .pipe(gulp.dest(paths.prod, paths.optionMode));

});


gulp.task('site', function () {
    return gulp.src(['app/*.js', 'app/**/**/**/*.js', 'app/**/**/**/*.tpl'])
        .pipe(static_site())
        .pipe(gulp.dest('build/'))
});

gulp.task('css', function () {
    return gulp.src('./dist/css/*.css')
        .pipe(gulp.dest('build/css'));
});


gulp.task('statico', ['site', 'css'], function () {
    gulp.watch(paths.sources, ['site']);
    gulp.watch(paths.stylesheets, ['css']);
});

gulp.task('default', ['validate-devserver-scripts', 'addConfig-dev'], function () {

    connect.server({
        root: 'dist/',
        host: 'localhost.gruppoitas.it',
        port: 8887,
        livereload: true,
        middleware: function () {
            return [cors()];
        }
    });

    // start live-reload server
    plugins.livereload.listen({start: true});

    // watch index
    watch(paths.index, function () {
        return pipes.builtIndexDev()
            .pipe(plugins.livereload());
    });

    // watch app scripts
    watch(paths.scripts, function () {
        return pipes.builtIndexDev()
            .pipe(plugins.livereload());
    });

    // watch html partials
    watch(paths.partials, function () {
        return pipes.builtPartialsDev()
            .pipe(plugins.livereload(), paths.optionMode);
    });

    // watch styles
    watch(paths.styles, function () {
        return pipes.builtIndexDev()
            .pipe(plugins.livereload());
    });

    // watch assets
    watch(getAssetsPath(), function () {
        gutil.log('ASSETS watcher called on:' + getAssetsPath());
        pipes.processedAssets()
            .pipe(plugins.livereload());
    });

    // watch bower components updates
    watch('./bower_components/!**!/!*', function () {
        return pipes.builtIndexDev()
            .pipe(plugins.livereload());
    });

});


var getPackageJson = function () {
    return JSON.parse(fs.readFileSync('./package.json', 'utf8'));
};


/*****
 MAJOR ("major") version when you make incompatible API changes
 MINOR ("minor") version when you add functionality in a backwards-compatible manner
 PATCH ("patch") version when you make backwards-compatible bug fixes.
 PRERELEASE ("prerelease") a pre-release version
 */
gulp.task('bump', function () {
    // reget package
    var pkg = getPackageJson();
    // increment version

    var options = {
        type: 'prerelease'
    };


    var newVer = semver.inc(pkg.version, options);

    return gulp.src(['./version/bower.json', './version/package.json'])
        .pipe(bump({
            version: newVer,
            type: 'prerelease'
        }))
        .pipe(gulp.dest('./version'));


});