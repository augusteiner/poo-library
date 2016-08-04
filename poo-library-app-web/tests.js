
(function(){

  var chai = require('chai');
  var chaiHttp = require('chai-http');
  var server = 'http://localhost:9090/api';

  var should = chai.should();

  chai.use(chaiHttp);

  describe('CRUD de Usuários', function() {
    var entityId = null;
    var ROOT_PATH = '/usuario';

    it('deve listar todos os usuários em ' + ROOT_PATH + ' GET', function(done) {
      chai.request(server)
        .get(ROOT_PATH)
        .end(function(err, res){

          if (err) throw err;

          res.should.have.status(200);

          done();
        });
    });

    it('deve cadastrar usuário em ' + ROOT_PATH + ' POST', function(done) {
      chai.request(server)
        .post(ROOT_PATH)
        .send({
          'nome' : 'João Maria das Lurdes',
          'login' : 'login-5a91a6e0',
          'senha' : '5a91a6e0',
          'cpf' : '00569875487',
          'endereco' : 'Av. Salgado Filho, 1001',
          'tipo': 'COMUM'})
        .end(function(err, res){

          if (err) throw err;

          res.headers.location.should.be.a('string');

          entityId = res.headers.location.replace(server, '').replace(ROOT_PATH + '/', '');
          entityId.should.be.above(0);

          res.should.have.status(201);

          done();
        });
    });

    it('deve retornar um usuário em ' + ROOT_PATH + '/{usuarioId} GET', function(done) {

      chai.request(server)
        .get(ROOT_PATH + '/' + entityId)
        .end(function(err, res){

          if (err) throw err;

          res.should.have.status(200);

          res.body.id.should.be.above(0);

          done();
        });
    });

    it('deve alterar usuário em ' + ROOT_PATH + '/{usuarioId} PUT', function(done) {

      chai.request(server)
        .put(ROOT_PATH + '/' + entityId)
        .send({
          'id' : entityId,
          'login' : 'login-5a91a6e0',
          'senha' : '5a91a6e0',
          'nome' : 'João Maria das Lurdes II',
          'cpf' : '00569875487',
          'endereco' : 'Av. Salgado Filho, 1001',
          'tipo': 'COMUM'})
        .end(function(err, res){

          if (err) throw err;

          res.should.have.status(204);

          done();
        });
    });

    it('deve deletar usuário em ' + ROOT_PATH + '/{usuarioId} DELETE', function(done) {
      chai.request(server)
        .delete(ROOT_PATH + '/' + entityId)
        .end(function(err, res){

          if (err) throw err;

          res.should.have.status(204);

          done();
        });
    });
  });

  describe('CRUD de Bibliotecas', function() {
    var entityId = null;
    var ROOT_PATH = '/biblioteca';

    it('deve listar todas as bibliotecas em ' + ROOT_PATH + ' GET', function(done) {
      chai.request(server)
        .get(ROOT_PATH)
        .end(function(err, res){

          if (err) throw err;

          res.should.have.status(200);

          done();
        });
    });

    it('deve cadastrar biblioteca em ' + ROOT_PATH + ' POST', function(done) {

      chai.request(server)
        .post(ROOT_PATH)
        .send({
          'nome' : 'iBook Store - Lagoa Nova',
          'endereco' : 'Av. Salgado Filho, 1001',
          'qteDiasValidadeReserva': 3,
          'qteDiasLocacao': 14,
          'multaDiaria': '2.55'})
        .end(function(err, res){

          if (err) throw err;

          res.headers.location.should.be.a('string');

          entityId = res.headers.location.replace(server, '').replace(ROOT_PATH + '/', '');
          entityId.should.be.above(0);

          res.should.have.status(201);

          done();
        });

    });

    it('deve retornar uma biblioteca em ' + ROOT_PATH + '/{bibliotecaId} GET', function(done) {

      chai.request(server)
        .get(ROOT_PATH + '/' + entityId)
        .end(function(err, res){

          if (err) throw err;

          res.should.have.status(200);

          res.body.id.should.be.above(0);

          done();
        });
    });

    it('deve alterar biblioteca em ' + ROOT_PATH + '/{bibliotecaId} PUT', function(done) {

      chai.request(server)
        .put(ROOT_PATH + '/' + entityId)
        .send({
          'id' : entityId,
          'nome' : 'iBook Store II - Lagoa Nova',
          'endereco' : 'Av. Salgado Filho, 1001',
          'qteDiasValidadeReserva': 3,
          'qteDiasLocacao': 14,
          'multaDiaria': '2.55'})
        .end(function(err, res){

          if (err) throw err;

          res.should.have.status(204);

          done();
        });
    });

    describe(':: Itens de Acervo', function() {
      var SUB_ROOT_PATH = '/biblioteca/{bibliotecaId}/acervo';
      var getRootPath = function() {

        // console.log('/biblioteca/' + entityId + '/acervo');

        return '/biblioteca/' + entityId + '/acervo';
      };

      var subEntityId = null;

      it('deve listar acervo em ' + SUB_ROOT_PATH + ' GET', function(done) {

        chai.request(server)
          .get(getRootPath())
          .end(function(err, res){

            if (err) throw err;

            res.should.have.status(200);

            done();
        });

      });

      it('deve cadastrar acervo em ' + SUB_ROOT_PATH + ' POST', function(done) {

        chai.request(server)
          .post(getRootPath())
          .send({
            'bibliotecaId': entityId,
            'titulo' : 'Use a cabeça! C#',
            'sinopse' : 'Aprenda a usar esta excelente linguagem',
            'autor' : 'GONÇALVES, Eleonor Alves',
            'precoLocacao' : 12.55,
            'qteDisponivel' : 20,
            'qteTotal' : 20,
            'categoria': 'LIVRO'})
          .end(function(err, res){

            if (err) throw err;

            // console.log(res.req);

            res.headers.location.should.be.a('string');

            subEntityId = res.headers.location.replace(server, '').replace(getRootPath() + '/', '');
            subEntityId.should.be.above(0);

            // console.log(subEntityId);

            res.should.have.status(201);

            done();
          });

      });

      it('deve retornar acervo em ' + SUB_ROOT_PATH + '/{acervoId} GET', function(done) {

        chai.request(server)
          .get(getRootPath() + '/' + subEntityId)
          .end(function(err, res){

            if (err) throw err;

            res.should.have.status(200);

            res.body.id.should.be.above(0);

            done();
          });
      });

      it('deve alterar acervo em ' + SUB_ROOT_PATH + ' PUT', function(done) {

        chai.request(server)
          .put(getRootPath() + '/' + subEntityId)
          .send({
            'id' : subEntityId,
            'bibliotecaId': entityId,
            'titulo' : 'Use a cabeça! C# Vol. I',
            'sinopse' : 'Aprenda a usar esta excelente linguagem',
            'autor' : 'GONÇALVES, Eleonor Alves',
            'precoLocacao' : 12.55,
            'qteDisponivel' : 20,
            'qteTotal' : 20,
            'categoria': 'LIVRO'})
          .end(function(err, res){

            if (err) throw err;

            res.should.have.status(204);

            done();
          });

      });

    it('deve deletar acervo em ' + SUB_ROOT_PATH + ' DELETE', function(done) {

      chai.request(server)
        .delete(getRootPath() + '/' + subEntityId)
        .end(function(err, res){

          if (err) throw err;

          res.should.have.status(204);

          done();
      });

    });

      it('deve deletar biblioteca em ' + ROOT_PATH + ' DELETE', function(done) {
        chai.request(server)
          .delete(ROOT_PATH + '/' + entityId)
          .end(function(err, res){

            if (err) throw err;

            res.should.have.status(204);

            done();
          });
      });

    });

  });

})();
