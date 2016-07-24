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
package poo.library.app.seeder;

import java.util.Arrays;
import java.util.List;

import poo.library.app.util.ISeeder;
import poo.library.app.util.Seeder;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Reserva;
import poo.library.modelo.Usuario;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.ItemIndisponivelException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
class ReservaSeeder extends Seeder<Reserva> implements ISeeder<Reserva> {

    private Reserva r1;

    public ReservaSeeder(
        IDAO<Reserva> dao,

        Biblioteca b1,
        ItemAcervo i1,
        Usuario u1) {

        super(dao);

        try {

            this.r1 = b1.reservar(i1, u1);

        } catch (ItemIndisponivelException e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<Reserva> getList() {

        return Arrays.asList(r1);
    }

    @Override
    public void seed() throws FalhaOperacaoException {

        for (Reserva r : this.getList()) {

            System.out.println(r);
            // this.dao.save(r);
        }
    }
}
