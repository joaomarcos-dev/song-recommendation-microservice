/**
 *
  * @requires ../../../node_modules/angular/angular.js
  * @requires ../../../node_modules/angular-aria/angular-aria.js
  * @requires ../../../node_modules/angular-animate/angular-animate.js
  * @requires ../../../node_modules/angular-messages/angular-messages.js
  * @requires ../../../node_modules/angular-material/angular-material.js
 */

var app = angular.module("playlistModule", ['ngMaterial']);

app.config(["$logProvider", function($logProvider) {
  $logProvider.debugEnabled(true);
}]);
