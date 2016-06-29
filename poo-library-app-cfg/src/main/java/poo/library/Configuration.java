/*
 * MIT License
 *
 * Copyright (c) 2016 José Nascimento & Juscelino Messias
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
package poo.library;

import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAOFactory;
import poo.library.util.ConfiguracaoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Configuration {

    public static void configure(String[] args) throws ConfiguracaoException {

        try {

            Class<?> cls = Class.forName(args[1]);
            IDAOFactory instance = null;

            if (IDAOFactory.class.isAssignableFrom(cls)) {

                instance = (IDAOFactory) cls.newInstance();

                System.out.println("----------------");
                System.out.println("CONFIGURATION STEP 1 " + cls.getName());
                System.out.println("----------------");
            } else {

                throw new ConfiguracaoException(String.format(
                    "Factory '%s' inválida",
                    cls.getName()));
            }

            DAOFactory.register(instance);

        } catch ( InstantiationException e) {

            ConfiguracaoException.raise(e);

        } catch (IllegalAccessException e) {

            ConfiguracaoException.raise(e);
        } catch (ClassNotFoundException e) {

            ConfiguracaoException.raise(e);
        }
    }
}
