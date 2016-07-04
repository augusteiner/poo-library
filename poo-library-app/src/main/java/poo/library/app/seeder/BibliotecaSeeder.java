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

import static poo.library.app.App.sysoutCentro;

import poo.library.app.util.ISeeder;
import poo.library.app.util.Seeder;
import poo.library.comum.IBiblioteca;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Reserva;
import poo.library.modelo.Usuario;
import poo.library.util.Bibliotecas;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
public class BibliotecaSeeder extends Seeder<Biblioteca>
    implements ISeeder<Biblioteca> {

    private static int bibliotecaId;
    private final ISeeder<ItemAcervo> itemSeeder;
    private final ISeeder<Reserva> reservaSeeder;
    private final ISeeder<Usuario> usuarioSeeder;

    public static int getBibliotecaId() {

        return bibliotecaId;
    }

    public BibliotecaSeeder(IDAO<Biblioteca> dao) {

        super(dao);

        this.itemSeeder = new ItemAcervoSeeder(DAOFactory.createNew(ItemAcervo.class));
        this.reservaSeeder = new ReservaSeeder(DAOFactory.createNew(Reserva.class));
        this.usuarioSeeder = new UsuarioSeeder(DAOFactory.createNew(Usuario.class));
    }

    @Override
    public void seed() {

        this.reservaSeeder.clear();
        this.usuarioSeeder.clear();
        this.itemSeeder.clear();
        this.clear();

        Biblioteca[] libs = new Biblioteca[] {
            new Biblioteca("Book Library", 2.5)
        };

        for (Biblioteca lib : libs) {

            this.dao.save(lib);
        }

        bibliotecaId = libs[0].getId();

        for (IBiblioteca lib : this.dao.all()) {

            sysoutCentro(Bibliotecas.toString(lib), 50);
        }

        this.itemSeeder.seed();
        this.usuarioSeeder.seed();
        this.reservaSeeder.seed();
    }

    @Override
    public void clear() {

        this.dao.delete("1 = 1");
    }
}
