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
package poo.library.dao.jpa;

import java.util.Collections;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import poo.library.dao.comum.IDAO;
import poo.library.dao.comum.IDAOFactory;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class JpaDAOFactory implements IDAOFactory {

    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();

    private final String persistenceUnitName;
    private final Map<String, String> properties;

    private EntityManagerFactory factory;

    public JpaDAOFactory(String persistenceUnitName) {

        this(persistenceUnitName, Collections.<String, String>emptyMap());
    }

    public JpaDAOFactory(String persistenceUnitName, Map<String, String> properties) {

        this.persistenceUnitName = persistenceUnitName;

        this.properties = properties;

        this.factory = null;
    }

    @Override
    public synchronized void close() {

        if (this.factory != null) {

            this.factory.close();
            this.factory = null;
        }
    }

    @Override
    public synchronized void connect() {

        if (this.factory == null ||
            !this.factory.isOpen()) {

            this.factory = Persistence.createEntityManagerFactory(
                this.persistenceUnitName,
                this.properties);
        }
    }

    @Override
    public void connectPooled() {

        this.connect();
    }

    // XXX Thanks to http://stackoverflow.com/questions/14888040/java-an-entitymanager-object-in-a-multithread-environment#answer-18698497
    private static synchronized EntityManager getEntityManager(EntityManagerFactory factory) {

        EntityManager em = threadLocal.get();

        if (em == null ||
            !em.isOpen()) {

            em = factory.createEntityManager();

            threadLocal.set(em);
        }

        return em;
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized <T> IDAO<T> novoDAO(Class<T> cls) {

        GenericDAO<?> dao;
        EntityManager em;

        this.connectPooled();

        em = getEntityManager(this.factory);

        if (cls.isAssignableFrom(Usuario.class)) {

            dao = new UsuarioDAO(em);

        } else if (cls.isAssignableFrom(ItemAcervo.class)) {

            dao = new ItemAcervoDAO(em);

        } else if (cls.isAssignableFrom(Biblioteca.class)) {

            dao = new BibliotecaDAO(em);

        } else {

            dao = new GenericDAO<T>(cls, em);
        }

        return new DAOChecaConexao<T>((GenericDAO<T>) dao);
    }
}
