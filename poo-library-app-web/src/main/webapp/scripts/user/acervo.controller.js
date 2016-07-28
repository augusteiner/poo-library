(function() {

  'use strict';

  var CTRLR = 'AcervoCtrlr';
  var PATH = 'api/acervo/:id';
  var ROOT_PATH = '/user/acervo';

  var app = angular.module('biblioteca');

  app.crudCtrlr(CTRLR, PATH, ROOT_PATH);

  app.config(function($routeProvider) {

    $routeProvider.
      when(ROOT_PATH, {
        templateUrl : ROOT_PATH + '/index.html'
      }).
      when(ROOT_PATH + '/:id', {
        templateUrl : ROOT_PATH + '/view.html'
      });
  });

})();
