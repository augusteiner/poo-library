<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="assets/jquery/dist/jquery.min.js"></script>
<script src="assets/jquery-serialize-object/dist/jquery.serialize-object.min.js"></script>
<script>
  $(function() {

    $('.usuario-create-form').submit(function(e) {

      e.preventDefault();

      $.ajax({
        url: 'api/usuario',
        method: 'post',
        data: JSON.stringify($(this).serializeObject()),
        contentType: 'application/json'
      });
    });

//     $.get('api/usuario', function(r) {

//       console.log(r);
//     });
  });
</script>
</head>
<body>
  <h2>RESTful Web Api</h2>
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
