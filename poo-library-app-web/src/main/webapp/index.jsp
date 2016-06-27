<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="UTF-8">
<script src="assets/jquery/dist/jquery.min.js" type="text/javascript"></script>
<script
  src="assets/jquery-serialize-object/dist/jquery.serialize-object.min.js"
  type="text/javascript"></script>
<script src="assets/angular/angular.min.js" type="text/javascript"></script>
<script type="text/javascript">
  'use strict';

  $(function() {
    $('.usuario-create-form').submit(function(e) {

      e.preventDefault();
    });
  });

  var app = angular.module('app', []);

  app.controller('UserCtrlr', function($scope, $element, $http) {
    var self = this;

    $scope.data = {};

    $scope.users = [ {
      nome : 'augusteiner',
      cpf : '01122244497'
    } ];

    $scope.save = function() {

      $http({
        method : 'POST',
        url : 'api/usuario',
        data : $scope.data
      }).then(function(r) {

        $scope.data = {};
      });

      $scope.load();
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
</script>
<style>
.data-table {
  width: 100%;
}

.data-table th {
  background-color: lightgray;
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
        </tr>
      </thead>
      <tfoot>
      </tfoot>
      <tbody>
        <tr ng-repeat="user in users">
          <td>{{user.nome}}</td>
          <td>{{user.cpf}}</td>
        </tr>
      </tbody>
    </table>
    <p>
      <a href="api/usuario"> Lista de Usuários</a>
    <form class="usuario-create-form" action="api/usuario" method="post"
      ng-submit="save()">
      <div>
        <label> Nome: <input name="nome" type="text"
          ng-model="data.nome">
        </label>
      </div>
      <div>
        <label> CPF: <input name="cpf" type="text"
          ng-model="data.cpf">
        </label>
      </div>
      <button class="usuario-create" type="submit">Salvar</button>
    </form>
  </section>
</body>
</html>
