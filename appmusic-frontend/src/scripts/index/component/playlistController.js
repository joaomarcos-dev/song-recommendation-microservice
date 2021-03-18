//CONTROLLERS

app.controller("playlistController", ['$scope', '$log', 'playlistService', '$mdDialog', playlistControllerFunction]);
function playlistControllerFunction ($scope, $log, playlistService, $mdDialog){

  $log.debug(`playlistControllerFunction()`);

  $scope.resultsFetched = true;

  $scope.getPlaylist = function functionName(cityName) {

    $scope.resultsFetched = false;
    $log.debug(`Progress circular bar disabled: ${$scope.resultsFetched}`)

    $scope.playlist = [];
    $log.debug(`Cleaning playlist`);

    $log.debug(`$scope.getPlaylist(${cityName})`);

    console.log(playlistService);

    playlistService.getPlaylistRecommendationByCityName(cityName, sucessCb, failureCb);

  };

  const sucessCb = function (playlist) {

    $scope.resultsFetched = true;
    $log.debug(`Progress circular bar disabled: ${$scope.resultsFetched}`);

    $log.debug(`sucessCb(playlist)`);

    $scope.playlist = playlist;
  }

  const failureCb = function (response) {

    $scope.resultsFetched = true;
    $log.debug(`Progress circular bar disabled: ${$scope.resultsFetched}`)

    showErrorDialog ();
    $log.debug(`Showing error dialog`);


    $log.debug(`failureCb(response)`);
  }

  const showErrorDialog =   function showErrorDialog (ev) {
    $mdDialog.show(
      $mdDialog.alert()
        .parent(angular.element(document.querySelector('#popupContainer')))
        .clickOutsideToClose(true)
        .title('Error')
        .textContent('We didn\'t find any results for the seach. Try using other terms:/')
        .ok('Got it!')
        .targetEvent(ev)
    );
  };



}
