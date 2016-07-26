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
import poo.library.util.FalhaOperacaoException;
import poo.library.util.IConversor;
import poo.library.util.Iterables;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class DAOConversor<I, O> implements IConversor<O>, IDAO<O> {

    private final poo.library.dao.comum.IDAO<I> dao;

    private final IConversor<O> conversorIn;
    private final IConversor<I> conversorOut;

    public DAOConversor(
        poo.library.dao.comum.IDAO<I> dao,
        IConversor<O> conversorIn,
        IConversor<I> conversorOut) {

        this.dao = dao;

        this.conversorIn = conversorIn;
        this.conversorOut = conversorOut;
    }

    @Override
    public Iterable<O> all() {

        return Iterables.convert(this.dao.all(), this);
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
    public O find(int id) throws ObjetoNaoEncontradoException {

        return this.converter(this.dao.find(id));
    }

    @Override
    public O first() throws ObjetoNaoEncontradoException {

        return this.converter(this.dao.first());
    }

    @Override
    public void save(O entity) throws FalhaOperacaoException {

        I obj = null;
        IIdentificavel outputId = null;

        if (entity instanceof IIdentificavel)
            outputId = (IIdentificavel) entity;

        if (outputId != null &&
            outputId.getId() > 0) {

            obj = this.dao.reference(outputId.getId());

            this.conversorOut.converter(entity, obj);

        } else {

            obj = this.conversorOut.converter(entity);
        }

        this.dao.save(obj);

        this.conversorIn.converter(obj, entity);
    }

    @Override
    public Iterable<O> search(String term) {

        return Iterables.convert(
            this.dao.search(term),
            this);
    }

    @Override
    public O converter(Object input) {

        return this.conversorIn.converter(input);
    }

    @Override
    public void flush() throws FalhaOperacaoException {

        this.dao.flush();
    }

    @Override
    public O reference(int id) {

        return this.converter(this.dao.reference(id));
    }

    @Override
    public void converter(Object input, O output) {

        this.conversorIn.converter(input, output);
    }

    @Override
    public void close() throws Exception {

        this.dao.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public poo.library.dao.comum.IDAO<I> unwrap() {

        return this.dao;
    }
}
