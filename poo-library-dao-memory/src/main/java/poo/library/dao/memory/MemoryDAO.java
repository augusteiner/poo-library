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
package poo.library.dao.memory;

import java.util.ArrayList;
import java.util.Collection;

import poo.library.dao.comum.IDAO;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
class MemoryDAO<T> implements IDAO<T> {

    private Collection<T> storage;

    public MemoryDAO() {

        this.storage = new ArrayList<T>();
    }

    @Override
    public Iterable<T> all() {

        return this.storage;
    }

    @Override
    public Iterable<T> all(String condition, Object... params) {

        return this.all();
    }

    @Override
    public void delete(String condition, Object... params) {

        this.storage.clear();
    }

    @Override
    public void delete(T obj) {

        this.storage.remove(obj);
    }

    @Override
    public T first() throws ObjetoNaoEncontradoException {

        return this.first("");
    }

    @Override
    public T first(String condition, Object... params)
        throws ObjetoNaoEncontradoException {

        T item = this.firstOrDefault(condition, params);

        if (item == null) {

            ObjetoNaoEncontradoException.raise(condition);
        }

        return item;
    }

    @Override
    public T firstOrDefault() {

        return this.firstOrDefault("");
    }

    @Override
    public T firstOrDefault(String condition, Object... params) {

        Iterable<T> iter = this.all(condition, params);

        for (T item : iter) {

            return item;
        }

        return null;
    }

    @Override
    public void save(T obj) {

        if (!this.storage.contains(obj)) {

            this.storage.add(obj);
        }
    }
}
