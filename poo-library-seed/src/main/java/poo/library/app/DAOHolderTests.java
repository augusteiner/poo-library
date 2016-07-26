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
package poo.library.app;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import poo.library.comum.IIdentificavel;
import poo.library.dao.util.IDAOHolder;
import poo.library.util.ConfiguracaoException;
import poo.library.util.IConversor;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class DAOHolderTests {

    private static abstract class TestModel implements IIdentificavel { }

    private static abstract class TestDTO implements IIdentificavel { }

    private static abstract class TestResource
        implements IDAOHolder<TestDTO>, IConversor<TestModel> { }

    public static void main(String[] args) throws ConfiguracaoException {

        discoverClasses(TestResource.class);
    }

    private static void discoverClasses(Class<TestResource> cls) {

        Class<?> dtoCls = null;
        Class<?> modelCls = null;

        for (Type gInter : cls.getGenericInterfaces()) {

            if (gInter instanceof ParameterizedType) {

                Class<?> gInterCls = (Class<?>) ((ParameterizedType) gInter).getRawType();
                Type[] typeArgs = ((ParameterizedType) gInter).getActualTypeArguments();

                for (Type typeArg : typeArgs) {

                    if (typeArg instanceof Class) {

                        Class<?> entityCls = (Class<?>) typeArg;

                        if (IIdentificavel.class.isAssignableFrom(entityCls)) {

                            if (IDAOHolder.class.isAssignableFrom(gInterCls)) {

                                dtoCls = entityCls;

                                System.out.println(String.format(
                                    "Encontrada classe de dto: %s",
                                    dtoCls));
                            }

                            if (IConversor.class.isAssignableFrom(gInterCls)) {

                                modelCls = entityCls;

                                System.out.println(String.format(
                                    "Encontrada classe de modelo: %s",
                                    modelCls));
                            }
                        }
                    }
                }
            }
        }
    }
}
