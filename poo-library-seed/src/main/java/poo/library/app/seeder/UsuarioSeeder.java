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

import static poo.library.app.seeder.LocacaoSeeder.*;
import static poo.library.app.App.*;

import poo.library.app.util.ISeeder;
import poo.library.app.util.Seeder;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Administrador;
import poo.library.modelo.Usuario;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.Usuarios;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
class UsuarioSeeder extends Seeder<Usuario>
    implements ISeeder<Usuario> {

    static int firstUsuarioId;

    private Usuario u1;
    private Usuario u2;
    private Usuario u3;

    public UsuarioSeeder(IDAO<Usuario> dao) {

        super(dao);
    }

    @Override
    public void seed() throws FalhaOperacaoException {

        IDAO<Usuario> dao = this.dao;

        this.clear();

        printlnCentro("Inserindo usuários de teste");

        Usuario[] seed = new Usuario[] {
            u1 = new Administrador("José", "11111111111"),
            u2 = new Usuario("João", "22222222211"),
            u3 = new Usuario("Maria", "33333333311")
        };

        u1.setEndereco("R. das Acácias, 211");
        u2.setEndereco("R. das Jabulanes, 70");
        u3.setEndereco("R. do Boro, 10");

        usuario = u1;

        for (Usuario u : seed) {

            dao.save(u);

            System.out.println(String.format(
                "Inserido %s",
                u));
        }

        firstUsuarioId = u1.getId();

        String termo = "Jo";

        printlnCentro(String.format(
            "Buscando usuários por termo: %s",
            termo));

        for (Usuario u : dao.search(termo)) {

            System.out.println(Usuarios.toString(u));

            u.setNome(u.getNome() + " II");

            // dao.save(u);
        }
    }
}
