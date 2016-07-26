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

import poo.library.comum.IIdentificavel;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.dao.util.IDAOHolder;
import poo.library.modelo.Usuario;
import poo.library.util.ConfiguracaoException;
import poo.library.util.IConversor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class DAOHolderTests {

    private static class UsuarioDTO implements IIdentificavel {

        @Override
        public int getId() {

            return 0;
        }
    }

    private static class UsuarioResource implements IDAOHolder<UsuarioDTO>, IConversor<Usuario> {

        @Override
        public Usuario converter(Object input) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void converter(Object input, Usuario output) {
            // TODO Auto-generated method stub

        }

        @Override
        public IDAO<UsuarioDTO> getDAO() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void setDAO(IDAO<UsuarioDTO> dao) {
            // TODO Auto-generated method stub

        }

    }

    /**
     * @param args
     * @throws ConfiguracaoException
     */
    public static void main(String[] args) throws ConfiguracaoException {

        Class<?> dtoCls = null;
        Class<?> modelCls = null;

        for (Type gInter : UsuarioResource.class.getGenericInterfaces()) {

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

        System.out.println("DTO Class: " + dtoCls);
        System.out.println("Model Class: " + modelCls);
    }
}
