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
import org.javalite.activejdbc.ModelDelegate;

import poo.library.dao.activejdbc.util.Models;
import poo.library.dao.comum.IDAO;
import poo.library.util.IIdentificavel;
import poo.library.util.Iterables;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class GenericDAO<T extends IIdentificavel> implements IDAO<T> {

    protected final Class<? extends T> entityType;
    protected final Class<? extends Model> modelType;

    protected GenericDAO(
        Class<? extends Model> modelType,
        Class<? extends T> proxyType) {

        this.entityType = proxyType;
        this.modelType = modelType;
    }

    @Override
    public Iterable<T> all() {

        return this.all("1 = 1");
    }

    public Iterable<T> all(
        String condition,
        Object... params) {

        Iterable<? extends Model> iter = ModelDelegate.where(
            this.modelType,

            condition,
            params);

        return Iterables.cast(Models.map(
            iter,
            entityType));
    }

    public int delete(
        String condition,
        Object... params) {

        return ModelDelegate.delete(
            modelType,
            condition,
            params);
    }

    @Override
    public void delete(T obj) {

        this.delete(
            "id = ?",
            obj.getId());
    }

    protected T novaInstancia() {

        return Models.novaInstancia(entityType);
    }

    @Override
    public void save(T obj) {

        // MAPPER.map(obj, target);
        Model model = this.map(obj);

        if (obj.getId() == 0) {

            model.setId(null);
        }

        model.saveIt();

        //System.out.println(target);
        //System.out.println(model);

        this.inverseMap(obj, model);
    }

    protected void inverseMap(T obj, Model model) {

        Models.inverseMap(
            obj,
            model);
    }

    protected Model map(T obj) {

        Model model = Models.map(obj, modelType);

        return model;
    }

    @Override
    public int count() {

        return ModelDelegate.count(modelType).intValue();
    }

    @Override
    public T find(int id) throws ObjetoNaoEncontradoException {

        return null;
    }

    @Override
    public void deleteById(int id) throws ObjetoNaoEncontradoException {

        int deleted = this.delete("id = ?", id);

        if (deleted == 0) {

            throw new ObjetoNaoEncontradoException("");
        }
    }

    @Override
    public T first() throws ObjetoNaoEncontradoException {

        Model model = ModelDelegate.findFirst(modelType, "1 = 1");

        if (model == null) {

            throw new ObjetoNaoEncontradoException("");
        } else {

            T obj = this.novaInstancia();

            this.inverseMap(obj, model);

            return obj;
        }
    }
}
