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
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class JpaDAOFactory implements IDAOFactory {

    private EntityManagerFactory factory;
    private EntityManager em;

    public JpaDAOFactory(String persistenceUnitName) {

        this.factory = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    @Override
    public void connect() {

        this.em = this.factory.createEntityManager();
    }

    @Override
    public void connectPooled() {

        this.connect();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> IDAO<T> createNew(Class<T> cls) {

        if (cls.equals(Usuario.class)) {

            return (IDAO<T>) new UsuarioDAO(this.em);

        } else if (cls.equals(ItemAcervo.class)) {

            return (IDAO<T>) new ItemAcervoDAO(this.em);

        } else {

            return new GenericDAO<T>(cls, this.em);
        }
    }

    @Override
    public void close() {

        this.factory.close();
    }
}
