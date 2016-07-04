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

import poo.library.comum.IItemAcervo;
import poo.library.comum.ILocacao;
import poo.library.comum.IReserva;
import poo.library.comum.IUsuario;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Locacao;
import poo.library.modelo.Reserva;
import poo.library.modelo.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class MemoryStorage implements IBibliotecaStorage {

    private final Collection<Locacao> locacoes;
    private final Collection<Reserva> reservas;
    private final Collection<Usuario> usuarios;
    private final Collection<ItemAcervo> itens;

    public MemoryStorage() {

        this.locacoes = newCollection();
        this.reservas = newCollection();
        this.usuarios = newCollection();
        this.itens = newCollection();
    }

    private <T> Collection<T> newCollection() {

        return new ArrayList<T>();
    }

    @Override
    public ILocacao locacaoPorId(int locacaoId) {

        for (Locacao l : locacoes) {

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

        Collection<ILocacao> locacoes = newCollection();

        for (Locacao l : this.locacoes) {

            if (l.getUsuarioId() == usuarioId) {

                locacoes.add(l);
            }
        }

        return locacoes;
    }

    @Override
    public IItemAcervo itemPorId(int itemId) {

        for (ItemAcervo i : this.itens) {

            if (i.getId() == itemId) {

                return i;
            }
        }

        return null;
    }

    @Override
    public Iterable<IItemAcervo> itens() {

        return Iterables.cast(this.itens);
    }

    @Override
    public Iterable<IItemAcervo> itensPorTermo(String termo) {

        Collection<IItemAcervo> itens = newCollection();

        for (ItemAcervo i : this.itens) {

            if (i.match(termo)) {

                itens.add(i);
            }
        }

        return itens;
    }

    @Override
    public IReserva reservaPorId(int reservaId) {

        for (Reserva r : this.reservas) {

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

        Collection<IReserva> reservas = newCollection();

        for (Reserva r : this.reservas) {

            if (r.getUsuarioId() == usuarioId) {

                reservas.add(r);
            }
        }

        return reservas;
    }

    @Override
    public IUsuario usuarioPorId(int usuarioId) {

        for (Usuario u : this.usuarios) {

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

        Collection<IUsuario> usuarios = newCollection();

        for (IUsuario u : this.usuarios) {

            if (u.match(termo)) {

                usuarios.add(u);
            }
        }

        return usuarios;
    }
}
