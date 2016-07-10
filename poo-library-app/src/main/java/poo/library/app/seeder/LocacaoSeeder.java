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
import poo.library.dao.comum.IDAO;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Locacao;
import poo.library.modelo.Usuario;
import poo.library.util.FalhaOperacaoException;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
class LocacaoSeeder extends Seeder<Locacao> implements ISeeder<Locacao> {

    static Usuario usuario;

    static ItemAcervo item1;
    static ItemAcervo item2;

    public LocacaoSeeder(IDAO<Locacao> dao) {

        super(dao);
    }

    @Override
    public void seed() throws FalhaOperacaoException {

        Instant tresDias = Instant.now().plus(3, ChronoUnit.DAYS);
        Date dataDevolucao = Date.from(tresDias);

        Locacao[] locacoes = new Locacao[] {
            new Locacao(dataDevolucao, item1, usuario),
            new Locacao(dataDevolucao, item2, usuario)
        };

        for (Locacao l : locacoes) {

            this.dao.save(l);
        }
    }
}
