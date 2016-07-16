(function() {
  'use strict';

  var CTRLR = 'BibliotecaCtrlr';
  var PATH = 'api/biblioteca/:id';
  var ROOT_PATH = '/admin/biblioteca';

  var app = angular.module('biblioteca');

  app.crudCtrlr(CTRLR, PATH, ROOT_PATH);

})();
