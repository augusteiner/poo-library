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

import poo.library.app.util.ISeeder;
import poo.library.app.util.Seeder;
import poo.library.comum.IItemAcervo;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Apostila;
import poo.library.modelo.Livro;
import poo.library.modelo.Texto;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
public class ItemAcervoSeeder extends Seeder<IItemAcervo>
    implements ISeeder<IItemAcervo> {

    public ItemAcervoSeeder(IDAO<IItemAcervo> dao) {

        super(dao);
    }

    @Override
    public void seed() {

        int bibliotecaId = BibliotecaSeeder.getBibliotecaId();

        this.dao.delete("1 = 1");

        IItemAcervo[] itens = new IItemAcervo[] {
            new Livro("José A.", "POO - Introdução", 2.5, bibliotecaId),
            new Texto("João M.", 1.5, bibliotecaId),
            new Apostila("Maria J.", "Java & JBDC", 1.25, bibliotecaId) };

        for (IItemAcervo item : itens) {

            this.dao.save(item);
        }
    }
}
