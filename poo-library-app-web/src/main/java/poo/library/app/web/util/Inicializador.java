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

import static poo.library.app.web.util.DAOFactory.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import poo.library.comum.IIdentificavel;
import poo.library.dao.comum.DAOFactory;
import poo.library.util.IConversor;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Inicializador<D, T, M> {

    public static class InicializadorHolder<D> {

        private Inicializador<D, ?, ?> init;

        public InicializadorHolder(Inicializador<D, ?, ?> init) {

            init.discoverGenericParams();

            this.init = init;
        }

        public IDAO<D> novoDAO() {

            return this.init.novoDAO();
        }

        public void configure(IDAOHolder<D> usuarioResource) {

            this.init.configure(usuarioResource);
        }
    }

    private final Class<T> cls;

    private Class<M> modelCls;
    private Class<D> dtoCls;

    private Inicializador(Class<T> cls) {

        this.cls = cls;

        this.dtoCls = null;
        this.modelCls = null;
    }

    @SuppressWarnings("unchecked")
    public static <D, T extends IDAOHolder<D>> void init(T obj) {

        InicializadorHolder<D> holder;

        holder = init((Class<? extends T>) obj.getClass());

        holder.configure(obj);
    }

    public static <D, T extends IDAOHolder<D>> InicializadorHolder<D> init(Class<T> cls) {

        Inicializador<D, T, ?> init = new Inicializador<D, T, Object>(cls);

        return new InicializadorHolder<D>(init);
    }

    public IDAO<D> novoDAO() {

        if (dtoCls != null && modelCls != null) {

            System.out.println(String.format(
                "Instanciando novo DAO<%s>",
                modelCls.getName()));

            return newDAO(DAOFactory.createNew(modelCls), modelCls, dtoCls);
        }

        return null;
    }

    public void configure(IDAOHolder<D> resource) {

        if (dtoCls != null && modelCls != null) {

            System.out.println(String.format(
                "Instanciando novo DAO<%s>",
                modelCls.getName()));

            resource.setDAO(novoDAO());
        }
    }

    @SuppressWarnings("unchecked")
    private void discoverGenericParams() {

        for (Type gInter : cls.getGenericInterfaces()) {

            if (gInter instanceof ParameterizedType) {

                Class<?> gInterCls = (Class<?>) ((ParameterizedType) gInter).getRawType();
                Type[] typeArgs = ((ParameterizedType) gInter).getActualTypeArguments();

                for (Type typeArg : typeArgs) {

                    if (typeArg instanceof Class) {

                        Class<?> entityCls = (Class<?>) typeArg;

                        if (IIdentificavel.class.isAssignableFrom(entityCls)) {

                            if (IDAOHolder.class.isAssignableFrom(gInterCls)) {

                                System.out.println(String.format(
                                    "Encontrada classe de dto: %s",
                                    entityCls));

                                dtoCls = (Class<D>) entityCls;
                            }

                            if (IConversor.class.isAssignableFrom(gInterCls)) {

                                System.out.println(String.format(
                                    "Encontrada classe de modelo: %s",
                                    entityCls));

                                modelCls = (Class<M>) entityCls;
                            }
                        }
                    }
                }
            }
        }
    }
}
