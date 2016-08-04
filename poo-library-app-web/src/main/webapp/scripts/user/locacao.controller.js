
(function() {

  'use strict';

  var CTRLR = 'LocacaoUserCtrlr';
  var BASE_CTRLR = 'Base' + CTRLR;

  var PATH = 'usuario/:usuarioId/locacao/:id';
  var PARENT_PATH = '/user/locacao';
  var ROOT_PATH = PARENT_PATH + '/:usuarioId/locacao';
  var TPL_ROOT_PATH = 'views' + PARENT_PATH;

  var app = angular.module('biblioteca');

  app.crudCtrlr(BASE_CTRLR, PATH, ROOT_PATH, TPL_ROOT_PATH);

  app.controller(CTRLR, function($scope, $routeParams, $controller) {

    console.log($scope);

    $controller(BASE_CTRLR, { $scope : $scope, $routeParams: { usuarioId: $scope.$parent.params.id } });

    $scope.locar = function(userId, acervoId) {

      var $rest = $scope.$rest;

      var $promise = $rest.save({
        usuarioId: userId,
        itemAcervoId: acervoId });

      // $rest.put();
    };

  });

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
