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
import java.util.Iterator;

import poo.library.comum.IItemAcervo;
import poo.library.comum.ILocacao;
import poo.library.comum.IReserva;
import poo.library.comum.IUsuario;
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

    public AcervoEmMemoria() {

        //this.locacoes = novaLista();
        //this.reservas = novaLista();
        //this.itens = novaLista();

        //this.usuarios = novaLista();
    }

    @Override
    public IItemAcervo itemPorId(int itemAcervoId) throws ObjetoNaoEncontradoException {

        for (IItemAcervo i : this.itens) {

            if (i.getId() == itemAcervoId) {

                return i;
            }
        }

        throw new ObjetoNaoEncontradoException(String.format(
            "Item de Acervo #%d não encontrado",
            itemAcervoId));
    }

    @Override
    public Iterable<IItemAcervo> itens() {

        return Iterables.cast(this.itens);
    }

    @Override
    public Iterable<IItemAcervo> itensPorTermo(String termo) {

        Collection<IItemAcervo> itens = novaLista();

        for (IItemAcervo i : this.itens) {

            if (i.match(termo)) {

                itens.add(i);
            }
        }

        return itens;
    }

    @Override
    public Iterator<IItemAcervo> iterator() {

        Iterable<IItemAcervo> iter = Iterables.cast(this.itens);

        return iter.iterator();
    }

    @Override
    public ILocacao locacaoPorId(int locacaoId) {

        for (ILocacao l : locacoes) {

            if (l.getId() == locacaoId) {

                return l;
            }
        }

        return null;
    }

    @Override
    public Iterable<ILocacao> locacoes() {

        return Iterables.cast(this.locacoes);
    }

    @Override
    public Iterable<ILocacao> locacoesPorUsuario(IUsuario usuario) {

        return this.locacoesPorUsuarioId(usuario.getId());
    }

    @Override
    public Iterable<ILocacao> locacoesPorUsuarioId(int usuarioId) {

        Collection<ILocacao> locacoes = novaLista();

        for (ILocacao l : this.locacoes) {

            if (l.getUsuarioId() == usuarioId) {

                locacoes.add(l);
            }
        }

        return locacoes;
    }

    @Override
    public IReserva reservaPorId(int reservaId) {

        for (IReserva r : this.reservas) {

            if (r.getId() == reservaId) {

                return r;
            }
        }

        return null;
    }

    @Override
    public Iterable<IReserva> reservas() {

        return Iterables.cast(this.reservas);
    }

    @Override
    public Iterable<IReserva> reservasPorUsuario(IUsuario usuario) {

        return this.reservasPorUsuarioId(usuario.getId());
    }

    @Override
    public Iterable<IReserva> reservasPorUsuarioId(int usuarioId) {

        Collection<IReserva> reservas = novaLista();

        for (IReserva r : this.reservas) {

            if (r.getUsuarioId() == usuarioId) {

                reservas.add(r);
            }
        }

        return reservas;
    }

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

    //public void salvarUsuario(IUsuario usuario) {
    //
    //    this.usuarios.add(usuario);
    //}

    @Override
    public IUsuario usuarioPorId(int usuarioId) {

        for (IUsuario u : this.usuarios) {

            if (u.getId() == usuarioId) {

                return u;
            }
        }

        return null;
    }

    @Override
    public Iterable<IUsuario> usuarios() {

        return Iterables.cast(this.usuarios);
    }

    @Override
    public Iterable<IUsuario> usuariosPorTermo(String termo) {

        Collection<IUsuario> usuarios = novaLista();

        for (IUsuario u : this.usuarios) {

            if (u.match(termo)) {

                usuarios.add(u);
            }
        }

        return usuarios;
    }

    private <T> Collection<T> novaLista() {

        return new ArrayList<T>();
    }

    protected Biblioteca getBiblioteca() {

        return this.biblioteca;
    }

    @Override
    public void setBiblioteca(Biblioteca biblioteca) {

        this.biblioteca = biblioteca;
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
    public void setItensAcervo(Collection<ItemAcervo> itens) {

        if (itens == null) {

            throw new IllegalArgumentException(
                "Argumento itens não deve ser nulo");
        }

        this.itens = itens;
    }
}
