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
package poo.library.dao.activejdbc.util;

import java.util.Iterator;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.ModelDelegate;
import org.modelmapper.ModelMapper;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
public class Models {

    public static <TModel extends Model, T> Iterable<T> map(
        final Iterable<TModel> modelIter,
        final Class<T> proxyCls,
        final ModelMapper mapper) {

        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {

                final Iterator<TModel> iter = modelIter.iterator();

                return new Iterator<T>(){

                    @Override
                    public boolean hasNext() {

                        return iter.hasNext();
                    }

                    @Override
                    public T next() {

                        T obj  = novaInstancia(proxyCls);

                        inverseMap(
                            obj,
                            iter.next(),
                            mapper);

                        return obj;
                    }
                };
            }
        };
    }

    public static <T> T novaInstancia(Class<T> cls) {

        try {

            return cls.newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();
        }

        return null;

    }

    public static <T, M extends Model> M map(
        T source,
        Class<M> modelType,
        ModelMapper mapper) {

        M model = ModelDelegate.create(modelType);

        map(source, model, mapper);

        return model;
    }

    public static <T, M extends Model> T map(
        T source,
        M target,
        ModelMapper mapper) {

        return null;
    }

    public static <T, M extends Model> void inverseMap(
        T target,
        M source,
        ModelMapper mapper) {

        ModelDelegate.attributeNames(source.getClass());
    }
}
