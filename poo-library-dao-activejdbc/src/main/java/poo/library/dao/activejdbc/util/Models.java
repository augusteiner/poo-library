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
package poo.library.dao.activejdbc.util;

import java.util.Iterator;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.ModelDelegate;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
public class Models {

    public static <TProxy> TProxy proxy(
        Model model,
        Class<TProxy> cls) {

        return ProxyFactory.makeNew(
            new ModelInvocationHandler(
                model,
                ModelDelegate.attributeNames(model.getClass())),
            cls);
    }

    public static <TModel extends Model, TProxy> Iterable<TProxy> proxy(
        final Iterable<TModel> modelIter,
        final Class<TProxy> proxyCls) {

        return new Iterable<TProxy>() {

            @Override
            public Iterator<TProxy> iterator() {

                final Iterator<TModel> iter = modelIter.iterator();

                return new Iterator<TProxy>() {

                    @Override
                    public boolean hasNext() {

                        return iter.hasNext();
                    }

                    @Override
                    public TProxy next() {

                        return proxy(
                            iter.next(),
                            proxyCls);
                    }
                };
            }
        };
    }
}
