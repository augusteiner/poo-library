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

import java.util.Collection;

import poo.library.dao.comum.IDAO;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Locacao;
import poo.library.modelo.Reserva;
import poo.library.modelo.comum.IBuscador;
import poo.library.util.AcervoEmMemoria;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class DAOAcervo extends AcervoEmMemoria implements IBuscador {

    private final IDAO<Biblioteca> dao;

    public DAOAcervo(IDAO<Biblioteca> dao) {

        this.dao = dao;
    }

    public IDAO<Biblioteca> getDAO() {

        return this.dao;
    }

    /**
     * XXX Forçando carregamento EAGER da lista
     *
     * Em futuras versões do java + jpa, utilizando streams seria possível
     * reduzir o overhead de uma lista inteira na heap utilizando métodos para
     * paginação.
     *
     * <code>
     *   // exemplo
     *   list.stream().skip(10).limit(10).collect(toList());
     * </code>
     *
     * @param list
     * @return
     */
    private <T> Collection<T> initialise(Collection<T> list) {

        list.size();

        return list;
    }

    @Override
    public Collection<ItemAcervo> itens() {

        return this.initialise(super.itens());
    }

    @Override
    public Collection<Locacao> locacoes() {

        return this.initialise(super.locacoes());
    }

    @Override
    public Collection<Reserva> reservas() {

        return this.initialise(super.reservas());
    }
}
