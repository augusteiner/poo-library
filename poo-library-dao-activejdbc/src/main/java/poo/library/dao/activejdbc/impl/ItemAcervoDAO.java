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
package poo.library.dao.activejdbc.impl;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.ModelDelegate;
import org.javalite.activejdbc.annotations.Table;

import poo.library.comum.ECategoriaItem;
import poo.library.dao.activejdbc.GenericDAO;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Apostila;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Livro;
import poo.library.modelo.Texto;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
public class ItemAcervoDAO extends GenericDAO<ItemAcervo>
    implements IDAO<ItemAcervo> {

    @Table("item_acervo")
    public static class ItemAcervoModel extends Model { }

    public ItemAcervoDAO() {

        super(ItemAcervoModel.class, ItemAcervo.class);
    }

    public ItemAcervo firstOrDefault(
        String condition,
        Object... params) {

        Model model = ModelDelegate.findFirst(
            modelType,
            condition,
            params);

        if (model != null) {

            ECategoriaItem categoria = ECategoriaItem.valueOf(model.getString("categoria"));

            ItemAcervo obj = this.novaInstancia(categoria);

            this.inverseMap(obj, model);

            return obj;

        } else {

            return null;
        }
    }

    protected ItemAcervo novaInstancia(ECategoriaItem categoria) {

        switch (categoria) {

            case APOSTILA:
                return new Apostila();
            case LIVRO:
                return new Livro();
            case TEXTO:
                return new Texto();
            default:
                throw new IllegalArgumentException("categoria deve ser válida");
        }
    }

}
