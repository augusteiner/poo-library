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
package poo.library.util;

import poo.library.comum.IItemAcervo;
import poo.library.comum.ILocacao;
import poo.library.comum.IReserva;
import poo.library.comum.IUsuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public interface IBuscador {

    IItemAcervo itemPorId(int itemId) throws ObjetoNaoEncontradoException;
    Iterable<? extends IItemAcervo> itens();
    Iterable<? extends IItemAcervo> itensPorTermo(String termo);

    ILocacao locacaoPorId(int locacaoId) throws ObjetoNaoEncontradoException;
    Iterable<? extends ILocacao> locacoes();
    //Iterable<? extends ILocacao> locacoesPorUsuario(IUsuario usuario);
    Iterable<? extends ILocacao> locacoesPorUsuarioId(int usuarioId) throws ObjetoNaoEncontradoException;

    IReserva reservaPorId(int reservaId) throws ObjetoNaoEncontradoException;
    Iterable<? extends IReserva> reservas();
    //Iterable<? extends IReserva> reservasPorUsuario(IUsuario usuario);
    Iterable<? extends IReserva> reservasPorUsuarioId(int usuarioId) throws ObjetoNaoEncontradoException;

    IUsuario usuarioPorId(int usuarioId) throws ObjetoNaoEncontradoException;
    Iterable<? extends IUsuario> usuarios();
    Iterable<? extends IUsuario> usuariosPorTermo(String termo);
}
