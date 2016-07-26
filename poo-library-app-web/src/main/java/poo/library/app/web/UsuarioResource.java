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

import static poo.library.app.web.util.Conversores.*;
import static poo.library.app.web.util.Responses.*;
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
import poo.library.app.web.util.IDAOHolder;
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

    public UsuarioResource() {

        super(PATH);
    }
    @GET
    @Path("/{id}/reserva")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getReservas(@PathParam("id") int usuarioId) {

        Usuario usuario;
        Iterable<ReservaDTO> iter;

        try {

            usuario = converter(this.getDAO().find(usuarioId));

            iter = convert(
                usuario.getReservas(),
                newConversor(ReservaDTO.class));

        } catch (ObjetoNaoEncontradoException e) {

            return notFound().entity(e).build();
        }

        return ok().entity(iter).build();
    }

    @GET
    @Path("/{id}/locacao")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getLocacoes(@PathParam("id") int usuarioId) {

        Usuario usuario;
        Iterable<LocacaoDTO> iter;

        try {

            usuario = converter(this.getDAO().find(usuarioId));

            iter = convert(
                usuario.getLocacoes(),
                newConversor(LocacaoDTO.class));

        } catch (ObjetoNaoEncontradoException e) {

            return notFound().entity(e).build();
        }

        return Response.ok().entity(iter).build();
    }


    @Override
    public Usuario converter(Object input) {

        Usuario usuario = new Usuario();

        converter(input, usuario);

        return usuario;
    }

    @Override
    public void converter(Object input, Usuario usuario) {

        newConversor(Usuario.class).converter(input, usuario);
    }
}
