(function (window) {
  window.__env = window.__env || {};

  // API url
  window.__env.apiUrl = '${apiUrl}';//parsed by Gulp
  window.__env.frontendHostname = '${frontendHostname}';//parsed by Gulp

  // Whether or not to enable debug mode
  // Setting this to false will disable console output
  window.__env.enableDebug = true;

}(this));
