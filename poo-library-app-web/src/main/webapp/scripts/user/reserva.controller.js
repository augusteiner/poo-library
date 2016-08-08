
(function() {

  'use strict';

  var CTRLR = 'ReservaUserCtrlr';
  var BASE_CTRLR = 'Base' + CTRLR;

  var PATH = 'usuario/:usuarioId/reserva/:id';
  var PARENT_PATH = '/user/reserva';
  var ROOT_PATH = PARENT_PATH + '/:usuarioId/reserva';
  var TPL_ROOT_PATH = 'views' + PARENT_PATH;

  var app = angular.module('biblioteca');

  app.crudCtrlr(BASE_CTRLR, PATH, ROOT_PATH, TPL_ROOT_PATH);

  app.controller(CTRLR, function($scope, $routeParams, $controller) {

    $controller(BASE_CTRLR, { $scope : $scope, $routeParams: { usuarioId: $scope.$parent.params.id } });
    var $rest = $scope.$rest;

    $scope.reservar = function(userId, acervoId) {

      var $promise = $rest.save({
        usuarioId: userId,
        itemAcervoId: acervoId });

    };

    $scope.cancelar = function(reservaId) {

      var mainCtrlr = $scope.$parent.$parent;

      //console.log('Devolvendo locação #', reservaId);

      $rest.update({

        usuarioId: mainCtrlr.user.credentials.id,
        id: reservaId,
        status: 'CANCELADA'

      }, function() {

        console.log('Cancelada com sucesso!');

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
