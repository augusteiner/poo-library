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

import static poo.library.app.seeder.BibliotecaSeeder.*;
import static poo.library.app.seeder.LocacaoSeeder.*;

import poo.library.app.util.ISeeder;
import poo.library.app.util.Seeder;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Apostila;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Livro;
import poo.library.modelo.Texto;
import poo.library.util.FalhaOperacaoException;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
class ItemAcervoSeeder extends Seeder<ItemAcervo>
    implements ISeeder<ItemAcervo> {

    static int firstItemAcervoId;

    private ItemAcervo i1;
    private ItemAcervo i2;

    public ItemAcervoSeeder(IDAO<ItemAcervo> dao) {

        super(dao);
    }

    @Override
    public void seed() throws FalhaOperacaoException {

        ItemAcervo[] itens = new ItemAcervo[] {
            i1 = new Livro("José A.", "POO - Introdução", 2.5, firstBibliotecaId),
            i2 = new Texto("João M.", 1.5, firstBibliotecaId),
            new Apostila("Maria J.", "Java & JBDC", 1.25, firstBibliotecaId) };

        item1 = i1;
        item2 = i2;

        for (ItemAcervo item : itens) {

            this.dao.save(item);
        }

        firstItemAcervoId = i1.getId();
    }
}
