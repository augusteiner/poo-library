<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="José Nascimento &lt;joseaugustodearaujonascimento@gmail.com&gt;">
<script src="assets/angular/angular.min.js" type="text/javascript"></script>
<script type="text/javascript">
  var __f = function() {
    'use strict';

    var app = angular.module('app', []);

    app.controller('UserCtrlr', function($scope, $http) {
      var self = this;

      $scope.data = {};

      $scope.users = [ {
        nome : 'augusteiner',
        cpf : '01122244497'
      } ];

      $scope.delete = function(id) {

        if (!confirm('Tem certeza?'))
          return;

        $http({
          method : 'DELETE',
          url : 'api/usuario/' + id,
          data : {}
        }).then(function() {

          $scope.load();
        });
      };

      $scope.save = function() {

        $http({
          method : 'POST',
          url : 'api/usuario',
          data : $scope.data
        }).then(function(r) {

          $scope.data = {};
        }).then(function() {

          $scope.load();
        });
      };

      $scope.load = function(r) {

        $http({
          method : 'GET',
          url : 'api/usuario'
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
          <td>
            \#{{user.id}}-{{user.nome}}</td>
          <td>
            {{user.cpf}}</td>
          <td>
            <button ng-click="delete(user.id)">delete</button>
          </td>
        </tr>
      </tbody>
    </table>
    <p>
      <a href="api/usuario"> Lista de Usuários</a>
    <form class="usuario-create-form" action="javascript:void(0);"
      method="post" ng-submit="save()">
      <div>
        <label> Nome: <input name="nome" type="text"
          ng-model="data.nome" minlength="10" required>
        </label>
      </div>
      <div>
        <label> CPF: <input name="cpf" type="number"
          ng-model="data.cpf" maxlength="11" minlength="11" required>
        </label>
      </div>
      <button class="usuario-create" type="submit">Salvar</button>
    </form>
  </section>
</body>
</html>
