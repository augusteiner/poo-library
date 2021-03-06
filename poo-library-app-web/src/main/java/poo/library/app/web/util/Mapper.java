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

import poo.library.util.IConversor;
import poo.library.util.IMapeador;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
class Mapper<I, O> implements IConversor<O> {

    private Class<O> clsOut;
    private Class<I> clsIn;

    private final IMapeador impl;

    public Mapper(Class<I> clsIn, Class<O> clsOut) {

        this.clsIn = clsIn;
        this.clsOut = clsOut;

        this.impl = novoMapeador();
    }

    @Override
    public O converter(Object input) {

        O output = this.map(input);

        //System.out.println(String.format(
        //    "Mapeado INPUT: %s -> OUTPUT: %s",
        //    input,
        //    output));

        return output;
    }

    @Override
    public void converter(Object input, O output) {

        this.map(input, output);

        //System.out.println(String.format(
        //    "Mapeado INPUT: %s -> OUTPUT: %s",
        //    input,
        //    output));
    }

    public Mapper<O, I> inverso() {

        return new Mapper<O, I>(clsOut, clsIn);
    }

    private static IMapeador novoMapeador() {

        return new DefaultOrikaMapper();
    }

    protected O map(Object input) {

        return this.impl.map(input, this.clsOut);
    }

    protected void map(Object input, O output) {

        // XXX Usando clsOut para ser explícito sobre a conversão
        this.impl.map(input, output, this.clsOut);
    }
}
