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

import java.sql.SQLRecoverableException;

import javax.persistence.PersistenceException;

import poo.library.dao.comum.IDAO;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class DAOChecaConexao<T> implements IDAO<T>, AutoCloseable {

    private GenericDAO<T> dao;

    public DAOChecaConexao(GenericDAO<T> dao) {

        this.dao = dao;
    }

    @Override
    public Iterable<? extends T> all() {

        try {

            return this.getGenericDAO().all();

        } catch (PersistenceException e) {

            checkRecovery(e);

            throw e;
        }
    }

    @Override
    public void close() {

        if (this.dao != null) {

            this.dao.close();
            this.dao = null;
        }
    }

    @Override
    public int count() {

        try {

            return this.getGenericDAO().count();

        } catch (PersistenceException e) {

            checkRecovery(e);

            throw e;
        }
    }

    @Override
    public void delete(T obj) throws FalhaOperacaoException {

        try {

            this.getGenericDAO().delete(obj);

        } catch (PersistenceException e) {

            checkRecovery(e);

            throw new FalhaOperacaoException(
                String.format("Não foi possivel remover %s", obj),
                e);
        }
    }

    @Override
    public void deleteById(int id)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        try {

            this.getGenericDAO().deleteById(id);

        } catch (PersistenceException e) {

            checkRecovery(e);

            throw e;
        }
    }

    @Override
    public T find(Object id)
        throws ObjetoNaoEncontradoException {

        try {

            return this.getGenericDAO().find(id);

        } catch (PersistenceException e) {

            checkRecovery(e);

            throw e;
        }
    }

    @Override
    public T first() throws ObjetoNaoEncontradoException {

        try {

            return this.getGenericDAO().first();

        } catch (PersistenceException e) {

            checkRecovery(e);

            throw e;
        }
    }

    @Override
    public void flush() throws FalhaOperacaoException {

        try {

            this.getGenericDAO().flush();

        } catch (PersistenceException e) {

            checkRecovery(e);

            throw new FalhaOperacaoException(
                e.getMessage(),
                e);
        }
    }

    @Override
    public T reference(Object id) {

        try {

            return this.getGenericDAO().reference(id);

        } catch (PersistenceException e) {

            checkRecovery(e);

            throw e;
        }
    }

    @Override
    public void save(T obj) throws FalhaOperacaoException {

        try {

            this.getGenericDAO().save(obj);

        } catch (PersistenceException e) {

            checkRecovery(e);

            throw new FalhaOperacaoException(
                String.format(
                    "Não foi possivel salvar o objeto %s, (Causa: %s)",
                    obj,
                    e.getMessage()),
                e);
        }
    }

    @Override
    public Iterable<? extends T> search(Object term) {

        try {

            return this.getGenericDAO().search(term);

        } catch (PersistenceException e) {

            checkRecovery(e);

            throw e;
        }
    }

    private void checkRecovery(PersistenceException e) {

        // XXX Se não for possível se recuperar do erro
        if (!(e.getCause() instanceof SQLRecoverableException)) {

            this.close();
        }
    }

    private IDAO<T> getGenericDAO() {

        return this.dao;
    }

    @Override
    public Class<T> getEntityClass() {

        return this.dao.getEntityClass();
    }
}
