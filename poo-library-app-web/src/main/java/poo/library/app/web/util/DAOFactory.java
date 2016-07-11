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

        private Class<O> clsOut;
        private Class<I> clsIn;

        public Conversor(Class<I> clsIn, Class<O> clsOut) {

            this.clsIn = clsIn;
            this.clsOut = clsOut;
        }

        private ModelMapper getMapper() {

            ModelMapper mapper = new ModelMapper();

            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            return mapper;
        }

        @Override
        public O convert(I input) {

            O output = this.getMapper().map(input, this.clsOut);

            return output;
        }

        @Override
        public void convert(I input, O output) {

            this.getMapper().map(input, output);
        }

        public IConversor<O, I> inverse() {

            return new Conversor<O, I>(clsOut, clsIn);
        }
    }

    public static <T, M> IDAO<T> createNew(
        IDAO<M> dao,
        Class<M> clsModel,
        Class<T> clsDto) {

        Conversor<T, M> conversor = new Conversor<T, M>(clsDto, clsModel);

        return new DAOConversor<M, T>(dao, conversor.inverse(), conversor);
    }
}
