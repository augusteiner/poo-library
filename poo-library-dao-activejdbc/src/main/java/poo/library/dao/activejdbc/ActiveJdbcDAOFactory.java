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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Hashtable;
import java.util.Map;

import org.javalite.activejdbc.Base;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import poo.library.dao.comum.IDAO;
import poo.library.dao.comum.IDAOFactory;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class ActiveJdbcDAOFactory implements IDAOFactory {

    private static final Map<Type, Class<?>> registry = new Hashtable<Type, Class<?>>();

    static {

        discoverImpls(ActiveJdbcDAOFactory.class.getPackage());
    }

    private static void discoverImpls(Package pkg) {

        Reflections ref = new Reflections(
            pkg.getName(),
            new SubTypesScanner(false));

        Iterable<? extends Class<?>> iter = ref.getSubTypesOf(IDAO.class);

        for (Class<?> cls : iter) {

            Type[] genericInterfaces = cls.getGenericInterfaces();

            if (genericInterfaces.length == 1 &&
                genericInterfaces[0] instanceof ParameterizedType) {

                ParameterizedType inter = ((ParameterizedType) genericInterfaces[0]);
                Type[] actualTypeArgs = inter.getActualTypeArguments();

                if (actualTypeArgs.length > 0) {

                    // XXX ParameterizedType.getTypeName() is java 1.8 only :(
                    System.out.print(inter);
                    System.out.println(": " + cls.getName());

                    registry.put(actualTypeArgs[0], cls);
                }
            }
        }
    }

    @Override
    public void connect() {

        if (!Base.hasConnection()) {

            // TODO Refatorar para classe?
            Base.open(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://127.0.0.1:4040/biblioteca" +
                    "?serverTimezone=America/Fortaleza" +
                    "&nullNamePatternMatchesAll=true" +
                    "&useUnicode=true" +
                    "&characterEncoding=UTF-8" +
                    "&autoReconnect=true",
                "biblioteca",
                "123456");
        }
    }

    @Override
    public void connectPooled() {

        // TODO Mover conexão para outro lugar, qual?
        // TODO Realizar conexão de maneira Lazy
        if (!Base.hasConnection()) {

            Base.open("java:comp/env/jdbc/default");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> IDAO<T> novoDAO(Class<T> cls) {

        this.connectPooled();

        Class<?> key = registry.get(cls);
        IDAO<T> r = null;

        if (key != null) {

            try {

                r = (IDAO<T>) key.newInstance();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return r;
    }

    @Override
    public void close() {

        Base.close();
    }
}
