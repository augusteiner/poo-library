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
package poo.library.app.web.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import poo.library.comum.IIdentificavel;
import poo.library.dao.util.IDAOHolder;
import poo.library.util.IConversor;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Inicializador<T> {

    private final Class<T> cls;

    private Class<?> modelCls;
    private Class<?> dtoCls;

    private Inicializador(Class<T> cls) {

        this.cls = cls;
    }

    public static <T> Inicializador<T> init(Class<T> cls) {

        return new Inicializador<T>(cls);
    }

    public void configure(T resource) {

        discoverGenericParams();
    }

    private void discoverGenericParams() {

        for (Type gInter : this.cls.getGenericInterfaces()) {

            if (gInter instanceof ParameterizedType) {

                Class<?> gInterCls = (Class<?>) ((ParameterizedType) gInter).getRawType();
                Type[] typeArgs = ((ParameterizedType) gInter).getActualTypeArguments();

                for (Type typeArg : typeArgs) {

                    if (typeArg instanceof Class) {

                        Class<?> entityCls = (Class<?>) typeArg;

                        if (IIdentificavel.class.isAssignableFrom(entityCls)) {

                            if (IDAOHolder.class.isAssignableFrom(gInterCls)) {

                                dtoCls = entityCls;
                            }

                            if (IConversor.class.isAssignableFrom(gInterCls)) {

                                modelCls = entityCls;
                            }
                        }
                    }
                }
            }
        }
    }
}
