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
import poo.library.modelo.Usuario;
import poo.library.util.FalhaOperacaoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class UsuarioDAO extends GenericDAO<Usuario> implements IDAO<Usuario> {

    public UsuarioDAO(EntityManager em) {

        super(Usuario.class, em);
    }

    @Override
    public void save(Usuario obj)
        throws FalhaOperacaoException {

        // TODO Detectar alteração no tipo de usuário???
        super.save(obj);
    }

    @Override
    public Iterable<Usuario> search(String term) {

        CriteriaBuilder builder = this.getEm().getCriteriaBuilder();

        CriteriaQuery<Usuario> query = builder.createQuery(this.cls);

        Root<Usuario> root = query.from(this.cls);

        Expression<String> nomePath = root.get("nome");
        Expression<String> loginPath = root.get("login");
        Expression<String> cpfPath = root.get("cpf");
        Expression<String> enderecoPath = root.get("endereco");

        Predicate p = builder.or(
            builder.gt(builder.locate(nomePath, term), 0),
            builder.gt(builder.locate(loginPath, term), 0),
            builder.gt(builder.locate(cpfPath, term), 0),
            builder.gt(builder.locate(enderecoPath, term), 0));

        query.where(p);

        return this.getEm().createQuery(query)
            .getResultList();
    }
}
