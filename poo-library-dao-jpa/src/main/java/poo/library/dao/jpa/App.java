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

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import poo.library.modelo.Apostila;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.Livro;
import poo.library.modelo.Usuario;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
public class App {

    public static void main(String[] args) {

        Biblioteca bi;

        EntityManagerFactory ef = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

        EntityManager em = ef.createEntityManager();

        em.getTransaction().begin();

        Usuario usuario = new Usuario("José Augusto", "12345612311");
        usuario.setEndereco("R. José Gular, 811");

        em.persist(usuario);

        em.persist(bi = new Biblioteca("Bib. São Lucas", 1.5));

        em.persist(new Livro("José A.", "How To work with Hibernate", 2.5, bi.getId()));
        em.persist(new Apostila("José A.", "Introd. ao word", 2.5, bi.getId()));

        em.getTransaction().commit();

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Livro> q = builder.createQuery(Livro.class);
        Root<Livro> r = q.from(Livro.class);

        q.where(r.isNotNull());

        Collection<Livro> livros = em.createQuery(q).getResultList();

        System.out.println("LIVROS>>>");

        for (Livro l : livros) {

            System.out.println(l);
        }

        em.close();

        System.exit(0);
    }
}
