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

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import poo.library.app.util.ISeeder;
import poo.library.app.util.Seeder;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Reserva;
import poo.library.modelo.Usuario;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
class ReservaSeeder extends Seeder<Reserva> implements ISeeder<Reserva> {

    public ReservaSeeder(IDAO<Reserva> dao) {

        super(dao);
    }

    @Override
    public void seed() {

        Instant instant = Instant.now().plus(3, ChronoUnit.DAYS);

        int usuarioId;
        int itemAcervoId;

        Date validaAte = Date.from(instant);

        try {

            usuarioId = DAOFactory.createNew(Usuario.class).first().getId();
            itemAcervoId = DAOFactory.createNew(ItemAcervo.class).first().getId();

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            return;
        }

        Reserva[] reservas = new Reserva[] {
            new Reserva(validaAte, itemAcervoId, usuarioId), };

        for (Reserva r : reservas) {

            this.dao.save(r);
        }
    }
}
