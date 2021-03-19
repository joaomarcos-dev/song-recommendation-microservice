//DEPENDENCIES

  //Gulp.js
  const gulp = require('gulp');
  const gulp_sass = require('gulp-sass');
  const gulp_cssnano = require('gulp-cssnano');
  const gulp_htmlmin = require('gulp-htmlmin');
  const gulp_concat = require('gulp-concat');
  const gulp_minify = require('gulp-minify');
  const gulp_uglify = require('gulp-uglify');
  const gulp_imagemin = require('gulp-imagemin');
  const gulp_replace = require('gulp-replace');
  const gulp_find = require('gulp-find');
  const gulp_awspublish = require('gulp-awspublish');
  const gulp_fileinclude = require('gulp-file-include');
  const gulp_resolve_dependencies = require('gulp-resolve-dependencies');
  const gulp_order = require("gulp-order");
  const gulp_print = require("gulp-print").default;


  //Node
  const del = require('del');

//PRODUCTION CHANGES
  var productionChanges = {
    //link for frontend calling backend api
    "${apiUrl}":"https://api.appmusic.com.br",
    "${frontendHostname}":"appmusic.com.br"
  }

  //DEV CHANGES
  var devChanges = {
    //link for frontend calling backend api
    //So test can be done in another host calling my computer in the backend
    //Error prone if the router give me another ip address
    "${apiUrl}":"http://127.0.0.1:8084",
    "${frontendHostname}":"http://127.0.0.1:3000"
  }

//PLUGIN CONFIGURATION

  //Gulp.js
  var gulp_replace_options = {
    searchValue: 'string',
    logs: {
      notReplaced: true
    }
  }

  gulp_sass.compiler = require('sass');
  var sass_modules_path = "C:/Users/jc808853/Dropbox/projectss/Softfocus - Microservice/appmusic-frontend/src/style/sass/sass_modules";
  var sass_options = {
    includePaths: [sass_modules_path]
  }

//DEFINING TASKS functions

//HTML
  // DEV TASKS
  function copyHTMLTask_dev(){
    return gulp.src('src/**/*.html')

          //MIMIFICATION
          .pipe(gulp_htmlmin({ collapseWhitespace: true }))

          //REPLACEMENT
          .pipe(
            gulp_replace(new String("${apiUrl}"),function () {return new String(devChanges["${apiUrl}"]);},gulp_replace_options)
            )

          //REPLACEMENT PER FILE
          .pipe(gulp_fileinclude({
            prefix: '@@',
            basepath: '@root'//gulp will for the file relative to gulpfile.js directore
          }))

          .pipe(gulp.dest('./assets-prod/'));
  }

  //HTML DEV TASKS
  function copyHTMLTask_prod(){
    return gulp.src('src/**/*.html')
          .pipe(gulp_htmlmin({ collapseWhitespace: true }))
          .pipe(
            gulp_replace(
                new String("${apiUrl}"),
                function () {
                  return new String(productionChanges["${apiUrl}"]);
                },
                gulp_replace_options
              )
            )
          .pipe(gulp.dest('./assets-prod/'));
  }

  //CSS DEV TASKS
  function copyCSSLibTask_dev_prod(){
    return gulp.src('src/style/lib/**/*.css')
          .pipe(gulp_cssnano())
          .pipe(gulp.dest('./assets-prod/style/lib/'));
  }

//SASS TASKS

  //CLEAN
  function delFinalSassTask(){
    console.log('[del module] Deleted sass files. \n');
    return del(['./assets-prod/style_tmp/**/*']);
  }

  //PARSE AND REPLACE
  function replaceSassTask(){
      return gulp.src('src/style/sass/**/*.scss')
          .pipe(
              gulp_replace(
                new String("#sass_modules_project_location"), function () { return new String(sass_modules_path); },
                gulp_replace_options
              )
          )
          .pipe(gulp.dest('./assets-prod/style_tmp/sass'));
  };

  //COMPILLE
  function compileSassTask(){
      return gulp.src('./assets-prod/style_tmp/sass/**/*.scss')

          //COMPILATION
          .pipe(gulp_sass(sass_options).on('error', gulp_sass.logError))

          //MIMIFICATION
          .pipe(gulp_cssnano())

          //CONCATANATION
          .pipe(gulp_concat('style.css'))

          //DESETINATION
          .pipe(gulp.dest('./assets-prod/style'));

  };

