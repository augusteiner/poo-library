(function() {
  'use strict';

  var CTRLR = 'UsuarioCtrlr';
  var PATH = 'api/usuario/:id';
  var ROOT_PATH = '/admin/usuario';

  var app = angular.module('biblioteca');

  app.crudCtrlr(CTRLR, PATH, ROOT_PATH);

})();
