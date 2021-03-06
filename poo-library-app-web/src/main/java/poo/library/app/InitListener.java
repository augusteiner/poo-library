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

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import poo.library.dao.comum.DAOFactory;
import poo.library.util.ConfiguracaoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String factory = System.getProperty("factories.dao");

        if (factory == null) {

            factory = "poo.library.dao.jpa.DefaultConnectionDAOFactory";
        }

        try {

            poo.library.Configuration.configure(new String[] {
                "--factories.dao",
                //"poo.library.dao.memory.MemoryDAOFactory"
                //"poo.library.dao.activejdbc.ActiveJdbcDAOFactory"
                //"poo.library.dao.jpa.DefaultConnectionDAOFactory"
                //"poo.library.dao.jpa.MySqlConnectionDAOFactory"
                factory
            });

        } catch (ConfiguracaoException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        DAOFactory.close();
    }
}
