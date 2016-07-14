(function() {
  'use strict';

  var app = angular.module('biblioteca');

  app.crudCtrlr = function(CTRLR, PATH, ROOT_PATH) {

    app.config(function($routeProvider) {
      $routeProvider.when(ROOT_PATH + '/:id', {
        templateUrl : ROOT_PATH + '/edit.html',
        controller : CTRLR
      });
    });

    app.controller(CTRLR, function($scope, $http, $routeParams, $location) {
      var self = this;

      $scope.rootPath = ROOT_PATH;
      $scope.params = $routeParams;

      $scope.data = {};

      $scope.itens = [];

      $scope.cancel = function() {

        $scope.data = {};
        $location.path(ROOT_PATH);
      };

      $scope.edit = function(id) {

        if (id > 0) {

          $http({
            method : 'GET',
            url : PATH + '/' + id
          }).then(function(r) {

            $scope.data = r.data;
          });
        }
      };

      $scope.remove = function(id) {

        if (!confirm('Tem certeza?'))
          return;

        $http({
          method : 'DELETE',
          url : PATH + '/' + id,
          data : {}
        }).then(function() {

          $scope.load();
        });
      };

      $scope.save = function() {

        var path = PATH;
        var method = 'POST';
        var data = $scope.data;

        if (data.id != null) {

          method = 'PUT';
          path = path + '/' + data.id;
        }

        $http({
          method : method,
          url : path,
          data : data
        }).then(function(r) {

          $scope.data = {};

          $scope.cancel();
        });
      };

      $scope.load = function(r) {

        $http({
          method : 'GET',
          url : PATH
        }).then(function(r) {

          $scope.itens = r.data;
        });
      };
    });
  };
})();
