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
import org.modelmapper.ModelMapper;

import poo.library.comum.IConvertible;
import poo.library.comum.IIdentificavel;
import poo.library.util.ObjetoNaoEncontradoException;
import poo.library.util.Iterables;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class GenericDAO<T extends IIdentificavel, M extends Model & IConvertible<T>> {

    private static ModelMapper MAPPER = new ModelMapper();

    public Iterable<T> all() {

        Iterable<M> iter = this.findAll("1 = 1");

        return Iterables.convertIterable(iter);
    }

    public Iterable<T> all(
        String condition,
        Object... params) {

        Iterable<M> iter = this.findAll(
            condition,
            params);

        return Iterables.convertIterable(iter);
    }

    public void delete(
        String condition,
        Object... params) {

        this.deleteAll(
            condition,
            params);
    }

    public void delete(T obj) {

        this.deleteAll(
            "id = ?",
            new Object[]{ obj.getId() });
    }

    protected abstract void deleteAll(
        String string,
        Object... objects);

    protected abstract Iterable<M> findAll(
        String condition,
        Object... params);

    public T first(
        String condition,
        Object... params) throws ObjetoNaoEncontradoException {

        T item = this.firstOrDefault(
            condition,
            params);

        if (item == null) {

            ObjetoNaoEncontradoException.raise(condition);
        }

        return item;
    }

    public T firstOrDefault(
        String condition,
        Object... params) {

        Iterable<T> iter = this.all(
            condition,
            params);

        for (T item : iter) {

            return item;
        }

        return null;
    }

    protected abstract M novo();

    public void save(T obj) {

        M model = this.novo();

        T target = model.convert();

        MAPPER.map(obj, target);

        if (obj.getId() == 0) {

            model.setId(null);
        }

        model.saveIt();

        //System.out.println(target);
        //System.out.println(model);

        MAPPER.map(target, obj);
    }
}
