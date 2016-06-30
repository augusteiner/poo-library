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

import java.text.ParseException;
import java.util.Iterator;

import javax.swing.text.MaskFormatter;

import poo.library.comum.IConvertible;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Iterables {

    static class CpfFormatter extends MaskFormatter {

        private static final long serialVersionUID = -5181943190057841460L;

        public CpfFormatter() {

            this.setValueContainsLiteralCharacters(false);

            try {

                this.setMask(CPF_MASK);

            } catch (ParseException e) {

                e.printStackTrace();
            }
        }
    }

    public static final String CPF_MASK = "###.###.###-##";

    private static final MaskFormatter CPF_FORMATTER = new CpfFormatter();

    public static String formatarCpf(String cpf) throws ParseException {

        return CPF_FORMATTER.valueToString(cpf);
    }

    public static <M extends IConvertible<T>, T> Iterable<T> convertIterable(final Iterable<M> iterable) {

        final Iterator<M> iter = iterable.iterator();

        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {

                return new Iterator<T>() {

                    @Override
                    public boolean hasNext() {

                        return iter.hasNext();
                    }

                    @Override
                    public T next() {

                        M from = iter.next();

                        return from.convert();
                    }
                };
            }
        };
    }

    public static <T extends C, C> Iterable<C> castIterable(Iterable<T> iterable) {

        final Iterator<T> iter = iterable.iterator();

        return new Iterable<C>() {

            @Override
            public Iterator<C> iterator() {

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
}
