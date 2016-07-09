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
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import poo.library.dao.comum.IDAO;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Livro;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
public class ItemAcervoDAO extends GenericDAO<ItemAcervo> implements IDAO<ItemAcervo> {

    public ItemAcervoDAO(EntityManager em) {

        super(ItemAcervo.class, em);
    }

    @Override
    public Iterable<ItemAcervo> search(String term) {

        CriteriaBuilder builder = this.getEm().getCriteriaBuilder();

        CriteriaQuery<ItemAcervo> query = builder.createQuery(this.cls);

        Root<ItemAcervo> root = query.from(this.cls);
        Root<Livro> livroRoot = builder.treat(root, Livro.class);

        Expression<String> autorPath = root.get("autor");
        Expression<String> tituloLivroPath = livroRoot.get("titulo");
        Expression<String> isbnLivroPath = livroRoot.get("isbn");

        Predicate p = builder.or(
            builder.gt(builder.locate(autorPath, term), 0),
            builder.gt(builder.locate(tituloLivroPath, term), 0),
            builder.gt(builder.locate(isbnLivroPath, term), 0)
        );

        query.where(p);

        return this.getEm().createQuery(query)
            .getResultList();
    }
}
