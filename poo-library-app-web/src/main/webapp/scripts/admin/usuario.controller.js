
(function() {

  'use strict';

  var CTRLR = 'UserCtrlr';
  var PATH = 'usuario/:id';
  var ROOT_PATH = '/admin/usuario';
  var TPL_ROOT_PATH = 'views' + ROOT_PATH;

  var app = angular.module('biblioteca');

  app.crudCtrlr(CTRLR, PATH, ROOT_PATH, TPL_ROOT_PATH);

})();

