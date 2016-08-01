
INSERT INTO biblioteca (id, nome, endereco, multaDiaria, qteDiasValidadeReserva, qteDiasLocacao) VALUES
	(1, 'iBook Store - Lagoa Nova', 'Av. Bernardo Vieira, 666', 5.5500, 3, 14),
	(2, 'iBook Store - Alecrim', 'Av. Presidente Bandeira, 1201', 7.5500, 3, 14),
	(4, 'iBook Store - Ponta Negra', 'Av. Engenheiro Roberto Freire, 1002', 5.4500, 3, 14);

INSERT INTO item_acervo (id, bibliotecaId, categoria, titulo, autor, edicao, isbn, qteTotal, qteDisponivel, precoLocacao) VALUES
	(1, 1, 'LIVRO', 'Use a cabeça! Java', 'NASCIMENTO, Capitão', NULL, NULL, 10, 0, 12.2500),
	(2, 1, 'APOSTILA', 'Como usar o MS Word 2013', 'GOMES, Javacildo', NULL, NULL, 19, 0, 2.5500),
	(3, 1, 'TEXTO', 'As implicações do aquecimento global', 'SANTOS, Maria dos', NULL, NULL, 1, 0, 1.2500),
	(4, 1, 'LIVRO', 'Como gastar seu dinheiro', 'GRANA, Salvio J.', NULL, NULL, 3, 0, 2.8500),
	(5, 2, 'APOSTILA', 'Como usar o MS Power Point 2013', 'SOUSA, Mario de', NULL, NULL, 20, 0, 3.5500),
	(6, 2, 'LIVRO', 'Harry Poter e a Ordem da Fênix', 'ROWLING, J. K.', NULL, NULL, 30, 0, 6.5500),
	(7, 2, 'LIVRO', 'Como dar nó em pingo d''água', 'OJUARA, João Sérgio de', NULL, NULL, 20, 0, 9.9500),
	(23, 4, 'LIVRO', 'Como ganhar na loteria, Vol. I', 'GRANA, José Muita', NULL, NULL, 10, 0, 3.5500),
	(24, 4, 'LIVRO', 'Harry Potter e a Pedra Filosofal', 'ROWLING, J. K.', NULL, NULL, 10, 0, 3.5500);

INSERT INTO usuario (id, nome, login, senha, cpf, tipo, endereco, criadoEm, atualizadoEm) VALUES
	(1, 'José Silvério dos Reis', 'jose.silverio', '123456', '91203109283', 'COMUM', 'Av. da Integração, 77', '2016-07-31 19:29:58', NULL),
	(2, 'João Armando Gonçalves', 'joao.armando', '123456', '18279163491', 'COMUM', 'R. das Olimpíadas, 11', '2016-07-31 19:30:25', NULL),
	(3, 'Maria Auxiliadora dos Pobres', 'maria.pobre', '123456', '13249178932', 'COMUM', 'R. dos Pedintes, 121', '2016-07-31 19:31:29', NULL),
	(4, 'Administrador', 'admin', '123456', '91823619283', 'ADMIN', 'Av. dos Admins, 1', '2016-07-31 19:34:23', NULL);
