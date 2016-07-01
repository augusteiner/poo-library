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

import org.modelmapper.ModelMapper;

import poo.library.dao.activejdbc.util.IModel;
import poo.library.util.IIdentificavel;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class GenericDAO<T extends IIdentificavel & IModel<T>> {

    private static ModelMapper MAPPER = new ModelMapper();

    public Iterable<T> all() {

        return this.findAll("1 = 1");
    }

    public Iterable<T> all(
        String condition,
        Object... params) {

        return this.findAll(
            condition,
            params);
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

    protected abstract Iterable<T> findAll(
        String condition,
        Object... params);

    public T first() throws ObjetoNaoEncontradoException {

        return this.first("1 = 1");
    }

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

    public T firstOrDefault() {

        return this.firstOrDefault("1 = 1");
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

    protected abstract T novo();

    public void save(T obj) {

        final T target = this.novo();

        // T target = model.converter();

        MAPPER.map(obj, target);

        if (obj.getId() == 0) {

            target.setId(null);
        }

        target.saveIt();

        //System.out.println(target);
        //System.out.println(model);

        MAPPER.map(target, obj);
    }
}
