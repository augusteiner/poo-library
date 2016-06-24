<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <script>
        $(function() {

            $.get(
                'api/usuario',
                function(r) {
                    console.log(r);
                });
        });
    </script>
</head>
<body>
    <h2>RESTful Web Apí</h2>
    <p><a href="api/usuario">Lista de Usuários</a>
</body>
</html>
