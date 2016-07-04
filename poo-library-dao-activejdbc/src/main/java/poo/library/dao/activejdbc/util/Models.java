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

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.ModelDelegate;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
public class Models {

    public static <TModel extends Model, T> Iterable<T> map(
        final Iterable<TModel> modelIter,
        final Class<T> proxyCls) {

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
                            iter.next());

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
        Class<M> modelType) {

        M model = ModelDelegate.create(modelType);
        Collection<String> attrs = ModelDelegate.attributeNames(modelType);

        map(source, model, attrs);

        return model;
    }

    public static <T, M extends Model> void map(
        T source,
        M targetModel,
        Collection<String> attrs) {

        for (String attr : attrs) {

            set(targetModel, source, attr);
        }
    }

    private static <T, M extends Model> void set(M targetModel, T source, String attr) {

        Object value = get(source, attr);

        if (value != null &&
            value.getClass().isEnum()) {

            value = value.toString();
        }

        targetModel.set(
            attr,
            value);
    }

    private static <T> Object get(T source, String attr) {

        Method m = null;
        String methodName = attr.substring(0, 1).toUpperCase() + attr.substring(1);

        try {

            m = source.getClass().getMethod("get" + methodName);

            return m.invoke(source);

        } catch (Exception e) {

            //e.printStackTrace();
        }

        return null;
    }

    public static <T, M extends Model> void inverseMap(
        T target,
        M model) {

        Collection<String> attrs = ModelDelegate.attributeNames(model.getClass());

        inverseMap(target, model, attrs);
    }

    public static <T, M extends Model> void inverseMap(
        T source,
        M targetModel,
        Collection<String> attrs) {

        Method[] methods = source.getClass().getMethods();

        for (String attr : attrs) {

            setA(
                source,
                attr,
                targetModel,
                methods);
        }
    }

    private static void setA(
        Object source,
        String attr,
        Model targetModel,
        Method[] methods) {

        String methodName = attr.substring(0, 1).toUpperCase() + attr.substring(1);

        try {

            for (Method m : methods) {

                if (m.getName().equals("set" + methodName)) {

                    if (m.getParameterCount() == 1) {

                        Class<?> p = m.getParameters()[0].getType();
                        Object value = null;

                        if (p.equals(Integer.TYPE) ||
                            p.equals(Integer.class)) {

                            Integer i = targetModel.getInteger(attr);

                            value = i != null ? i.intValue() : 0;

                        } else if (p.equals(Double.TYPE) ||
                            p.equals(Double.class)) {

                            value = targetModel.getBigDecimal(attr).doubleValue();

                        } else if (p.equals(Date.class)) {

                            value = targetModel.getDate(attr);

                        } else if (p.isEnum()) {

                            for (Object e : p.getEnumConstants()) {

                                if (e.toString().equals(targetModel.get(attr))) {

                                    value = e;

                                    break;
                                }
                            }
                        } else {

                            value = targetModel.get(attr);
                        }

                        m.invoke(source, value);
                    }
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            throw new IllegalArgumentException(attr, e);
        }
    }
}
