/*
 * MIT License
 *
 * Copyright (c) 2016 José Augusto & Juscelino Messias
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package poo.library.app;

import static poo.library.app.App.*;

import poo.library.comum.IIdentificavel;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.dao.util.DAOAcervo;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.comum.IBuscador;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class BuscadorTests {

    private int usuarioId;
    private int locacaoId;
    private int reservaId;
    private int itemId;

    private String usuariosTermo = "a";
    private String itensTermo = "a";

    public static void main(String[] args) throws Exception {

        //System.err.close();

        configure();

        try {

            run();

        } finally {

            DAOFactory.close();
        }
    }

    public static void run() throws ObjetoNaoEncontradoException {

        BuscadorTests tests = new BuscadorTests();
        IDAO<Biblioteca> dao = DAOFactory.createNew(Biblioteca.class);

        tests.buscador = new DAOAcervo(dao);
        Biblioteca.exportarBuscador(dao.first(), tests.buscador);

        tests.runTests();
    }

    private IBuscador buscador;

    @SuppressWarnings("unchecked")
    private <I, T> I displayIter(Iterable<T> itens) {

        I id = null;

        for (T item : itens) {

            if (item instanceof IIdentificavel)
                id = (I) (Object) ((IIdentificavel) item).getId();

            if (item instanceof poo.library.comum.generic.IIdentificavel<?>)
                id = (I) ((poo.library.comum.generic.IIdentificavel<?>) item).getId();

            println("%s", item);
        }

        return id;
    }

    private void runTests() throws ObjetoNaoEncontradoException {

        this.testSearch();
    }

    private void testSearch() throws ObjetoNaoEncontradoException {

        this.testSearchItens();

        this.testSearchUsuarios();

        this.testSearchReservas();

        this.testSearchLocacoes();
    }

    private void testSearchItens() throws ObjetoNaoEncontradoException {

        // Listando todos os Itens
        printlnCentro("Listando todos os Itens");

        itemId = displayIter(buscador.itens());

        // Busca por todos os Itens
        printlnCentro("Item por id %d", itemId);

        println("Item encontrado: %s", buscador.itemPorId(itemId));

        // Busca de Itens por Id
        printlnCentro("Itens por termo: %s", itensTermo);

        displayIter(buscador.itensPorTermo(itensTermo));
    }

    private void testSearchLocacoes() throws ObjetoNaoEncontradoException {

        printlnCentro("Listando todas as Locações");

        locacaoId = displayIter(buscador.locacoes());

        printlnCentro("Locação por id: %d", locacaoId);

        println("Locação encontrada: %s", buscador.locacaoPorId(locacaoId));

        printlnCentro("Locações por id do usuario: %s", usuarioId);

        displayIter(buscador.locacoesPorUsuarioId(usuarioId));
    }

    private void testSearchReservas() throws ObjetoNaoEncontradoException {

        printlnCentro("Listando todas as Reservas");

        reservaId = displayIter(buscador.reservas());

        printlnCentro("Reserva por id: %d", reservaId);

        println("Reserva encontrada: %s", buscador.reservaPorId(reservaId));

        printlnCentro("Reservas por id do usuario: %s", usuarioId);

        displayIter(buscador.reservasPorUsuarioId(usuarioId));
    }

    private void testSearchUsuarios() throws ObjetoNaoEncontradoException {

        printlnCentro("Listando todos os Usuários");

        usuarioId = displayIter(buscador.usuarios());

        printlnCentro("Usuário por id: %d", usuarioId);

        println("Usuário encontrado: %s", buscador.usuarioPorId(usuarioId));

        printlnCentro("Usuários por termo: %s", usuariosTermo);

        displayIter(buscador.usuariosPorTermo(usuariosTermo));
    }
}
