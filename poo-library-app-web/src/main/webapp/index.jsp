<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author"
  content="José Nascimento &lt;joseaugustodearaujonascimento@gmail.com&gt;">
<title>RESTful Web Api!</title>
<script src="assets/angular/angular.min.js" type="text/javascript"></script>
<script type="text/javascript">
  var __f = function() {
    'use strict';

    var app = angular.module('app', []);

    app.controller('UserCtrlr', function($scope, $http) {
      var self = this;

      $scope.PATH = 'api/usuario';

      $scope.data = {};

      $scope.users = [];

      $scope.cancel = function() {

        $scope.data = {};
      };

      $scope.edit = function(id) {

        $http({
          method: 'GET',
          url: $scope.PATH + '/' + id
        }).then(function(r){

          $scope.data = r.data;
        });
      };

      $scope.delete = function(id) {

        if (!confirm('Tem certeza?'))
          return;

        $http({
          method : 'DELETE',
          url : $scope.PATH + '/' + id,
          data : {}
        }).then(function() {

          $scope.load();
        });
      };

      $scope.save = function() {

        var path = $scope.PATH;
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
        }).then(function() {

          $scope.load();
        });
      };

      $scope.load = function(r) {

        $http({
          method : 'GET',
          url : $scope.PATH
        }).then(function(r) {

          $scope.users = r.data;
        });
      };

      $scope.load();
    });

  }();
</script>
<style>
.data-table {
  width: 100%;
}

.data-table tbody tr:hover {
  background-color: rgba(220, 220, 220, 0.5) !important;
}

.data-table tbody tr:nth-child(even) {
  background-color: #dcdcdc;
}

.data-table th {
  background-color: lightgray;
}

input {
  width: 100%;
}
</style>
</head>
<body>
  <h2>RESTful Web {{'Api' + '!'}}</h2>
  <section class="user-mgmt" ng-controller="UserCtrlr">
    <table class="data-table">
      <caption>Usuários</caption>
      <thead>
        <tr>
          <th>Nome</th>
          <th>CPF</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tfoot>
      </tfoot>
      <tbody>
        <tr ng-repeat="user in users">
          <td>\#{{user.id}}-{{user.nome}}</td>
          <td>{{user.cpf}}</td>
          <td>
            <button ng-click="delete(user.id)">delete</button>
            <button ng-click="edit(user.id)">edit</button>
          </td>
        </tr>
      </tbody>
    </table>
    <p>
      <a href="api/usuario"> Lista de Usuários</a>
    <form action="javascript:void(0);" method="post" ng-submit="save()">
      <input type="hidden" name="id" ng-model="data.id">
      <div>
        <label>
          Nome:
          <input name="nome" type="text" ng-model="data.nome" required>
        </label>
      </div>
      <div>
        <label>
          CPF:
          <input name="cpf" type="text" maxlength="11" ng-model="data.cpf" required>
        </label>
      </div>
      <button type="submit">Salvar</button>
      <button type="reset" ng-click="cancel()">Limpar</button>
    </form>
  </section>
</body>
</html>
