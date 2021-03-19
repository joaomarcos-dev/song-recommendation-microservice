app.service('playlistApiErrorService',  playlistApiErrorServiceFunction);
function playlistApiErrorServiceFunction($log, $mdDialog, $window) {
  $log.debug(`playlistApiErrorServiceFunction(...)`);

  const _errorMap = {
    error_404 : {
      title:'Error 404 - Not Found',
      textContent: 'We didn\'t find any results for the seach. Try using other terms :/',
      ok:'Got it!'
    },
    error_400 : {
      title:'Error 400 - Bad Request',
      textContent: 'We could\'t process the search. Make sure you typed the parameters correctly:/',
      ok:'Ok, I will fix it!'
    },
    error_500 : {
      title:'Error 500 - Internal Error',
      textContent: 'We are having problems to process requests. We will be back in a whilw',
      ok:'Ok, I will wait and try later'
    },

  }

  this.getError = function(errorName){
    return $mdDialog.alert()
              .parent(angular.element(document.querySelector('#popupContainer')))
              .clickOutsideToClose(true)
              .title(_errorMap[errorName].title)
              .textContent(_errorMap[errorName].textContent)
              .ok(_errorMap[errorName].ok)
              .targetEvent()
  }

}
