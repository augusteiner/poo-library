<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="UTF-8">
<script src="assets/jquery/dist/jquery.min.js" type="text/javascript"></script>
<script src="assets/jquery-serialize-object/dist/jquery.serialize-object.min.js" type="text/javascript"></script>
<script src="assets/angular/angular.min.js" type="text/javascript"></script>
<script type="text/javascript">
  'use strict';

//     $('.usuario-create-form').submit(function(e) {

//       e.preventDefault();

//       $.ajax({
//         url: 'api/usuario',
//         method: 'post',
//         data: JSON.stringify($(this).serializeObject()),
//         contentType: 'application/json'
//       });
//     });

    var app = angular.module('app', []);

    app.controller('UserCtrlr', function($scope) {
      var self = this;

      $scope.users = [{nome: "augusteiner"}];

      $scope.log = function(r) {

        console.log(r);
      };
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
  <section class="user-mgmt" ng-controller="UserCtrlr" >
    <table class="data-table">
      <caption>Usuários</caption>
      <thead>
        <tr>
          <th>
              Nome
          </th>
        </tr>
      </thead>
      <tfoot>
      </tfoot>
      <tbody>
        <tr ng-repeat="user in users">
          <td>{{user.nome}}</td>
        </tr>
      </tbody>
    </table>
  </section>
  <p>
    <a href="api/usuario">
      Lista de Usuários</a>
      <form class="usuario-create-form" action="api/usuario" method="post">
        <div>
          <label>
            Nome:
            <input name="nome" type="text">
          </label>
        </div>
        <div>
          <label>
            CPF:
            <input name="cpf" type="text">
          </label>
        </div>
        <button class="usuario-create" type="submit">
          Salvar
        </button>
      </form>
</body>
</html>
