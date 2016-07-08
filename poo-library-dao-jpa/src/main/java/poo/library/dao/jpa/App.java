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

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import poo.library.dao.comum.IDAO;
import poo.library.dao.comum.IDAOFactory;
import poo.library.modelo.Administrador;
import poo.library.modelo.Apostila;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.Livro;
import poo.library.modelo.Reserva;
import poo.library.modelo.Usuario;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
public class App {

    public static void main(String[] args) {

        //testDAO();

        testEMF();
    }

    public static void testDAO() {

        IDAOFactory factory = new DefaultConnectionDAOFactory();
        factory.connect();

        IDAO<Usuario> usuarios = factory.createNew(Usuario.class);

        try {

            System.out.println(usuarios.first());

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

        }

        //for (Usuario u : usuarios.all()) {
        //
        //    System.out.println(u);
        //}

        factory.close();
    }

    private static void testEMF() {

        EntityManagerFactory ef;

        ef = Persistence.createEntityManagerFactory("poo.library.dao.jpa.default");

        try {

            seed(ef);

        } catch (Exception e) {

            e.printStackTrace();

            ef.close();
        }
    }

    private static void seed(EntityManagerFactory ef) {

        Biblioteca b1;
        Livro i1;
        Apostila i2;
        Usuario u1;
        Usuario u2;
        Reserva r1;

        EntityManager em = ef.createEntityManager();

        em.getTransaction().begin();

        u1 = new Usuario("Augusto", "12345612311");
        u1.setEndereco("R. José Gular, 811");

        u2 = new Administrador("José", "32135465498");
        u2.setEndereco("R. dos Admins, 879");

        em.persist(u1);
        em.persist(u2);

        em.persist(b1 = new Biblioteca("Bib. São Lucas", 1.5));

        i1 = new Livro("José A.", "How To work with Hibernate", 2.5, b1.getId());

        i1.setIsbn("01231655984");
        i1.setEdicao(1);

        i2 = new Apostila("José A.", "Introd. ao word", 2.5, b1.getId());

        em.persist(i1);
        em.persist(i2);

        r1 = new Reserva(
            new Date(),

            i1.getId(),
            u1.getId());

        r1.setValidaAte(Date.from(Instant.now().plus(3, ChronoUnit.DAYS)));

        em.persist(r1);

        em.getTransaction().commit();

        em.clear();

        r1 = em.find(Reserva.class, r1.getId());

        System.out.println(r1);

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
