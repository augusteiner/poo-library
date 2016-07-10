(function() {
  'use strict';

  var app = angular.module('biblioteca', ['ngRoute']);

  app.config(function ($routeProvider) {
    $routeProvider.
      when('/admin/usuario', {
          templateUrl: '/admin/usuario/',
          controller: 'AdminCtrlr'
      }).
      when('/admin/usuario/:id', {
          templateUrl: '/admin/usuario/edit.jsp',
          controller: 'AdminCtrlr'
      }).
      otherwise({
          redirectTo: '/admin/usuario' 
      });
  });

  app.controller('AdminCtrlr', function($scope, $http, $routeParams, $location) {
    var self = this;
    var PATH = 'api/usuario';

    $scope.rootPath = '/admin/usuario';
    $scope.params = $routeParams;

    $scope.data = {};

    $scope.users = [];

    $scope.cancel = function() {

      $scope.data = {};
      $location.path($scope.rootPath);
    };

    $scope.edit = function(id) {

      if (id > 0) {

        $http({
          method: 'GET',
          url: PATH + '/' + id
        }).then(function(r){

          $scope.data = r.data;
        });
      }
    };

    $scope.delete = function(id) {

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

        $scope.load();
        
        $location.path($scope.rootPath);
      });
    };

    $scope.load = function(r) {

      $http({
        method : 'GET',
        url : PATH
      }).then(function(r) {

        $scope.users = r.data;
      });
    };
  });

})();