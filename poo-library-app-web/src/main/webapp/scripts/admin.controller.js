(function() {
  'use strict';

  var app = angular.module('biblioteca', ['ngRoute']);

  app.config(['$httpProvider', function($httpProvider) {
    // initialize get if not there
    if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};
    }

    // Answer edited to include suggestions from comments
    // because previous version of code introduced browser-related errors

    // disable IE ajax request caching
    $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
    // extra
    $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
  }]);

  app.config(function ($routeProvider) {
    $routeProvider.
      when('/admin/usuario', {
          templateUrl: '/admin/usuario/',
          controller: 'AdminCtrlr'
      }).
      when('/admin/usuario/:id', {
          templateUrl: '/admin/usuario/edit/',
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

        $scope.cancel();
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
