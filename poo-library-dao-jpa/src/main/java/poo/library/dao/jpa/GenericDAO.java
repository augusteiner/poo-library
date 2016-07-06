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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import poo.library.dao.comum.IDAO;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class GenericDAO<T> implements IDAO<T> {

    private Class<T> cls;
    private EntityManager em;

    public GenericDAO(Class<T> cls, EntityManager em) {

        this.cls = cls;
        this.em = em;
    }

    @Override
    public Iterable<T> all() {

        CriteriaBuilder builder = this.em.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(this.cls);

        query.from(this.cls);

        return this.em.createQuery(query).getResultList();
    }

    @Override
    public int count() {

        return 0;
    }

    @Override
    public void delete(T obj) {

        this.em.remove(obj);
    }

    @Override
    public void deleteById(int id) throws ObjetoNaoEncontradoException {

        T obj = this.find(id);

        this.delete(obj);
    }

    @Override
    public T find(int id) throws ObjetoNaoEncontradoException {

        T obj = this.em.find(this.cls, id);

        if (obj == null) {

            throw new ObjetoNaoEncontradoException(String.format(
                "Objeto de id #%d não encontrado",
                id));

        } else {

            return obj;
        }
    }

    @Override
    public T first() throws ObjetoNaoEncontradoException {

        CriteriaBuilder builder = this.em.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(this.cls);

        query.from(this.cls);

        return this.em.createQuery(query)
            .setMaxResults(1)
            .getSingleResult();
    }

    protected EntityManager getEm() {

        return this.em;
    }

    @Override
    public void save(T obj) throws FalhaOperacaoException {

        this.em.getTransaction().begin();

        try {

            if (!this.em.contains(obj)) {

                this.em.merge(obj);

            } else {

                this.em.persist(obj);
            }

            this.em.flush();

        } catch (Exception e) {

            this.em.getTransaction().rollback();

            throw new FalhaOperacaoException(
                String.format(
                    "Não foi possivel salvar o objeto %s",
                    obj),
                e);
        }

        this.em.getTransaction().commit();
    }
}