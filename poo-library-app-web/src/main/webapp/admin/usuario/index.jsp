<section class="user-mgmt" ng-controller="AdminCtrlr" ng-init="load()">

  <table class="table table-striped table-hover">
    <caption>
      Usuários
      | <a class="btn btn-xs btn-primary" href="#/admin/usuario/new">
        <span class="glyphicon glyphicon-certificate"></span> Novo
      </a>
    </caption>
    <thead>
      <tr>
        <th>Usuário</th>
        <th style="width: 12rem;">Ações</th>
      </tr>
    </thead>
    <tfoot>
    </tfoot>
    <tbody>
      <tr ng-repeat="user in users">
        <td>
          <div>
            &#35;{{user.id}} - <strong>{{user.nome}}</strong>
          </div>
          <div>
            <small>
              {{user.cpfFormatado}} :: <em>{{user.endereco}}</em>
            </small>
          </div>
        </td>
        <td>
          <div class="btn-group">
            <a class="btn btn-sm btn-primary" href="\#{{rootPath}}/{{user.id}}">
              <span class="glyphicon glyphicon-pencil"></span> Editar
            </a>
            <a data-target="#" class="btn btn-sm btn-default dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
              <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
              <li>
                <a class="" href="\#{{rootPath}}" ng-click="delete(user.id)">
                  <span class="glyphicon glyphicon-remove"></span> Deletar
                </a>
              </li>
              <li class="divider"></li>
              <li><a class="" href="#">Ver detalhes</a></li>
            </ul>
          </div>
        </td>
      </tr>
    </tbody>
  </table>
</section>
