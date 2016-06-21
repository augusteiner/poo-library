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

import poo.library.dao.activejdbc.DAOImpl;
import poo.library.dao.activejdbc.IAdapter;
import poo.library.dao.activejdbc.UsuarioAR;
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

        IAdapter<Usuario> adapter = new UsuarioAR();

        IDAO<Usuario> dao = new DAOImpl<Usuario>(adapter);

        Iterable<Usuario> usuarios = dao.where(
            "nome = ?",
            "jose");

        for (Usuario u : usuarios) {

            System.out.println(u);
        }
    }
}
