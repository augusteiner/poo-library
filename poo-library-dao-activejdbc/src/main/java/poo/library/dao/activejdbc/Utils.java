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
package poo.library.dao.activejdbc;

import java.util.Iterator;

import org.javalite.activejdbc.Model;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Utils {

    public static <TFrom extends Model & IAdapter<TTo>, TTo> Iterable<TTo> mapIterable(
        final Iterable<TFrom> iterable,
        final Class<TTo> toType) {

        return new Iterable<TTo>() {

            @Override
            public Iterator<TTo> iterator() {

                final Iterator<TFrom> iter = iterable.iterator();

                return new Iterator<TTo>() {

                    @Override
                    public boolean hasNext() {

                        return iter.hasNext();
                    }

                    @Override
                    public TTo next() {

                        TFrom from = iter.next();

                        TTo to = from.map(from);

                        // TODO utilizar model mapper
                        // usuario.setId(user.getInteger("id"));
                        // usuario.setNome(user.getString("nome"));

                        return to;
                    }
                };
            }
        };
    }

}
