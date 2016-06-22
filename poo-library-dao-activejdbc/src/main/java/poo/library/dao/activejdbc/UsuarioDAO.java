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

import poo.library.dao.activejdbc.model.UsuarioModel;
import poo.library.dao.comum.IDAO;
import poo.library.dao.comum.Utils;
import poo.library.modelo.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class UsuarioDAO implements IDAO<Usuario> {

    @Override
    public void delete(
        String condition,
        Object... params) {

        UsuarioModel.delete(
            condition,
            params);
    }

    @Override
    public void delete(Usuario obj) {

        UsuarioModel.delete(
            "id = ?",
            obj.getId());
    }

    @Override
    public Iterable<Usuario> findAll() {

        Iterable<UsuarioModel> iter = UsuarioModel.findAll();

        return Utils.mapIterable(iter);
    }

    @Override
    public Iterable<Usuario> findAll(
        String condition,
        Object... params) {

        Iterable<UsuarioModel> iter = UsuarioModel.where(
            condition,
            params);

        return Utils.mapIterable(iter);
    }

    @Override
    public void save(Usuario obj) {

        Model model = UsuarioModel.from(obj);

        if (obj.getId() == 0)
            model.insert();
        else
            model.save();
    }
}
