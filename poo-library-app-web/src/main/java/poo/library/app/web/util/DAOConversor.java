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
package poo.library.app.web.util;

import poo.library.comum.IIdentificavel;
import poo.library.dao.comum.IDAO;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.IConversor;
import poo.library.util.Iterables;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class DAOConversor<I, O> implements IConversor<O>, IDAO<O> {

    private final IDAO<I> dao;

    private final IConversor<O> conversorIn;
    private final IConversor<I> conversorOut;

    private Class<O> cls;

    public DAOConversor(
        IDAO<I> dao,
        IConversor<O> conversorIn,
        Class<O> clsIn,
        IConversor<I> conversorOut) {

        this.dao = dao;

        this.cls = clsIn;
        this.conversorIn = conversorIn;
        this.conversorOut = conversorOut;
    }

    @Override
    public Iterable<O> all() {

        return Iterables.convert(this.dao.all(), this);
    }

    @Override
    public void close() throws Exception {

        this.dao.close();
    }

    @Override
    public O converter(Object input) {

        return this.conversorIn.converter(input);
    }

    @Override
    public void converter(Object input, O output) {

        this.conversorIn.converter(input, output);
    }

    @Override
    public int count() {

        return this.dao.count();
    }

    @Override
    public void delete(O obj) throws FalhaOperacaoException {

        try {

            if (obj instanceof IIdentificavel)
                this.deleteById(((IIdentificavel) obj).getId());

        } catch (ObjetoNaoEncontradoException e) {

            throw new FalhaOperacaoException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(int id)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        this.dao.deleteById(id);
    }

    @Override
    public O find(Object id)
        throws ObjetoNaoEncontradoException {

        return this.converter(this.dao.find(id));
    }

    @Override
    public O first() throws ObjetoNaoEncontradoException {

        return this.converter(this.dao.first());
    }

    @Override
    public void flush() throws FalhaOperacaoException {

        this.dao.flush();
    }

    @Override
    public Class<O> getEntityClass() {

        return this.cls;
    }

    @Override
    public O reference(Object id) {

        return this.converter(this.dao.reference(id));
    }

    @Override
    public void save(O entity) throws FalhaOperacaoException {

        int id = ((IIdentificavel) entity).getId();
        I obj;

        if (id > 0) {

            obj = this.dao.reference(id);

            this.conversorOut.converter(entity, obj);

        } else {

            obj = this.conversorOut.converter(entity);

        }

        this.dao.save(obj);

        this.conversorIn.converter(obj, entity);
    }

    @Override
    public Iterable<O> search(Object term) {

        return Iterables.convert(
            this.dao.search(term),
            this);
    }
}
