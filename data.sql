

INSERT INTO biblioteca (id, nome, endereco, multaDiaria, qteDiasValidadeReserva, qteDiasLocacao) VALUES
  (1, 'iBook Store - Lagoa Nova', 'Av. Bernardo Vieira, 666', 5.5500, 3, 14),
  (2, 'iBook Store - Alecrim', 'Av. Presidente Bandeira, 1201', 7.5500, 3, 14),
  (3, 'iBook Store - Ponta Negra', 'Av. Engenheiro Roberto Freire, 1002', 5.4500, 3, 14)
ON DUPLICATE KEY UPDATE
  id = VALUES(id);

INSERT INTO item_acervo (id, bibliotecaId, categoria, titulo, autor, sinopse, edicao, isbn, qteTotal, qteDisponivel, precoLocacao) VALUES
  (1, 1, 'LIVRO', 'Use a cabeça! Java', 'NASCIMENTO, Capitão', 'Use a "cabeça" e comece a utilizar esta ótima linguagem de programação. Use a cabeça! utiliza uma linguagem informal porém nada retrógrada para lhe ensinar todos os macetes desta linguagem de pogramação', NULL, NULL, 10, 0, 12.2500),
  (2, 1, 'APOSTILA', 'Como usar o MS Word 2013', 'GOMES, Jevanildo', 'Aprenda a usar esta ótima ferramenta da Microsoft e torne-se um profissional de ponta', NULL, NULL, 19, 0, 2.5500),
  (3, 1, 'TEXTO', 'As implicações do aquecimento global', 'SANTOS, Maria dos', 'Neste breve resumo sobre o clima e o aquecimento global o autor indaga a humanidade sobre questões primordiais a respeito das implicações do imprecedente aquecimento que o nosso planeta sofre nas últimas décadas', NULL, NULL, 1, 0, 1.2500),
  (4, 1, 'LIVRO', 'Como gastar seu dinheiro', 'GRANA, Salvio J.', 'Aprenda a utilizar o seu dinheiro de forma eficaz, valorizando a qualidade e diminuindo gastos desnecessários', NULL, NULL, 3, 0, 2.8500),
  (5, 2, 'APOSTILA', 'Como usar o MS Power Point 2013', 'SOUSA, Mario de', 'Aprenda a usar esta excelente ferramenta de apresentações e seja mestre na arte dos slides, prezi é coisa do passado, com esta apostila você terá habilidades supremas na apresentação de slides', NULL, NULL, 20, 0, 3.5500),
  (6, 2, 'LIVRO', 'Harry Poter e a Ordem da Fênix', 'ROWLING, J. K.', 'Neste volume da série Harry Potter você, Harry, Hermione e Rony irão desvendar os mistérios do mundo bruxo e enfrentar comensais da morte de perto', NULL, NULL, 30, 0, 6.5500),
  (7, 2, 'LIVRO', 'Como dar nó em pingo d''água', 'OJUARA, João Sérgio de', 'Especialista na arte de dar nós ensina a você os segredos de como dar nós em coisas inimagináveis', NULL, NULL, 20, 0, 9.9500),
  (8, 3, 'LIVRO', 'Como ganhar na loteria, Vol. I', 'GRANA, José Muita', 'Neste best seller da indústria de livros você irá aprender como ganhar na loteria, o livro que todo mundo quer ter', NULL, NULL, 10, 0, 3.5500),
  (9, 3, 'LIVRO', 'Harry Potter e a Pedra Filosofal', 'ROWLING, J. K.', 'Na primeira obra de sucesso da escritora J. K. Rowling, ela apresenta Harry Potter um garoto magricela e que não tem a menor ideia do seu passado. Acompanhe Harry ao passo que ele é introduzido no mundo de magia e bruxaria na escola de Hogwarts', NULL, NULL, 10, 0, 3.5500)
ON DUPLICATE KEY UPDATE
  id = VALUES(id);

INSERT INTO usuario (id, nome, login, senha, saldoDevedor, cpf, tipo, endereco, criadoEm, atualizadoEm) VALUES
  (1, 'José Silvério dos Reis', 'jose.silverio', '123456', 0.0000, '91203109283', 'COMUM', 'Av. da Integração, 77', '2016-07-31 19:29:58', NULL),
  (2, 'João Armando Gonçalves', 'joao.armando', '123456', 0.0000, '18279163491', 'COMUM', 'R. das Olimpíadas, 11', '2016-07-31 19:30:25', NULL),
  (3, 'Maria Auxiliadora dos Pobres', 'maria.pobre', '123456', 0.0000, '13249178932', 'COMUM', 'R. dos Pedintes, 121', '2016-07-31 19:31:29', NULL),
  (4, 'Administrador', 'admin', '123456', 0.0000, '91823619283', 'ADMIN', 'Av. dos Admins, 1', '2016-07-31 19:34:23', NULL)
ON DUPLICATE KEY UPDATE
  id = VALUES(id);

