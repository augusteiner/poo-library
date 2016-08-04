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

import static javax.ws.rs.core.Response.Status.*;
import static poo.library.app.web.util.Responses.*;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import poo.library.app.web.util.ISubResource;
import poo.library.comum.IIdentificavel;
import poo.library.dao.comum.IDAO;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.IConversor;
import poo.library.util.Iterables;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class GenericSubResource<T extends IIdentificavel>
    extends NullSubResource<T>
    implements ISubResource<T> {

    private ISubResource<T> behavior;

    public GenericSubResource() {

        this.initBehavior(this);
    }

    public static void closeDAO(IDAO<?> dao) {

        try {

            dao.close();

        } catch (Exception e) {

            e.printStackTrace();

            throw new ServerErrorException(
                INTERNAL_SERVER_ERROR, e);
        }
    }

    private void close() {

        closeDAO(this.getParentDAO());
    }

    @Override
    public URI createdAt(int parentId, int id) {

        throw new UnsupportedOperationException();
    }

    protected void flush() {

        try {

            this.getParentDAO().flush();

        } catch (FalhaOperacaoException e) {

            e.printStackTrace();

            throw new ServerErrorException(
                INTERNAL_SERVER_ERROR, e);
        }
    }

    protected abstract IConversor<T> getConversorDTO();

    protected abstract IDAO<?> getParentDAO();

    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response httpDelete(
        @PathParam("parentId") int parentId,
        @PathParam("id") int id) {

        try {

            this.behavior.delete(parentId, id);

            this.flush();

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            throw new NotFoundException(
                notFound().entity(e).build(),
                e);

        } finally {

            this.close();
        }

        return noContent().build();
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Iterable<T> httpGet(
        @PathParam("parentId") int parentId) {

        try {

            Collection<?> list = this.behavior.get(parentId);

            System.out.println(String.format(
                "(lista: %s; size: %d)",

                list.getClass(),
                list.size()));

            return Iterables.convert(
                list,
                this.getConversorDTO());

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            throw new NotFoundException(
                notFound().entity(e).build(),
                e);

        } finally {

            this.close();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public T httpGet(
        @PathParam("parentId") int parentId,
        @PathParam("id") int id) {

        try {

            Object entity = this.behavior.get(parentId, id);

            return this.getConversorDTO().converter(entity);

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            throw new NotFoundException(
                notFound().entity(e).build(),
                e);

        } finally {

            this.close();
        }
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    public Response httpPost(
        @PathParam("parentId") int parentId,

        T obj) {

        try {

            this.behavior.post(parentId, obj);

            //this.flush();

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            throw new NotFoundException(
                notFound().entity(e).build(),
                e);

        } finally {

            this.close();
        }

        URI createdAt = this.createdAt(parentId, obj.getId());

        return created(createdAt).build();
    }

    @PUT
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response httpPut(
        @PathParam("parentId") int parentId,
        @PathParam("id") int id,

        T obj) {

        try {

            this.behavior.put(parentId, id, obj);

            this.flush();

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            throw new NotFoundException(
                notFound().entity(e).build(),
                e);

        } finally {

            this.close();
        }

        return noContent().build();
    }

    public void initBehavior(ISubResource<T> behavior) {

        this.behavior = behavior;
    }
}
