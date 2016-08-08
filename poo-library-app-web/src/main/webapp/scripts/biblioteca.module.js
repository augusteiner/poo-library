
(function() {

  'use strict';

  var app = angular.module('biblioteca', ['ngRoute', 'ngResource', 'ngAnimate', 'angular-loading-bar']);

  app.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {

    cfpLoadingBarProvider.includeSpinner = false;
  }]);

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

  var routeProvider;

  app.config(['$routeProvider', function($routeProvider) {

    routeProvider = $routeProvider
      .when('/login', {
        templateUrl: 'views/login/'
      })
      .otherwise({
        redirectTo: '/login'
      });
  }]);

  app.controller('MainCtrlr', function($route, $scope, $location, $resource) {

    $scope.settings = {
      theme : {
        dark: false }
    };

    $scope.user = {
      credentials: null
    };
    $scope.credentials = {};

    var credentials = JSON.parse(sessionStorage.getItem("credentials"));

    if (credentials != null) {

      $scope.user.credentials = credentials;
    }

    var displayMenu = function() {

      var credentials = $scope.user.credentials;

      if (credentials == null ||
          credentials.login == null)
        return;

      if (credentials.login == 'admin') {

        $scope.menu = 'views/admin/menu/';
        $location.path('/admin');

      } else {

        $scope.menu = 'views/user/menu/';
        $location.path('/user');
      }
    };

    $scope.logout = function() {

      sessionStorage.setItem("credentials", JSON.stringify(null));

      $scope.user.credentials = null;
      $scope.menu = null;

      $location.path('/');
    };

    $scope.login = function() {

      routeProvider
        .when('/user', {
          templateUrl: 'views/user/',
          controller: 'UserCtrlr'
        })
        .when('/admin', {
          templateUrl: 'views/admin/',
          controller: 'AdminCtrlr'
        });

      $scope.user = {};

      // TODO Realizar via ajax
      (function(r) {

        //console.log(r);

        $scope.user.credentials = r;
        sessionStorage.setItem("credentials", JSON.stringify($scope.user.credentials));

        $scope.credentials = {};

        displayMenu();

      })(angular.extend({ id: 1 }, $scope.credentials));
    };

    displayMenu();
  });

})();

