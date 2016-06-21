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
package poo.library.dao.activejdbc;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import poo.library.modelo.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Table("usuario")
public class UsuarioAR extends Model implements IAdapter<Usuario> {

    @Override
    public void _delete(Usuario obj) {

        //
    }

    @Override
    public Iterable<Usuario> _findAll() {

        Iterable<UsuarioAR> iter = UsuarioAR.findAll();

        return Utils.mapIterable(
            iter,
            Usuario.class);
    }

    @Override
    public void _save(Usuario obj) {

        //
    }

    @Override
    public Iterable<Usuario> _where(
        String subquery,
        Object... params) {

        Iterable<UsuarioAR> iter = UsuarioAR.where(
            subquery,
            params);

        return Utils.mapIterable(
            iter,
            Usuario.class);
    }

    @Override
    public Usuario map(Model from) {

         return new Usuario(
             from.getInteger("id"),
             from.getString("nome"),
             from.getString("cpf"));
    }
}
