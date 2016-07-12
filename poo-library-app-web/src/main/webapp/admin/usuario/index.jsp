<section class="user-mgmt" ng-controller="AdminCtrlr" ng-init="load()">

  <table class="table table-striped table-hover">
    <caption>
      Usuários
      | <a class="btn btn-xs btn-default" href="#/admin/usuario/0">
        <span class="glyphicon glyphicon-certificate"></span> Novo
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
        <td>
          <div class="btn-group" role="group" aria-label="...">
            <a class="btn btn-xs btn-default" href="\#{{rootPath}}" ng-click="delete(user.id)">
              <span class="glyphicon glyphicon-remove"></span> Deletar
            </a>
            <a class="btn btn-xs btn-primary" href="\#{{rootPath}}/{{user.id}}">
              <span class="glyphicon glyphicon-pencil"></span> Editar
            </a>
          </div>
        </td>
      </tr>
    </tbody>
  </table>
</section>
