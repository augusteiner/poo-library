
(function() {

  'use strict';

  var app = angular.module('biblioteca');

  app.crudCtrlr = function(CTRLR_PREFIX, REL_PATH, ROOT_PATH, TPL_ROOT_PATH) {

    var __PREFIX_PATH = 'api';
    var PATH = __PREFIX_PATH + '/' + REL_PATH;
    // console.log('Novo ctrlr de CRUD: ' + CTRLR_PREFIX + ' @ ' + ROOT_PATH);

    if (TPL_ROOT_PATH == null)
      TPL_ROOT_PATH = ROOT_PATH;

    app.config(function($routeProvider) {
      $routeProvider.
        when(ROOT_PATH, {
          templateUrl : TPL_ROOT_PATH + '/',
          controller : CTRLR_PREFIX
        }).
        when(ROOT_PATH + '/:id', {
          templateUrl : TPL_ROOT_PATH + '/edit.html',
          controller : CTRLR_PREFIX
        });
    });

    app.controller(CTRLR_PREFIX, function($scope, $routeParams, $location, $resource) {

      console.log(CTRLR_PREFIX);

      var self = this;
      var errorHandler = function(response) {

        console.log(response);

        if (response.data && 
            response.data.message) {

          alert(['OPS!', response.data.message].join('\n\n'));
        }
      };

      var $params = {};
      var $paramsWithId = { id: '@id' };
      var $inter = { responseError: errorHandler };

      for (var key in $routeParams) {
        if (key == 'id')
          continue;

        $params[key] = '@'+key;
      }

      $paramsWithId = angular.extend({}, $paramsWithId, $params);

      var $rest = $resource(PATH, null, {
        get: { method: 'GET', params: $paramsWithId, interceptor: $inter },
        update: { method: 'PUT', params: $paramsWithId, interceptor: $inter },
        save: { method: 'POST', params: $params, interceptor: $inter },
        remove: { method: 'DELETE', params: $paramsWithId, interceptor: $inter },
        search: {
          method: 'GET',
          params: $params,
          interceptor: $inter,
          url: __PREFIX_PATH + '/search/' + REL_PATH,
          isArray: true
        }
      });

      console.log($rest);

      console.log('Init of ' + CTRLR_PREFIX + ' @ ' + ROOT_PATH);

      $scope.rootPath = ROOT_PATH;
      $scope.params = $routeParams;
      $scope.url = $location.url();

      console.log(ROOT_PATH);

      $scope.data = {};

      for (var p in $scope.params) {

        $scope.data[p] = $scope.params[p];
      }

      $scope.itens = [];

      $scope.search = function(term) {

        var params = angular.extend(
          { term: term },
          $scope.params);

        console.log('Loading: ' + PATH);

        $rest.search(params).$promise.then(function(r) {

          console.log(r);

          $scope.itens = r;
        });
      };

      $scope.cancel = function(url) {

        $scope.data = {};

        $location.path(url);
      };

      $scope.edit = function(paramId) {

        if (paramId > 0) {

          console.log("Getting resource", $scope.params);

          $rest.get($scope.params).$promise.then(function(r) {

            //console.log(r);

            $scope.data = r;
          });
        }
      };

      $scope.remove = function(paramId) {

        if (!confirm('Tem certeza?'))
          return;

        var params = angular.extend(
            { id: paramId },
            $scope.params);

        $rest.remove(params).$promise.then(function() {

          $scope.load();
        });
      };

      $scope.save = function(url) {

        var data = $scope.data;
        var $promise;

        console.log(PATH);
        console.log(data);
        console.log($.extend({}, data, $scope.params));

        if (data.id > 0) {

          $promise = $rest.update(data).$promise;
  
        } else {

          data.id = 0;

          $promise = $rest.save(data).$promise;
        }

        $promise.then(function(r) {

          $scope.cancel(url);
        });
      };

      $scope.load = function() {

        console.log('Loading: ' + PATH);

        $rest.query($scope.params).$promise.then(function(r) {

          $scope.itens = r;
        });
      };
    });
  };

})();

