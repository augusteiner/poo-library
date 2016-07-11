<section class="user-mgmt" ng-controller="AdminCtrlr" ng-init="load()">

  <table class="table table-striped table-hover">
    <caption>
      Usuários | <a href="#/admin/usuario/0"> <span
        class="glyphicon glyphicon-certificate"></span> Novo
      </a>
    </caption>
    <thead>
      <tr>
        <th>Nome</th>
        <th>CPF</th>
        <th>Tipo</th>
        <th>Endereço</th>
        <th>Ações</th>
      </tr>
    </thead>
    <tfoot>
    </tfoot>
    <tbody>
      <tr ng-repeat="user in users">
        <td># {{user.id}} - {{user.nome}}</td>
        <td>{{user.cpf}}</td>
        <td>{{user.tipo}}</td>
        <td>{{user.endereco}}</td>
        <td><a class="" href="\#{{rootPath}}"
          ng-click="delete(user.id)"> <span
            class="glyphicon glyphicon-remove"></span> Deletar
        </a> | <a class="" href="\#{{rootPath}}/{{user.id}}"> <span
            class="glyphicon glyphicon-pencil"></span> Editar
        </a></td>
      </tr>
    </tbody>
  </table>
</section>