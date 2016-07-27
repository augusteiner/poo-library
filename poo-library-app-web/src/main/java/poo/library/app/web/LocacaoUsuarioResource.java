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

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import poo.library.app.web.dto.LocacaoDTO;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Locacao;
import poo.library.modelo.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Path(LocacaoUsuarioResource.PATH)
public class LocacaoUsuarioResource {

    public static final String PATH = "usuario/{usuarioId}/locacao";

    public static final Class<Locacao> MODEL_CLASS = Locacao.class;
    public static final Class<LocacaoDTO> DTO_CLASS = LocacaoDTO.class;

    private IDAO<Usuario> parentDAO;

    public LocacaoUsuarioResource(IDAO<Usuario> parentDAO) {

        this.parentDAO = parentDAO;
    }

    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response delete(
        @PathParam("usuarioId") int usuarioId,
        @PathParam("id") int locacaoId) {

        return unauthorized().build();
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response get(@PathParam("usuarioId") int usuarioId) {

        return null;
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response get(
        @PathParam("usuarioId") int usuarioId,
        @PathParam("id") int locacaoId) {

        return null;
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    public Response post(
        @PathParam("usuarioId") int usuarioId,

        LocacaoDTO locacao) {

        return unauthorized().build();
    }

    @PUT
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response put(
        @PathParam("usuarioId") int usuarioId,
        @PathParam("id") int id,

        LocacaoDTO locacao) {

        return unauthorized().build();
    }

    protected IDAO<Usuario> getParentDAO() {

        return parentDAO;
    }
}
