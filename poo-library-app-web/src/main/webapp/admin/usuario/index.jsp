<section class="user-mgmt" ng-controller="AdminCtrlr" ng-init="load()">

  <a class="btn btn-default" href="#/admin/usuario/0">Novo</a>

  <table class="table table-striped table-hover ">
    <caption>Usuários</caption>
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
        <td>
          <div>
            <a class="btn btn-danger" href="\#{{rootPath}}"
              ng-click="delete(user.id)"> <span
              class="glyphicon glyphicon-remove"></span>
            </a> <a class="btn btn-primary"
              href="\#{{rootPath}}/{{user.id}}"> <span
              class="glyphicon glyphicon-edit"></span>
            </a>
          </div>
        </td>
      </tr>
    </tbody>
  </table>
</section>