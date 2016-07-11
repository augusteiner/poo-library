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

import poo.library.comum.IReserva;
import poo.library.modelo.Reserva;
import poo.library.util.IConversor;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class ConversorReserva<I extends IReserva> implements IConversor<I, Reserva> {

    private IConversor<I, Reserva> conversor;

    public ConversorReserva(IConversor<I, Reserva> conversor) {

        this.conversor = conversor;
    }

    @Override
    public Reserva convert(I input) {

        Reserva reserva = new Reserva();

        this.convert(input, reserva);

        return reserva;
    }

    @Override
    public void convert(I input, Reserva output) {

        this.conversor.convert(input, output);

        output.setUsuario(null);
        output.setItemAcervo(null);
    }

    public static IConversor<Reserva, Reserva> makeNew() {

        return new ConversorReserva<Reserva>(DAOFactory.newConversor(Reserva.class));
    }
}