//MEDIA TASKS

  //COPY
  function copyMediaTask(){
    return gulp.src('./src/media/**/*')

          //IMAGES OPTIMIZATION
          .pipe(gulp_imagemin())

          //DESETINATION
          .pipe(gulp.dest('./assets-prod/media/'));
  };

//SCRIPT TASKS

  // DEVELOPMENT TASKS
  function copyScripTask_dev(){

    return gulp.src('src/scripts/**/*.js')

    //REPLACEMENT
    .pipe(
      gulp_replace(
          new String("${apiUrl}"),
          function () {
            return new String(devChanges["${apiUrl}"]);
          },
          gulp_replace_options
        )
      )

    // //PRINTING FILES FROM THE STREAM
    // .pipe(gulp_print())

    //REPLACEMENT
    .pipe(
      gulp_replace(
          new String("${frontendHostname}"),
          function () {
            return new String(devChanges["${frontendHostname}"]);
          },
          gulp_replace_options
        )
      )

      // //PRINTING FILES FROM THE STREAM
      // .pipe(gulp_print())

      //RESOLVING DEPENDENCIES
      .pipe(gulp_resolve_dependencies({pattern: /\* @requires [\s-]*(.*\.js)/g, log: true}))
      .on('error', function(err) {console.log(err.message);})

      // //PRINTING FILES FROM THE STREAM
      // .pipe(gulp_print())

      //ORDERING FILES
      .pipe(gulp_order([
        //LIBRARIES
        "node_modules/angular/angular.js",
        "node_modules/angular-material/angular-material.js",
        "node_modules/angular-animate/angular-animate.js",
        "node_modules/angular-messages/angular-messages.js",
        "node_modules/angular-aria/angular-aria.js",

        //BASE MODULE
        "src/scripts/**/app.js",

        //GENERALS
        "src/scripts/general/**/*.js",

        //SERVICES
        "src/scripts/**/*Service*.js",

        "src/scripts/**/*Controller*.js",

        "src/scripts/*.js"
      ], {base: './'}))

      //PRINTING FILES FROM THE STREAM
      .pipe(gulp_print())

      //CONCATANATION
      .pipe(gulp_concat('app.js'))//concat before resolve dependencies

      //PRINTING FILES FROM THE STREAM
      .pipe(gulp_print())

      // //MIMIFICATION
      // .pipe(gulp_minify())

      // //UNGLIFYING
      // .pipe(gulp_uglify())

      //DESETINATION
      .pipe(gulp.dest('assets-prod/script'));
  }

  //PRODUCTION TASKS
  function copyScripTask_prod(){
    return gulp.src('src/scripts/**/*.js')

           //REPLACEMENT
           .pipe(
             gulp_replace(
                 new String("${apiUrl}"),
                 function () {
                   return new String(productionChanges["${apiUrl}"]);
                 },
                 gulp_replace_options
               )
             )

             //REPLACEMENT
             .pipe(
               gulp_replace(
                   new String("${frontendHostname}"),
                   function () {
                     return new String(productionChanges["${frontendHostname}"]);
                   },
                   gulp_replace_options
                )
              )

            //CONCATANATION
            .pipe(gulp_concat('app.js'))

            //RESOLVING DEPENDENCIES
            .pipe(gulp_resolve_dependencies({pattern: /\* @requires [\s-]*(.*\.js)/g}))
                .on('error', function(err) {console.log(err.message);})

            //ORDERING FILES
            .pipe(gulp_order([
              "node_modules/angular/angular.js",
              "node_modules/angular-*/*.js",
              "src/scripts/*.js"
            ], { base: './' }))

            //MIMIFICATION
            .pipe(gulp_minify())

            //UNGLIFYING
            .pipe(gulp_uglify())

            //DESETINATION
            .pipe(gulp.dest('assets-prod/script'));
  }

