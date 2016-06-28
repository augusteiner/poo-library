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
package poo.library.dao.activejdbc.impl;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import poo.library.comum.IUsuario;
import poo.library.dao.activejdbc.GenericDAO;
import poo.library.dao.activejdbc.model.UsuarioModel;
import poo.library.dao.comum.IDAO;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class UsuarioDAO extends GenericDAO<IUsuario, UsuarioModel> implements IDAO<IUsuario> {

    public UsuarioDAO() {

        this.delete = new BiConsumer<String, Object[]>() {

            @Override
            public void accept(String subquery, Object[] params) {

                UsuarioModel.delete(subquery, params);
            }
        };

        this.find = new BiFunction<String, Object[], Iterable<UsuarioModel>>(){

            @Override
            public Iterable<UsuarioModel> apply(String subquery, Object[] params) {

                return UsuarioModel.find(subquery, params);
            }
        };
    }

    @Override
    protected UsuarioModel makeNew() {

        return new UsuarioModel();
    }
}
