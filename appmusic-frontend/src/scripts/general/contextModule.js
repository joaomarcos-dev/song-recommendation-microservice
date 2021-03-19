var appContextModule =  angular.module("contextModule", ["ngCookies", "ngStorage"]);


appContextModule.service('serviceName', function ndc($localStorage) {
  const userId = $localStorage.userId;
  var variables = {
    ndcMercadoPagoPubKey: '',
    userId: userId
  };
  return variables;
});
