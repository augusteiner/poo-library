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

import poo.library.modelo.Biblioteca;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Locacao;
import poo.library.modelo.Reserva;
import poo.library.modelo.Usuario;
import poo.library.modelo.comum.IAcervo;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class AcervoEmMemoria implements IAcervo {

    private Biblioteca biblioteca;

    private Collection<Locacao> locacoes;
    private Collection<Reserva> reservas;
    private Collection<ItemAcervo> itens;

    private Collection<Usuario> usuarios;

    public AcervoEmMemoria() { }

    @Override
    public void close() throws Exception {

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

        return this.locacoesPorUsuarioId(usuario.getId());
    }

    @Override
    public Collection<Locacao> locacoesPorUsuarioId(int usuarioId) {

        Collection<Locacao> locacoes = novaLista();

        for (Locacao l : this.locacoes) {

            if (l.getUsuarioId() == usuarioId) {

                locacoes.add(l);
            }
        }

        return Collections.unmodifiableCollection(locacoes);
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

        return this.reservasPorUsuarioId(usuario.getId());
    }

    @Override
    public Collection<Reserva> reservasPorUsuarioId(int usuarioId) {

        Collection<Reserva> reservas = novaLista();

        for (Reserva r : this.reservas) {

            if (r.getUsuarioId() == usuarioId) {

                reservas.add(r);
            }
        }

        return Collections.unmodifiableCollection(reservas);
    }

    //public void salvarUsuario(IUsuario usuario) {
    //
    //    this.usuarios.add(usuario);
    //}

    @Override
    public void salvarItemAcervo(ItemAcervo itemAcervo) {

//         this.itens.add(itemAcervo);
    }

    @Override
    public void salvarLocacao(Locacao locacao) {

//        this.locacoes.add(locacao);
    }

    @Override
    public void salvarReserva(Reserva reserva) {

//        this.reservas.add(reserva);
    }

    @Override
    public void setBiblioteca(Biblioteca biblioteca) {

        this.biblioteca = biblioteca;
    }

    @Override
    public void setItensAcervo(Collection<ItemAcervo> itens) {

        if (itens == null) {

            throw new IllegalArgumentException(
                "Argumento itens não deve ser nulo");
        }

        this.itens = itens;
    }

    @Override
    public void setLocacoes(Collection<Locacao> locacoes) {

        this.locacoes = locacoes;
    }

    @Override
    public void setReservas(Collection<Reserva> reservas) {

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
    public Iterable<Usuario> usuarios() {

        return this.usuarios;
    }

    @Override
    public Iterable<Usuario> usuariosPorTermo(String termo) {

        Collection<Usuario> usuarios = novaLista();

        for (Usuario u : this.usuarios) {

            if (u.match(termo)) {

                usuarios.add(u);
            }
        }

        return usuarios;
    }
}
