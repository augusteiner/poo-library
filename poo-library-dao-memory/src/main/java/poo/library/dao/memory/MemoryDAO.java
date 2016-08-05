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
import java.util.Collections;
import java.util.List;

import poo.library.comum.IIdentificavel;
import poo.library.dao.comum.IDAO;
import poo.library.util.ISearcheable;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
class MemoryDAO<T> implements IDAO<T> {

    private Class<T> cls;
    private List<T> storage;

    public MemoryDAO(Class<T> cls) {

        this.cls = cls;
        this.storage = newList();
    }

    @Override
    public Iterable<T> all() {

        return this.storage;
    }

    @Override
    public void close() throws Exception {

        this.storage.clear();
        this.storage = null;
    }

    @Override
    public int count() {

        return this.storage.size();
    }

    @Override
    public void delete(T obj) {

        this.storage.remove(obj);
    }

    @Override
    public void deleteById(int id) throws ObjetoNaoEncontradoException {

        T obj = this.find(id);

        this.storage.remove(obj);
    }

    @Override
    public T find(Object id) throws ObjetoNaoEncontradoException {

        for (T obj : this.storage) {

            if (obj instanceof IIdentificavel &&
                id.equals(((IIdentificavel) obj).getId())) {

                return obj;
            }
        }

        throw new ObjetoNaoEncontradoException(String.format(
            "%s #%d não encontrado",

            this.getEntityClass().getSimpleName(),
            id));
    }

    @Override
    public T first() throws ObjetoNaoEncontradoException {

        if (this.storage.isEmpty()) {

            throw new ObjetoNaoEncontradoException(String.format(
                "Nenhum(a) '%s' encontrado(a) - (classe: %s)",

                this.getEntityClass().getSimpleName(),
                this.getEntityClass().getName()));

        } else {

            return this.storage.get(0);
        }
    }

    @Override
    public void flush() {

        //
    }

    @Override
    public Class<T> getEntityClass() {

        return this.cls;
    }

    @Override
    public T reference(Object id) {

        try {

            return this.find(id);

        } catch (ObjetoNaoEncontradoException e) {

            return null;
        }
    }

    @Override
    public void save(T obj) {

        if (!this.storage.contains(obj)) {

            this.storage.add(obj);
        }
    }

    public Iterable<T> search(String term) {

        Collection<T> result = newList();

        for (T i : this.storage) {

            if (i instanceof ISearcheable &&
                ((ISearcheable) i).match(term)) {

                result.add(i);
            }
        }

        return result;
    }

    @Override
    public Iterable<T> search(Object term) {

        if (term instanceof String)
            return this.search(term.toString());
        else
            return Collections.emptyList();
    }

    private <C> List<C> newList() {

        return new ArrayList<C>();
    }
}
