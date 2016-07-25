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

import static poo.library.app.App.printlnCentro;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import poo.library.app.util.ISeeder;
import poo.library.app.util.Seeder;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Locacao;
import poo.library.modelo.Reserva;
import poo.library.modelo.Usuario;
import poo.library.util.Bibliotecas;
import poo.library.util.FalhaOperacaoException;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
public class BibliotecaSeeder extends Seeder<Biblioteca>
    implements ISeeder<Biblioteca> {

    private Biblioteca b1;

    private ISeeder<ItemAcervo> itemSeeder;
    private ISeeder<Reserva> reservaSeeder;
    private ISeeder<Usuario> usuarioSeeder;
    private ISeeder<Locacao> locacaoSeeder;

    public BibliotecaSeeder(IDAO<Biblioteca> dao) {

        super(dao);

        b1 = new Biblioteca("Book Library", 2.5);
    }

    @Override
    public List<Biblioteca> getList() {

        return Arrays.asList(b1);
    }

    @Override
    public void seed() throws FalhaOperacaoException {

        //this.list();

        //if (true) return;

        ItemAcervo i1;
        Usuario u1;

        this.clear();

        for (Biblioteca lib : this.getList()) {

            lib.setEndereco("R. das Magnólias, 21");

            lib.setQteDiasLocacao(14);
            lib.setQteDiasValidadeReserva(3);

            this.dao.save(lib);
        }

        usuarioSeeder = new UsuarioSeeder(dao(Usuario.class));
        itemSeeder = new ItemAcervoSeeder(b1);

        i1 = itemSeeder.getList().get(0);
        u1 = usuarioSeeder.getList().get(0);

        for (Biblioteca lib : this.dao.all()) {

            printlnCentro(Bibliotecas.toString(lib));
        }

        this.itemSeeder.seed();

        this.dao.flush();

        this.usuarioSeeder.seed();

        reservaSeeder = new ReservaSeeder(dao(Reserva.class), b1, i1, u1);
        locacaoSeeder = new LocacaoSeeder(dao(Locacao.class), b1, i1, u1);

        this.reservaSeeder.seed();
        this.locacaoSeeder.seed();

        this.dao.flush();

        this.list();
    }

    private void list() {

        for (Biblioteca lib : this.dao.all()) {

            printlnCentro(Bibliotecas.toString(lib));

            System.out.println(String.format(
                "%s (size: %d)",

                lib.getLocacoes().getClass(),
                ((Collection<?>) lib.getLocacoes()).size()));

            for (Locacao l : lib.getLocacoes()) {

                System.out.println(String.format(
                    "#%s :: %s: %s",

                    l.getId(),

                    l.getUsuario(),
                    l.getItemAcervo()));
            }
        }
    }

    private static <T> IDAO<T> dao(Class<T> cls) {

        return DAOFactory.createNew(cls);
    }
}
