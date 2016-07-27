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
package poo.library.app.web.util;

import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Usuario;
import poo.library.util.IConversor;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
public class Conversores {

    public static <I, O> O converter(I input, Class<O> clsOut) {

        return conversor(clsOut).converter(input);
    }

    @SuppressWarnings("unchecked")
    public static <I, O> void converter(I input, O output) {

        if (output == null) {

            throw new IllegalArgumentException(
                "O argumento output não pode ser nulo");
        }

        IConversor<O> conversor = conversor((Class<O>) output.getClass());

        conversor.converter(input, output);
    }

    public static <D> IConversor<D> conversor(Class<D> clsOut) {

        return conversor(Object.class, clsOut);
    }

    @SuppressWarnings("unchecked")
    public static <T, D> IConversor<D> conversor(
        Class<T> clsIn,
        Class<D> clsOut) {

        IConversor<D> out;

        if (clsIn == null)
            throw new IllegalArgumentException("Argumento clsIn não deve ser nulo");

        if (clsOut == null)
            throw new IllegalArgumentException("Argumento clsOut não deve ser nulo");

        if (clsOut.equals(Usuario.class)) {

            out = (IConversor<D>) new UsuarioMapperInverso<D>(clsOut);

        } else if (clsOut.equals(ItemAcervo.class)) {

            out = (IConversor<D>) new ItemAcervoMapperInverso<D>(clsOut);

        } else {

            out = new Mapper<T, D>(clsIn, clsOut);
        }

        return out;
    }
}
