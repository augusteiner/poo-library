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
package poo.library.dao;

import org.javalite.activejdbc.Base;

import poo.library.comum.IUsuario;
import poo.library.dao.activejdbc.impl.UsuarioDAO;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class App {

    public static void main(String[] args) {

        Base.open(
            "com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://localhost:4040/biblioteca?serverTimezone=UTC&nullNamePatternMatchesAll=true",
            "biblioteca",
            "123456");

        IDAO<IUsuario> dao = new UsuarioDAO();

        dao.delete("1 = 1");

        printCentro("Inserindo usuários de teste", 45);

        Usuario[] seed = new Usuario[] {
            new Usuario("José", "11111111111"),
            new Usuario("João", "22222222222"),
            new Usuario("Maria", "33333333333")
        };

        for (Usuario u : seed) {

            dao.save(u);

            System.out.println(String.format("Inserido %s", u));
        }

        Iterable<IUsuario> usuarios = dao.findAll(
            "LOCATE(?, nome) > 0",
            "José");

        printCentro("Usuários com José no nome", 45);

        for (IUsuario u : usuarios) {

            System.out.println(u);

            u.setNome(u.getNome() + " II");

            dao.save(u);
        }
    }

    public static void printCentro(String texto, int size) {

        texto = texto.trim();
        StringBuilder header = new StringBuilder(size);
        StringBuilder body = new StringBuilder(Math.max(size, texto.length()));

        for (int i = 0; i < size; i++)
            header.append("-");

        for (int i = 0; i < (size - texto.length()) / 2; i++) {

            body.append(" ");
        }

        for (int i = 0; i < texto.length(); i++) {

            body.append(texto.charAt(i));
        }

        System.out.println(header);

        System.out.println(body);

        System.out.println(header);
    }
}
