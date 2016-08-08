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

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.generator.JavassistCompilerStrategy;
import ma.glasnost.orika.metadata.TypeFactory;
import ma.glasnost.orika.unenhance.HibernateUnenhanceStrategy;
import poo.library.util.IMapeador;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class DefaultOrikaMapper implements IMapeador {

    private final MapperFacade mapper;

    public DefaultOrikaMapper() {

        DefaultMapperFactory.Builder factoryBuilder = new DefaultMapperFactory.Builder();

        factoryBuilder.compilerStrategy(new JavassistCompilerStrategy());
        factoryBuilder.unenhanceStrategy(new HibernateUnenhanceStrategy());

        MapperFactory mapperFactory = factoryBuilder.build();

        this.mapper = mapperFactory.getMapperFacade();
    }

    @Override
    public <O> O map(Object input, Class<O> clsOut) {

        return mapper.map(
            input,
            null,
            TypeFactory.valueOf(clsOut));
    }

    @Override
    public <O> void map(Object input, O output) {

        mapper.map(input, output);

        throw new UnsupportedOperationException();
    }

    @Override
    public <O> void map(Object input, O output, Class<O> clsOut) {

        mapper.map(
            input,
            output,
            null,
            TypeFactory.valueOf(clsOut));
    }
}