//CLEAN TAKS
  function deleteProdTask(cb){
      return del(['./assets-prod/**/*.*']);
      cb();
  }
  function deleteHTMLTask(cb){
      return del(['./assets-prod/**/*.html']);
      cb();
  }

  function deleteJSTask(cb){
      return del(['./assets-prod/**/*.js']);
      cb();
  }

  function deleteHTMLAll(cb){
      return del(['./assets-prod/**/*']);
      cb();
  }

  //Create GitHub Pages doc folder
  function createDocFolder(){

    //DELETE PREVIOUS DOC
    del(['./docs/*']);

    return
          //SOURCE
          gulp.src('assets-prod/**/*.*')

          //PATH CORRECTION
          .pipe(gulp_replace('assets-prod/', ''))

          //DESETINATION
          .pipe(gulp.dest('docs'));
  }

  // AWS S3
  function publishToS3() {
    //Declaring Publisher
    var publisher = gulp_awspublish.create({
      params:{
        Bucket: "appmusic.com.br"
      }
    });

    // define custom headers
     var headers = {
       "Cache-Control": "max-age=315360000, no-transform, public"
       // ...
     };

    return gulp
            .src('assets-prod/**/*.*')
            // .pipe(gulp_awspublish.gzip({ ext: ".gz" }))
            // publisher will add Content-Length, Content-Type and headers specified above
            // If not specified it will set x-amz-acl to public-read by default
            .pipe(publisher.publish(headers))
            // create a cache file to speed up consecutive uploads
            .pipe(publisher.cache())
            // print upload updates to console
            .pipe(gulp_awspublish.reporter())
  }

  //GATHERING FUNCION TASKS INTO MORE MEANINGFULL TASKS RETURNED BY series()

  var htmlTasks = gulp.series(copyHTMLTask_dev);
  var sassTasks = gulp.series( delFinalSassTask, replaceSassTask, compileSassTask );
  var cssTasks = gulp.series(copyCSSLibTask_dev_prod);
  var jsTasks = gulp.series( deleteJSTask, copyScripTask_dev);
  var allTheTasks = gulp.series(deleteHTMLTask, copyHTMLTask_dev, copyScripTask_dev, copyMediaTask);

  // PRODUCTION
  var jsTasks_prod = gulp.series( copyScripTask_prod);

  var allTheTasks_prod = gulp.series(
    deleteHTMLTask,
    copyHTMLTask_prod,
    copyScripTask_prod,
    copyMediaTask,
    copyCSSLibTask_dev_prod
  );

  //COMPLETE TASKS IN WATCHERS

  gulp.task('watch_dev',function(){
    gulp.watch(
      ['src/**/*.scss'],
      sassTasks
    );
    gulp.watch(
      ['src/**/*.html'],
      htmlTasks
    );
    gulp.watch(
      ['src/**/*.js'],
      jsTasks
    );
  });

  // PRODUCTION
  gulp.task('watch_prod',function(){
    gulp.watch(
      ['src/**/*.scss'],
      sassTasks
    );
    gulp.watch(
      ['src/**/*.html'],
      htmlTasks
    );
    gulp.watch(
      ['src/**/*.js'],
      jsTasks_prod
    );
  });

  //EXPORTING TASKS
  exports.build_prod = gulp.series(deleteHTMLAll, deleteHTMLTask, copyHTMLTask_prod, copyScripTask_prod, copyMediaTask, sassTasks);
  exports.publish = gulp.series(deleteHTMLAll, deleteHTMLTask, copyHTMLTask_prod, copyScripTask_prod, copyMediaTask, sassTasks, publishToS3);
  exports.build_dev = gulp.series(deleteHTMLAll, deleteHTMLTask, copyHTMLTask_dev, copyScripTask_dev, copyMediaTask, sassTasks, cssTasks);
