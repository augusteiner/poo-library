/*
 * MIT License
 *
 * Copyright (c) 2016 José Nascimento & Juscelino Messias
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

import static poo.library.app.web.util.Inicializador.*;
import static poo.library.app.web.util.Conversores.*;
import static poo.library.app.web.util.DAOFactory.*;
import static poo.library.util.Iterables.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import poo.library.app.web.dto.LocacaoDTO;
import poo.library.app.web.dto.ReservaDTO;
import poo.library.app.web.dto.UsuarioDTO;
import poo.library.app.web.util.Inicializador;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.dao.util.IDAOHolder;
import poo.library.modelo.Usuario;
import poo.library.util.IConversor;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Path(UsuarioResource.PATH)
public class UsuarioResource extends GenericResource<UsuarioDTO>
    implements IDAOHolder<UsuarioDTO>, IConversor<Usuario> {

    public static final String PATH = "usuario";

    private static final Inicializador<UsuarioResource> INIT = init(UsuarioResource.class);

    public static final Class<Usuario> MODEL_CLASS = Usuario.class;
    public static final Class<UsuarioDTO> DTO_CLASS = UsuarioDTO.class;
    private final IDAO<Usuario> dao;

    public UsuarioResource() {

        this(DAOFactory.createNew(MODEL_CLASS));

        INIT.configure(this);
    }

    public UsuarioResource(IDAO<Usuario> dao) {

        super(PATH, newDAO(dao, MODEL_CLASS, DTO_CLASS));

        this.dao = dao;
    }

    @GET
    @Path("/{id}/reserva")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getReservas(@PathParam("id") int usuarioId) {

        Iterable<ReservaDTO> iter;

        try {

            iter = convert(
                this.dao.find(usuarioId).getReservas(),
                newConversor(ReservaDTO.class));

        } catch (ObjetoNaoEncontradoException e) {

            return Response.status(Response.Status.NOT_FOUND)
                .entity(e)
                .build();
        }

        return Response.ok().entity(iter).build();
    }

    @GET
    @Path("/{id}/locacao")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getLocacoes(@PathParam("id") int usuarioId) {

        Iterable<LocacaoDTO> iter;

        try {

            iter = convert(
                this.dao.find(usuarioId).getLocacoes(),
                newConversor(LocacaoDTO.class));

        } catch (ObjetoNaoEncontradoException e) {

            return Response.status(Response.Status.NOT_FOUND)
                .entity(e)
                .build();
        }

        return Response.ok().entity(iter).build();
    }

    @Override
    public Usuario converter(Object input) {

        return null;
    }

    @Override
    public void converter(Object input, Usuario output) {

        //
    }
}
