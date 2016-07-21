var chai = require('chai');
var chaiHttp = require('chai-http');
var server = 'http://localhost:9090/api';

var should = chai.should();

chai.use(chaiHttp);

describe('CRUD de Usuários', function() {
  var usuarioId = null;
  var ROOT_PATH = '/usuario';

  it('Deve listar todos os usuários em /usuario GET', function(done) {
    chai.request(server)
      .get(ROOT_PATH)
      .end(function(err, res){

        if (err) throw err;

        res.should.have.status(200);

        done();
      });
  });

  it('Deve cadastrar usuário em /usuario POST', function(done) {
    chai.request(server)
      .post(ROOT_PATH)
      .send({
        'nome' : 'João Maria das Lurdes',
        'cpf' : '00569875487',
        'endereco' : 'Av. Salgado Filho, 1001',
        'tipo': 'COMUM'})
      .end(function(err, res){

        if (err) throw err;

        res.headers.location.should.be.a('string');

        usuarioId = res.headers.location.replace(server, '').replace('/usuario/', '');
        usuarioId.should.be.above(0);

        res.should.have.status(201);

        done();
      });
  });

  it('Deve alterar usuário em /usuario PUT', function(done) {

    chai.request(server)
      .put(ROOT_PATH + '/' + usuarioId)
      .send({
        'id' : usuarioId,
        'nome' : 'João Maria das Lurdes',
        'cpf' : '00569875487',
        'endereco' : 'Av. Salgado Filho, 1001',
        'tipo': 'COMUM'})
      .end(function(err, res){

        if (err) throw err;

        res.should.have.status(200);

        done();
      });
  });

  it('Deve deletar usuário em /usuario DELETE', function(done) {
    chai.request(server)
      .delete(ROOT_PATH + '/' + usuarioId)
      .end(function(err, res){

        if (err) throw err;

        res.should.have.status(200);

        done();
      });
  });
});
