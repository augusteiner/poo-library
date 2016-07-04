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
package poo.library.app;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import poo.library.app.util.ISeeder;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.util.ConfiguracaoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class App {

    public static void configure() {

        try {

            poo.library.Configuration.configure(new String[] {
                "--factories.dao",
                //"poo.library.dao.memory.DAOFactory"
                "poo.library.dao.activejdbc.DAOFactory"
            });

            DAOFactory.connect();

        } catch (ConfiguracaoException e) {

            e.printStackTrace();

            System.exit(1);

            return;
        }
    }

    public static void main(String[] args) {

        configure();

        seed();
    }

    private static void seed() {

        Reflections r = new Reflections(
            App.class.getPackage().getName(),
            new SubTypesScanner(false));

        for (Class<?> cls : r.getSubTypesOf(ISeeder.class)) {

            if (!Modifier.isPublic(cls.getModifiers()))
                continue;

            ParameterizedType t = ((ParameterizedType) (cls.getGenericInterfaces()[0]));

            Class<?> inter = ((Class<?>) t.getActualTypeArguments()[0]);

            Object dao = DAOFactory.createNew(inter);

            if (dao == null) {

                System.out.println(String.format(
                    "DAO \'%s\' não encontrado, continuando!",
                    inter));

                continue;
            }

            try {

                ISeeder<?> seeder = (ISeeder<?>) cls.getConstructor(IDAO.class).newInstance(dao);

                seeder.seed();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    public static void sysoutCentro(String texto, int size) {

        texto = texto.trim();
        StringBuilder header = new StringBuilder(size);
        StringBuilder body = new StringBuilder(Math.max(size, texto.length()));

        for (int i = 0; i < size; i++)
            header.append("-");

        for (int i = 0; i < (size - texto.length()) / 2; i++) {

            body.append(" ");
        }

        for (int i = 0; i < texto.length(); i++) {

            body.append(texto.charAt(i));
        }

        System.out.println(header);

        System.out.println(body);

        System.out.println(header);
    }
}
