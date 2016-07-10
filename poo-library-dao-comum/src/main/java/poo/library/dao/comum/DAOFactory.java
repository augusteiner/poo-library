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
package poo.library.dao.comum;

import java.lang.reflect.Type;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class DAOFactory {

    private static final DAOFactory INSTANCE = new DAOFactory();

    public static void close() {

        getSingleton().cache = null;
        getSingleton().impl.close();
    }

    public static void connect() {

        getSingleton().initCache();

        getSingleton().impl.connect();
    }

    public static void connectPooled() {

        getSingleton().initCache();

        getSingleton().impl.connectPooled();
    }

    public static <T> IDAO<T> createNew(Class<T> cls) {

        IDAO<T> dao = INSTANCE.getFromCache(cls);

        if (dao == null) {

            dao = INSTANCE.impl.createNew(cls);

            INSTANCE.cache.put(cls, dao);
        }

        return dao;
    }

    private static DAOFactory getSingleton() {

        return INSTANCE;
    }

    public static void register(IDAOFactory factory) {

        getSingleton().impl = factory;
    }

    private IDAOFactory impl = new NullDAOFactory();

    private Map<Type, IDAO<?>> cache = null;

    private DAOFactory() { }

    @SuppressWarnings("unchecked")
    private <T> IDAO<T> getFromCache(Class<T> cls) {

        IDAO<?> dao = this.cache.get(cls);

        return (IDAO<T>) dao;
    }

    private void initCache() {

        if (this.cache == null) {

            this.cache = new Hashtable<Type, IDAO<?>>();
        }
    }
}
