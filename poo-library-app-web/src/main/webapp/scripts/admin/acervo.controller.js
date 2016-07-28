(function() {

  'use strict';

  var CTRLR = 'AcervoCtrlr';
  var PATH = 'api/biblioteca/:bibliotecaId/acervo/:id';
  var PARENT_PATH = '/admin/biblioteca';
  var ROOT_PATH = PARENT_PATH + '/:bibliotecaId/acervo';
  var TPL_ROOT_PATH = 'views' + PARENT_PATH + '/acervo';

  var app = angular.module('biblioteca');

  app.crudCtrlr(CTRLR, PATH, ROOT_PATH, TPL_ROOT_PATH);

  app.config(function($routeProvider) {

    $routeProvider.
      when(ROOT_PATH, {
        templateUrl : TPL_ROOT_PATH + '/'
      }).
      when(ROOT_PATH + '/:id', {
        templateUrl : TPL_ROOT_PATH + '/edit.html'
      });
  });

})();
