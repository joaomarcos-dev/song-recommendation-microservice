//CONTROLLERS

app.controller("playlistController", ['$scope', '$log', 'playlistService', '$mdDialog', 'playlistApiErrorService', playlistControllerFunction]);
function playlistControllerFunction ($scope, $log, playlistService, $mdDialog, playlistApiErrorService){

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

    $log.debug(`Showing error dialog`);

    showErrorDialog (`error_${response.status}`);

    $log.debug(`failureCb(response)`);
  }

  const showErrorDialog =   function showErrorDialog (errorName) {
    $mdDialog.show(playlistApiErrorService.getError(errorName));

  };



}
