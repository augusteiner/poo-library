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
package poo.library.app;

import static poo.library.app.App.configure;

import java.util.Collection;

import poo.library.comum.IBiblioteca;
import poo.library.comum.IItemAcervo;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.dao.util.DAOCollectionMap;
import poo.library.util.ICollectionMap;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class CollectionTests {

    public static void main(String[] args) throws ObjetoNaoEncontradoException {

        configure();

        IDAO<IBiblioteca> keys = DAOFactory.createNew(IBiblioteca.class);
        IDAO<IItemAcervo> values = DAOFactory.createNew(IItemAcervo.class);

        ICollectionMap<IBiblioteca, IItemAcervo> map = new DAOCollectionMap<IBiblioteca, IItemAcervo>(
            keys,
            values,
            "biblioteca");

        Collection<IItemAcervo> list = map.asList();

        IBiblioteca bi = keys.first();
        map.values(bi);

        for (IItemAcervo item : list) {

            System.out.println(String.format(
                "#%d - %s (%s)",

                item.getId(),
                item.getAutor(),
                item.getCategoria()));
        }
    }
}
