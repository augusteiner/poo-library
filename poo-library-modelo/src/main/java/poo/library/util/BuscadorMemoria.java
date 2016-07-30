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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import poo.library.modelo.comum.IBuscador;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Locacao;
import poo.library.modelo.Reserva;
import poo.library.modelo.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class BuscadorMemoria implements IBuscador {

    private Biblioteca biblioteca;

    private Collection<Locacao> locacoes;
    private Collection<Reserva> reservas;
    private Collection<ItemAcervo> itens;

    private Collection<Usuario> usuarios;

    public BuscadorMemoria() { }

    public void close() {

        this.biblioteca = null;

        this.itens = null;
        this.locacoes = null;
        this.reservas = null;
        this.usuarios = null;
    }

    @Override
    public Biblioteca getBiblioteca() {

        return this.biblioteca;
    }

    @Override
    public ItemAcervo itemPorId(int itemAcervoId) throws ObjetoNaoEncontradoException {

        System.out.println(String.format(
            "Collection: %s (size: %d)",

            this.itens.getClass(),
            this.itens.size()));

        for (ItemAcervo i : this.itens) {

            if (i.getId() == itemAcervoId) {

                System.out.println(String.format(
                    "Item de Acervo #%d encontrado",

                    itemAcervoId));

                return i;
            }
        }

        throw new ObjetoNaoEncontradoException(String.format(
            "Item de Acervo #%d não encontrado",
            itemAcervoId));
    }

    @Override
    public Collection<ItemAcervo> itens() {

        return Collections.unmodifiableCollection(this.itens);
    }

    @Override
    public Collection<ItemAcervo> itensPorTermo(String termo) {

        Collection<ItemAcervo> itens = novaLista();

        for (ItemAcervo i : this.itens) {

            if (i.match(termo)) {

                itens.add(i);
            }
        }

        return Collections.unmodifiableCollection(itens);
    }

    @Override
    public Locacao locacaoPorId(int locacaoId) throws ObjetoNaoEncontradoException {

        for (Locacao l : this.locacoes) {

            if (((Object) locacaoId).equals(l.getId())) {

                return l;
            }
        }

        throw new ObjetoNaoEncontradoException(String.format(
            "Locação de id #%d não encontrada",
            locacaoId));
    }

    @Override
    public Collection<Locacao> locacoes() {

        return Collections.unmodifiableCollection(this.locacoes);
    }

    public Collection<Locacao> locacoesPorUsuario(Usuario usuario) {

        Collection<Locacao> locacoes = novaLista();

        for (Locacao l : this.locacoes) {

            if (l.getUsuarioId() == usuario.getId()) {

                locacoes.add(l);
            }
        }

        return Collections.unmodifiableCollection(locacoes);

    }

    @Override
    public Collection<Locacao> locacoesPorUsuarioId(int usuarioId)
        throws ObjetoNaoEncontradoException {

        Usuario usuario = this.usuarioPorId(usuarioId);

        return this.locacoesPorUsuario(usuario);
    }

    private <T> Collection<T> novaLista() {

        return new ArrayList<T>();
    }

    @Override
    public Reserva reservaPorId(int reservaId) throws ObjetoNaoEncontradoException {

        for (Reserva r : this.reservas) {

            if (((Object) reservaId).equals(r.getId())) {

                return r;
            }
        }

        throw new ObjetoNaoEncontradoException(String.format(
            "Reserva de id #%d não encontrada",
            reservaId));
    }

    @Override
    public Collection<Reserva> reservas() {

        return Collections.unmodifiableCollection(this.reservas);
    }

    public Collection<Reserva> reservasPorUsuario(Usuario usuario) {

        Collection<Reserva> reservas = novaLista();

        for (Reserva r : this.reservas) {

            if (r.getUsuarioId() == usuario.getId()) {

                reservas.add(r);
            }
        }

        return Collections.unmodifiableCollection(reservas);
    }

    @Override
    public Collection<Reserva> reservasPorUsuarioId(int usuarioId)
        throws ObjetoNaoEncontradoException {

        Usuario usuario = this.usuarioPorId(usuarioId);

        return this.reservasPorUsuario(usuario);
    }

    @Override
    public void setBiblioteca(Biblioteca biblioteca) {

        this.biblioteca = biblioteca;

        this.setLocacoes(biblioteca.getLocacoes());
        this.setReservas(biblioteca.getReservas());
        this.setItensAcervo(biblioteca.getAcervo());
    }

    public void setItensAcervo(Collection<ItemAcervo> itens) {

        if (itens == null) {

            throw new IllegalArgumentException(
                "Argumento itens não deve ser nulo");
        }

        this.itens = itens;
    }

    public void setLocacoes(Collection<Locacao> locacoes) {

        if (locacoes == null) {

            throw new IllegalArgumentException(
                "Argumento locacoes não deve ser nulo");
        }

        this.locacoes = locacoes;
    }

    public void setReservas(Collection<Reserva> reservas) {

        if (reservas == null) {

            throw new IllegalArgumentException(
                "Argumento reservas não deve ser nulo");
        }

        this.reservas = reservas;
    }

    @Override
    public Usuario usuarioPorId(int usuarioId) throws ObjetoNaoEncontradoException {

        for (Usuario u : this.usuarios) {

            if (u.getId() == usuarioId) {

                return u;
            }
        }

        throw new ObjetoNaoEncontradoException(String.format(
            "Usuário de id #%d não encontrado",
            usuarioId));
    }

    @Override
    public Collection<Usuario> usuarios() {

        return this.usuarios;
    }

    @Override
    public Collection<Usuario> usuariosPorTermo(String termo) {

        Collection<Usuario> usuarios = novaLista();

        for (Usuario u : this.usuarios) {

            if (u.match(termo)) {

                usuarios.add(u);
            }
        }

        return usuarios;
    }

    @Override
    public Locacao ultimaLocacao(int usuarioId, int itemAcervoId)
        throws ObjetoNaoEncontradoException {

        // XXX Se vale da ordenação de locações pela data de realização
        for (Locacao l : this.locacoes) {

            if (l.getUsuarioId() == usuarioId &&
                l.getItemAcervoId() == itemAcervoId) {

                return l;
            }
        }

        throw new ObjetoNaoEncontradoException(String.format(
            "Última locação não encontrada (Usuario: #%d; ItemAcervo: #%d)",

            usuarioId,
            itemAcervoId));
    }
}
