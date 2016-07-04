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
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Administrador;
import poo.library.modelo.Usuario;
import poo.library.util.Usuarios;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
class UsuarioSeeder extends Seeder<Usuario>
    implements ISeeder<Usuario> {

    public UsuarioSeeder(IDAO<Usuario> dao) {

        super(dao);
    }

    @Override
    public void seed() {

        this.clear();

        sysoutCentro("Inserindo usuários de teste", 45);

        Administrador admin;

        Usuario[] seed = new Usuario[] {
            admin = new Administrador("José", "11111111111"),
            new Usuario("João", "22222222222"),
            new Usuario("Maria", "33333333333")
        };

        admin.setEndereco("R. das Acácias, 211");

        for (Usuario u : seed) {

            dao.save(u);

            System.out.println(String.format(
                "Inserido %s",
                u));
        }

        Iterable<Usuario> usuarios = dao.all(
            "LOCATE(?, nome) > 0",
            "José");

        sysoutCentro(
            "Usuários com José no nome",
            45);

        for (Usuario u : usuarios) {

            System.out.println(Usuarios.toString(u));

            u.setNome(u.getNome() + " II");

            dao.save(u);
        }
    }

    @Override
    public void clear() {

        dao.delete("1 = 1");
    }
}
