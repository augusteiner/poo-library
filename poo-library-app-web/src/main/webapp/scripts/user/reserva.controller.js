
(function() {

  'use strict';

  var CTRLR = 'ReservaUserCtrlr';
  var PATH = 'usuario/:usuarioId/reserva/:id';
  var PARENT_PATH = '/user/reserva';
  var ROOT_PATH = PARENT_PATH + '/:usuarioId/reserva';
  var TPL_ROOT_PATH = 'views' + PARENT_PATH;

  var app = angular.module('biblioteca');

  app.crudCtrlr(CTRLR, PATH, ROOT_PATH, TPL_ROOT_PATH);

  app.config(function($routeProvider) {

    $routeProvider.
      when(ROOT_PATH, {
        templateUrl : TPL_ROOT_PATH + '/'
      }).
      when(ROOT_PATH + '/:id', {
        templateUrl : TPL_ROOT_PATH + '/view.html'
      });
  });

})();
