<!DOCTYPE html>
<html ng-app="biblioteca">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta name="author" content="José Nascimento &lt;joseaugustodearaujonascimento@gmail.com&gt;">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>:: Sistema de Gerenciamento de Bibliotecas ::</title>

<link href="assets/bootswatch/flatly/bootstrap.min.css" rel="stylesheet" type="text/css">

<link href="styles/main.css" rel="stylesheet" type="text/css">

<script src="assets/jquery/dist/jquery.min.js" type="text/javascript"></script>
<script src="assets/bootstrap/dist/js/bootstrap.min.js"></script>

<script src="assets/angular/angular.min.js" type="text/javascript"></script>
<script src="assets/angular-route/angular-route.min.js" type="text/javascript"></script>

<script src="scripts/admin.controller.js" type="text/javascript"></script>

</head>
<body>
  <div class="container">
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span> <span
              class="icon-bar"></span> <span class="icon-bar"></span> <span
              class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">
            <span class="glyphicon glyphicon-book"></span> SGLiB
          </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Biblioteca <span
                  class="caret"></span>
              </a>
              <ul class="dropdown-menu" role="menu">
                <li>
                  <a href="javascript:void(0);">
                    <span class="glyphicon glyphicon-th-list"></span> Consultar Acervo</a>
                </li>
                <li><a href="javascript:void(0);">Ver usuários</a></li>
                <li class="divider"></li>
                <li><a href="javascript:void(0);">Separated link</a></li>
                <li class="divider"></li>
                <li><a href="javascript:void(0);">One more separated link</a></li>
              </ul>
            </li>
            <li class="">
              <a href="javascript:void(0);">Empréstimos</a>
            </li>
            <li>
              <a href="javascript:void(0);">Reservas</a>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
              <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                <span class="glyphicon glyphicon-user"></span> Primeiro Nome <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="javascript:void(0);">Ver reservas</a></li>
                <li><a href="javascript:void(0);">Ver usuários</a></li>
                <li class="divider"></li>
                <li>
                  <a href="javascript:void(0);">
                    <span class="glyphicon glyphicon-cog"></span> Configurações</a>
                </li>
                <li class="divider"></li>
                <li>
                  <a href="javascript:void(0);">
                    <span class="glyphicon glyphicon-log-out"></span> Sair</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div ng-view></div>
  </div>
</body>
</html>
