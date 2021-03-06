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
package poo.library.comum;

import java.util.Date;

import poo.library.util.FalhaOperacaoException;
import poo.library.util.ISearcheable;
import poo.library.util.ItemIndisponivelException;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public interface IBiblioteca extends IIdentificavel, ISearcheable {

    String getNome();
    String getEndereco();
    double getMultaDiaria();
    int getQteDiasValidadeReserva();
    int getQteDiasLocacao();

    Iterable<? extends IItemAcervo> getAcervo();

    Iterable<? extends ILocacao> getLocacoes();
    Iterable<? extends IReserva> getReservas();

    @Deprecated
    Iterable<? extends IUsuario> getUsuarios();

    void setNome(String nome);
    void setMultaDiaria(double multaDiaria);

    IReserva cancelar(int reservaId)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException;

    ILocacao devolver(int locacaoId, Date referencia)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException;
    ILocacao devolver(int usuarioId, int itemAcervoId, Date referencia)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException;

    ILocacao locar(int itemAcervoId, int usuarioId, Date referencia)
        throws ObjetoNaoEncontradoException, ItemIndisponivelException;

    IReserva reservar(int itemAcervoId, int usuarioId, Date referencia)
        throws ObjetoNaoEncontradoException, ItemIndisponivelException;

    void atualizarReservas(Date referencia);
    void atualizarLocacoes(Date referencia);
}
