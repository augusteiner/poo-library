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

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.javalite.activejdbc.Model;

import poo.library.comum.IConvertible;
import poo.library.comum.IIdentificavel;
import poo.library.comum.Utils;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class GenericDAO<T extends IIdentificavel, M extends Model & IConvertible<T>> {

    protected BiFunction<String, Object[], Iterable<M>> find;
    protected BiConsumer<String, Object[]> delete;

    public void delete(
        String condition,
        Object... params) {

        this.delete.accept(
            condition,
            params);
    }

    public void delete(T obj) {

        this.delete.accept(
            "id = ?",
            new Object[]{ obj.getId() });
    }

    public Iterable<T> findAll() {

        return Utils.mapIterable(this.find.apply(
            "1 = 1",
            null));
    }

    public Iterable<T> findAll(
        String condition,
        Object... params) {

        Iterable<M> iter = this.find.apply(
            condition,
            params);

        return Utils.mapIterable(iter);
    }

    protected abstract M from(T obj);

    public void save(T obj) {

        M model = this.from(obj);

        if (obj.getId() == 0) {
            model.insert();
        } else {
            model.save();
        }
    }
}
