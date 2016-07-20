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

    private final String persistenceUnitName;
    private EntityManagerFactory factory;

    public JpaDAOFactory(String persistenceUnitName) {

        this.persistenceUnitName = persistenceUnitName;

        this.factory = null;
    }

    @Override
    public void connect() {

        if (this.factory == null ||
            !this.factory.isOpen()) {

            this.factory = Persistence.createEntityManagerFactory(
                this.persistenceUnitName);
        }
    }

    @Override
    public void connectPooled() {

        this.connect();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> IDAO<T> createNew(Class<T> cls) {

        GenericDAO<?> dao;
        EntityManager em;

        this.connectPooled();
        em = this.factory.createEntityManager();

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

    @Override
    public void close() {

        if (this.factory != null) {

            this.factory.close();
            this.factory = null;
        }
    }
}
