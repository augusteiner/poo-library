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

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import poo.library.dao.comum.IDAO;
import poo.library.util.IConversor;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class DAOFactory {

    private static class Conversor<I, O> implements IConversor<I, O> {

        private ModelMapper mapper = new ModelMapper();
        private Class<O> cls;

        public Conversor(Class<O> cls) {

            this.cls = cls;

            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        }

        @Override
        public O convert(I input) {

            O output = mapper.map(input, this.cls);

            return output;
        }

        @Override
        public void convert(I input, O output) {

            mapper.map(input, output);
        }
    }

    public static <I> IConversor<I, I> newConversor(Class<I> cls) {

        return new Conversor<I, I>(cls);
    }

    public static <I> IDAO<I> createNew(
        Class<I> cls,
        IConversor<I, I> conversor) {

        return createNew(
            cls,
            cls,
            conversor,
            conversor);
    }

    public static <I> IDAO<I> createNew(
        Class<I> cls) {

        return createNew(
            cls,
            new Conversor<I, I>(cls));
    }

    public static <I, O> IDAO<O> createNew(
        Class<I> clsIn,
        Class<O> clsOut,
        IConversor<I, O> conversorIn,
        IConversor<O, I> conversorOut) {

        IDAO<I> dao = poo.library.dao.comum.DAOFactory.createNew(clsIn);

        return new DAOConversor<I, O>(
            dao,
            conversorIn,
            conversorOut);
    }
}
