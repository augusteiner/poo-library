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

import static poo.library.app.web.util.Responses.*;
import static poo.library.app.web.util.Conversores.*;
import static poo.library.app.web.util.DAOFactory.*;
import static poo.library.util.Iterables.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import poo.library.app.web.dto.BibliotecaDTO;
import poo.library.app.web.dto.ItemAcervoDTO;
import poo.library.app.web.util.IResource;
import poo.library.app.web.util.ISubResource;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Biblioteca;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Path(BibliotecaResource.PATH)
public class BibliotecaResource extends GenericResource<BibliotecaDTO>
    implements IResource<BibliotecaDTO> {

    public static final String PATH = "biblioteca";

    public static final Class<Biblioteca> MODEL_CLASS = Biblioteca.class;
    public static final Class<BibliotecaDTO> DTO_CLASS = BibliotecaDTO.class;

    private final ISubResource<ItemAcervoDTO> acervo;
    private final IDAO<Biblioteca> dao;

    public BibliotecaResource() {

        this(DAOFactory.createNew(MODEL_CLASS));
    }

    public BibliotecaResource(IDAO<Biblioteca> dao) {

        super(PATH, newDAO(dao, MODEL_CLASS, DTO_CLASS));

        this.dao = dao;

        this.acervo = new AcervoResource(dao);
    }

    public ISubResource<ItemAcervoDTO> getAcervo() {

        return this.acervo;
    }

    @GET
    @Path("/{id}/acervo")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAcervo(@PathParam("id") int id) {

        return this.getAcervo().get(id);
    }

    @GET
    @Path("/{id}/acervo/{itemAcervoId}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAcervo(
        @PathParam("id") int id,
        @PathParam("itemAcervoId") int itemAcervoId) {

        return this.getAcervo().get(id, itemAcervoId);
    }

    @GET
    @Path("/{id}/reserva")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getReservas(@PathParam("id") int id) {

        Biblioteca biblioteca;

        try {

            biblioteca = this.dao.find(id);

        } catch (ObjetoNaoEncontradoException e) {

            return notFound().entity(e).build();
        }

        Iterable<?> iter = convert(
            biblioteca.getReservas(),
            newConversor(ReservaResource.DTO_CLASS));

        return ok().entity(iter).build();
    }

    @POST
    @Path("/{id}/acervo")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response postAcervo(
        @PathParam("id") int id,

        ItemAcervoDTO itemAcervo) {

        return this.getAcervo().post(id, itemAcervo);
    }

    @PUT
    @Path("/{id}/acervo/{itemAcervoId}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response putAcervo(
        @PathParam("id") int id,
        @PathParam("itemAcervoId") int itemAcervoId,

        ItemAcervoDTO itemAcervo) {

        return this.getAcervo().put(
            id,
            itemAcervoId,
            itemAcervo);
    }

    @DELETE
    @Path("/{id}/acervo/{itemAcervoId}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response deleteAcervo(
        @PathParam("id") int id,
        @PathParam("itemAcervoId") int itemAcervoId) {

        return this.getAcervo().delete(
            id,
            itemAcervoId);
    }
}
