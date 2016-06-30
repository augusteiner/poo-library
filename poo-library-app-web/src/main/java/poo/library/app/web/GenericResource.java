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

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import poo.library.comum.IIdentificavel;
import poo.library.dao.comum.IDAO;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class GenericResource<I extends IIdentificavel, T extends I> {

    private final String path;
    private final IDAO<I> dao;

    protected GenericResource(
        String path,
        IDAO<I> dao) {

        if (path == null ||
            path.length() == 0) {

            throw new IllegalArgumentException("Argumento path deve ser válido");
        }

        if (dao == null) {

            throw new IllegalArgumentException("Argumento dao deve ser válido");
        }

        this.path = path;
        this.dao = dao;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {

        this.dao.delete("id = ?", id);
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Iterable<I> get() {

        return this.dao.all();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response get(@PathParam("id") int id) {

        I obj;

        try {

            obj = this.dao.first(
                "id = ?",
                id);

        } catch (ObjetoNaoEncontradoException e) {

            throw new NotFoundException(
                e.getMessage(),
                e);
        }

        return Response.ok(obj).build();
    }

    @POST
    public Response post(T obj) {

        this.dao.save(obj);

        URI createdUri = URI.create(String.format(
            "%s/%d",
            this.path,
            obj.getId()));

        return Response.created(createdUri).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response put(
        @PathParam("id") int id,
        T obj) {

        this.dao.save(obj);

        return Response.ok().build();
    }
}
