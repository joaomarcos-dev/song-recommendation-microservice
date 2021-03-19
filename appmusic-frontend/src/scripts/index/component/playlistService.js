app.service('playlistService',  playlistServiceFunction);
function playlistServiceFunction($log, $http, $window) {
  $log.debug(`playlistServiceFunction(...)`);

  const resource = "playlist";

  this.getPlaylistRecommendationByCityName = function(cityName, sucessCb, failureCb){

    $log.debug(`getPlaylistRecommendationByCityName(${cityName})`);

    const endpointUrl = `${$window.__env.apiUrl}/${resource}`;

    $log.info(`requesting ${endpointUrl}`);

    const httpConfig = {};
    httpConfig.params = {
      "cityName" : cityName
    }

    $http.get(endpointUrl, httpConfig).
    then(function(response) {
        $log.debug(response);
        sucessCb(response.data);
      }, function(response) {
        $log.debug(response);
        failureCb(response);
    });
  }

}
