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
package poo.library.app.web;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.NotFoundException;

import poo.library.app.web.util.ISubResource;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class NullSubResource<T> implements ISubResource<T> {

    @Override
    public void delete(int parentId, int id)
        throws ObjetoNaoEncontradoException {

        throw new NotFoundException();
    }

    @Override
    public Collection<?> get(int parentId) throws ObjetoNaoEncontradoException {

        throw new NotFoundException();
    }

    @Override
    public Object get(int parentId, int id)
        throws ObjetoNaoEncontradoException {

        throw new NotFoundException();
    }

    @Override
    public void post(int parentId, T obj) throws ObjetoNaoEncontradoException {

        throw new NotFoundException();
    }

    @Override
    public void put(int parentId, int id, T obj)
        throws ObjetoNaoEncontradoException {

        throw new NotFoundException();
    }

    public URI createdAt(int parentId, int id) {

        throw new UnsupportedOperationException();
    }
}
