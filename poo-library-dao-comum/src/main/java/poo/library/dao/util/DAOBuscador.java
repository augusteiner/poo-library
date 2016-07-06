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
package poo.library.dao.util;

import poo.library.comum.IItemAcervo;
import poo.library.comum.ILocacao;
import poo.library.comum.IReserva;
import poo.library.comum.IUsuario;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Locacao;
import poo.library.modelo.Reserva;
import poo.library.modelo.Usuario;
import poo.library.util.IBuscador;
import poo.library.util.Iterables;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class DAOBuscador implements IBuscador {

    private final IDAO<? extends ILocacao> locacoes;
    private final IDAO<? extends IReserva> reservas;
    private final IDAO<? extends IUsuario> usuarios;
    private final IDAO<? extends IItemAcervo> itens;

    public DAOBuscador() {

        this.locacoes = DAOFactory.createNew(Locacao.class);
        this.reservas = DAOFactory.createNew(Reserva.class);
        this.usuarios = DAOFactory.createNew(Usuario.class);
        this.itens = DAOFactory.createNew(ItemAcervo.class);
    }

    @Override
    public IItemAcervo itemPorId(int itemId) throws ObjetoNaoEncontradoException {

        return itens.find(itemId);
    }

    @Override
    public Iterable<IItemAcervo> itens() {

        return Iterables.cast(this.itens.all());
    }

    @Override
    public Iterable<IItemAcervo> itensPorTermo(String termo) {

        return Iterables.cast(this.itens.search(termo));
    }

    @Override
    public ILocacao locacaoPorId(int locacaoId) throws ObjetoNaoEncontradoException {

        return this.locacoes.find(locacaoId);
    }

    @Override
    public Iterable<ILocacao> locacoes() {

        return Iterables.cast(this.locacoes.all());
    }

    @Override
    public Iterable<ILocacao> locacoesPorUsuario(IUsuario usuario) {

        return usuario.getLocacoes();
    }

    @Override
    public Iterable<ILocacao> locacoesPorUsuarioId(int usuarioId) throws ObjetoNaoEncontradoException {

        IUsuario usuario = this.usuarioPorId(usuarioId);

        return this.locacoesPorUsuario(usuario);
    }

    @Override
    public IReserva reservaPorId(int reservaId) throws ObjetoNaoEncontradoException {

        return this.reservas.find(reservaId);
    }

    @Override
    public Iterable<IReserva> reservas() {

        return Iterables.cast(this.reservas.all());
    }

    @Override
    public Iterable<IReserva> reservasPorUsuario(IUsuario usuario) {

        return usuario.getReservas();
    }

    @Override
    public Iterable<IReserva> reservasPorUsuarioId(int usuarioId) throws ObjetoNaoEncontradoException {

        IUsuario usuario = this.usuarioPorId(usuarioId);

        return this.reservasPorUsuario(usuario);
    }

    @Override
    public IUsuario usuarioPorId(int usuarioId) throws ObjetoNaoEncontradoException {

        return this.usuarios.find(usuarioId);
    }

    @Override
    public Iterable<IUsuario> usuarios() {

        return Iterables.cast(this.usuarios.all());
    }

    @Override
    public Iterable<IUsuario> usuariosPorTermo(String termo) {

        return Iterables.cast(this.usuarios.search(termo));
    }
}
