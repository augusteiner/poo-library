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

import poo.library.comum.ILocacao;
import poo.library.comum.IItemAcervo;
import poo.library.comum.IReserva;
import poo.library.comum.IUsuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public interface IBibliotecaStorage {

    ILocacao locacaoPorId(int locacaoId);
    Iterable<ILocacao> locacoes();
    Iterable<ILocacao> locacoesPorUsuario(IUsuario usuario);
    Iterable<ILocacao> locacoesPorUsuarioId(int usuarioId);

    IItemAcervo itemPorId(int itemId);
    Iterable<IItemAcervo> itens();
    Iterable<IItemAcervo> itensPorTermo(String termo);

    IReserva reservaPorId(int reservaId);
    Iterable<IReserva> reservas();
    Iterable<IReserva> reservasPorUsuario(IUsuario usuario);
    Iterable<IReserva> reservasPorUsuarioId(int usuarioId);

    IUsuario usuarioPorId(int usuarioId);
    Iterable<IUsuario> usuarios();
    Iterable<IUsuario> usuariosPorTermo(String termo);
}
