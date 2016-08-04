
(function() {

  'use strict';

  var CTRLR = 'AcervoUserCtrlr';

  var PATH = 'acervo/:id';
  var ROOT_PATH = '/user/acervo';

  var app = angular.module('biblioteca');

  app.crudCtrlr(CTRLR, PATH, ROOT_PATH);

  app.config(function($routeProvider) {

    $routeProvider.
      when(ROOT_PATH, {
        templateUrl : 'views' + ROOT_PATH + '/'
      }).
      when(ROOT_PATH + '/:id', {
        templateUrl : 'views' + ROOT_PATH + '/view.html'
      });
  });

})();

