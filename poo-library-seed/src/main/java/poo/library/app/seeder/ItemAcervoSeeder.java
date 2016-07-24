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
package poo.library.app.seeder;

import java.util.Arrays;
import java.util.List;

import poo.library.app.util.ISeeder;
import poo.library.comum.IItemAcervo;
import poo.library.modelo.Apostila;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Livro;
import poo.library.modelo.Texto;
import poo.library.util.FalhaOperacaoException;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
class ItemAcervoSeeder implements ISeeder<ItemAcervo> {

    private Biblioteca b1;

    private ItemAcervo i1;
    private ItemAcervo i2;
    private ItemAcervo i3;

    public ItemAcervoSeeder(Biblioteca b1) {

        i1 = new Livro("José A.", "POO - Introdução", 2.5, b1.getId());
        i1.setQteDisponivel(1);

        i2 = new Texto("João M.", 1.5, b1.getId());
        i2.setQteDisponivel(1);

        i3 = new Apostila("Maria J.", "Java & JBDC", 1.25, b1.getId());
        i3.setQteDisponivel(1);

        this.b1 = b1;
    }

    @Override
    public List<ItemAcervo> getList() {

        return Arrays.asList(
            i1,
            i2,
            i3);
    }

    @Override
    public void seed() throws FalhaOperacaoException {

        for (ItemAcervo item : getList()) {

            b1.addAcervo(item);
        }

        for (IItemAcervo item : b1.getAcervo()) {

            System.out.println(String.format(
                "Item do acervo: %d ~ %s",
                item.getId(),
                item));
        }
    }
}
