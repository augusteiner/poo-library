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

import java.util.Collections;
import java.util.Iterator;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Iterables {

    public static <C, T extends C> Iterable<C> cast(final Iterable<T> iterable) {

        if (iterable == null) {

            return null;
        }

        return new Iterable<C>() {

            @Override
            public Iterator<C> iterator() {

                final Iterator<T> iter = iterable.iterator();

                return new Iterator<C>() {

                    @Override
                    public boolean hasNext() {

                        return iter.hasNext();
                    }

                    @Override
                    public C next() {

                        return iter.next();
                    }
                };
            }
        };
    }

    public static <I, O> Iterable<O> convert(
        final Iterable<I> iterable,
        final IConversor<O> conversor) {


        if (iterable == null) {

            return Collections.emptyList();
        }

        return new Iterable<O>() {

            @Override
            public Iterator<O> iterator() {

                final Iterator<I> iter = iterable.iterator();

                return new Iterator<O>() {

                    @Override
                    public boolean hasNext() {

                        return iter.hasNext();
                    }

                    @Override
                    public O next() {

                        return conversor.converter(iter.next());
                    }
                };
            }
        };
    }
}
