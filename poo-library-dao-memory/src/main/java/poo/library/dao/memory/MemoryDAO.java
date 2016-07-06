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
import java.util.List;

import poo.library.dao.comum.IDAO;
import poo.library.util.IIdentificavel;
import poo.library.util.ISearcheable;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
class MemoryDAO<T> implements IDAO<T> {

    private List<T> storage;

    public MemoryDAO() {

        this.storage = newList();
    }

    private <C> List<C> newList() {

        return new ArrayList<C>();
    }

    @Override
    public Iterable<T> all() {

        return this.storage;
    }

    @Override
    public void delete(T obj) {

        this.storage.remove(obj);
    }

    @Override
    public void save(T obj) {

        if (!this.storage.contains(obj)) {

            this.storage.add(obj);
        }
    }

    @Override
    public int count() {

        return this.storage.size();
    }

    @Override
    public T find(int id) throws ObjetoNaoEncontradoException {

        for (T i : this.storage) {

            if (i instanceof IIdentificavel) {

                if (((IIdentificavel) i).getId() == id) {

                    return i;
                }
            }
        }

        throw new ObjetoNaoEncontradoException(String.format(
            "Objeto de id #%d não encontrado",
            id));
    }

    @Override
    public void deleteById(int id) throws ObjetoNaoEncontradoException {

        T obj = this.find(id);

        this.storage.remove(obj);
    }

    @Override
    public T first() throws ObjetoNaoEncontradoException {

        if (this.storage.isEmpty()) {

            throw new ObjetoNaoEncontradoException("");
        } else {

            return this.storage.get(0);
        }
    }

    @Override
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
}
