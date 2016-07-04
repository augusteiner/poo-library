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
package poo.library.dao.util;

import java.util.Collection;
import java.util.Iterator;

import poo.library.dao.comum.IDAO;
import poo.library.util.ICollectionMap;
import poo.library.util.IIdentificavel;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class DAOCollectionMap<K extends IIdentificavel, V extends IIdentificavel>
    implements ICollectionMap<K, V> {

    private IDAO<K> keys;
    private IDAO<V> values;
    private String navigationProp;

    public DAOCollectionMap(
        IDAO<K> keys,
        IDAO<V> values,
        String navigationProp) {

        this.keys = keys;
        this.values = values;

        this.navigationProp = navigationProp;
    }

    @Override
    public boolean add(V obj) {

        this.values.save(obj);

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {

        return false;
    }

    @Override
    public void clear() {

        this.values.delete("1 = 1");
    }

    @Override
    public boolean contains(Object obj) {

        if (obj instanceof IIdentificavel) {

            return this.values.count(
                "id = ?",
                ((IIdentificavel) obj).getId()) > 0;
        } else {

            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        return false;
    }

    public boolean containsKey(K key) {

        return this.keys.count("id = ?", key.getId()) > 0;
    }

    @Override
    public V first(K key) {

        try {

            return this.values.first(
                String.format(
                    "%sId = ?",
                    this.getNavigationProp()),
                key.getId());

        } catch (ObjetoNaoEncontradoException e) {

            return null;
        }
    }

    private String getNavigationProp() {

        return this.navigationProp;
    }

    @Override
    public boolean isEmpty() {

        return this.size() == 0;
    }

    @Override
    public Iterator<V> iterator() {

        return this.values.all().iterator();
    }

    @Override
    public Iterable<K> keys() {

        return this.keys.all();
    }

    @Override
    public boolean remove(Object obj) {

        if (obj instanceof IIdentificavel) {

            this.values.delete(
                "id = ?",
                ((IIdentificavel) obj).getId());

            return true;
        } else {

            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        return false;
    }

    @Override
    public int size() {

        return this.values.count();
    }

    @Override
    public Object[] toArray() {

        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {

        return null;
    }

    @Override
    public Iterable<V> values(K key) {

        return this.values.all(
            String.format(
                "%sId = ?",
                this.getNavigationProp()),
            key.getId());
    }

    @Override
    public Collection<V> asList() {

        return this;
    }
}
