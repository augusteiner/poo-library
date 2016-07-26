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

import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import poo.library.app.web.dto.ReservaDTO;
import poo.library.app.web.util.ISubResource;
import poo.library.comum.EStatusRequisicao;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Reserva;
import poo.library.modelo.Usuario;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Path(ReservaUsuarioResource.PATH)
public class ReservaUsuarioResource implements ISubResource<ReservaDTO> {

    public static final String PATH = "usuario/{usuarioId}/reserva";

    public static final Class<Reserva> MODEL_CLASS = Reserva.class;
    public static final Class<ReservaDTO> DTO_CLASS = ReservaDTO.class;

    private final IDAO<Usuario> parentDAO;

    public ReservaUsuarioResource() {

        this(DAOFactory.createNew(Usuario.class));
    }

    public ReservaUsuarioResource(IDAO<Usuario> parentDAO) {

        this.parentDAO = parentDAO;
    }

    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public Response delete(
        @PathParam("usuarioId") int usuarioId,
        @PathParam("id") int reservaId) {

        return unauthorized().build();
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public Response get(@PathParam("usuarioId") int usuarioId) {

        Iterable<Reserva> iter;

        try {

            iter = this.reservasPorUsuario(usuarioId);

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            return notFound().entity(e).build();
        }

        return ok().entity(iter).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public Response get(
        @PathParam("usuarioId") int usuarioId,
        @PathParam("id") int id) {

        Reserva reserva;

        try {

            reserva = this.usuarioPorId(usuarioId).reservaPorId(id);

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            return notFound().entity(e).build();
        }

        return ok().entity(this.converter(reserva)).build();
    }

    private Object converter(Reserva reserva) {

        return null;
    }

    protected IDAO<Usuario> getParentDAO() {

        return parentDAO;
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public Response post(
        @PathParam("usuarioId") int usuarioId,

        ReservaDTO dto) {

        // XXX Não será permitida atualização
        if (dto.getId() > 0) {

            return unauthorized().build();
        }

        try {

            this.postReserva(usuarioId, dto);

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            return notFound().entity(e).build();

        } catch (FalhaOperacaoException e) {

            e.printStackTrace();

            return serverError().entity(e).build();
        }

        URI createdUri = URI.create(String.format(
            "usuario/%d/reserva/%d",

            dto.getUsuarioId(),
            dto.getId()));

        return created(createdUri).build();
    }

    private void postReserva(int usuarioId, ReservaDTO dto)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        Reserva reserva = this.usuarioPorId(usuarioId).reservaPorId(dto.getId());

        this.converter(dto, reserva);

        this.getParentDAO().flush();

        this.converter(reserva, dto);
    }

    private void converter(ReservaDTO dto, Reserva reserva) {

        //
    }

    private void converter(Reserva reserva, ReservaDTO dto) {

        //
    }

    @PUT
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public Response put(
        @PathParam("usuarioId") int usuarioId,
        @PathParam("id") int reservaId,

        ReservaDTO obj) {

        Usuario usuario;

        try {

            usuario = this.getParentDAO().reference(usuarioId);

            if (obj.getStatus() == EStatusRequisicao.CANCELADA) {

                usuario.cancelar(reservaId);

            }

            //usuario.getReservas();

            this.getParentDAO().flush();

            return noContent().build();

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            return notFound().entity(e).build();

        } catch (FalhaOperacaoException e) {

            e.printStackTrace();

            return serverError().entity(e).build();
        }
    }

    private Iterable<Reserva> reservasPorUsuario(int usuarioId) throws ObjetoNaoEncontradoException {

        return this.usuarioPorId(usuarioId).getReservas();
    }

    private Usuario usuarioPorId(int usuarioId) throws ObjetoNaoEncontradoException {

        return this.getParentDAO().find(usuarioId);
    }
}
