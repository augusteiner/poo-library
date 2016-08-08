
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

    $controller(BASE_CTRLR, { $scope : $scope, $routeParams: { usuarioId: $scope.$parent.params.id } });
    var $rest = $scope.$rest;

    $scope.locar = function(userId, acervoId) {

      var $promise = $rest.save({
        usuarioId: userId,
        itemAcervoId: acervoId });

    };

    $scope.devolver = function(locacaoId) {

      var mainCtrlr = $scope.$parent.$parent;

      //console.log('Devolvendo locação #', locacaoId);

      $rest.update({

        usuarioId: mainCtrlr.user.credentials.id,
        id: locacaoId,
        status: 'ENCERRADA'

      }, function() {

        console.log('Devolvido com sucesso!');

        $scope.load();
      });
    }
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
