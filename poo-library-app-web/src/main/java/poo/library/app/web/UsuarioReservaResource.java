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

import static poo.library.app.web.ReservaResource.*;
import static javax.ws.rs.core.Response.Status.*;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Path;
import javax.ws.rs.ServerErrorException;

import poo.library.app.web.dto.ReservaDTO;
import poo.library.app.web.util.ISubResource;
import poo.library.comum.EStatusRequisicao;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.Reserva;
import poo.library.modelo.Usuario;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.IConversor;
import poo.library.util.ItemIndisponivelException;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Path(UsuarioReservaResource.PATH)
public class UsuarioReservaResource extends GenericSubResource<ReservaDTO>
    implements ISubResource<ReservaDTO> {

    public static final String PATH = "usuario/{parentId}/reserva";

    private final IDAO<Usuario> parentDAO;

    public UsuarioReservaResource() {

        this(DAOFactory.novoDAO(Usuario.class));
    }

    public UsuarioReservaResource(IDAO<Usuario> parentDAO) {

        this.parentDAO = parentDAO;

        this.initBehavior(this);
    }

    @Override
    public URI createdAt(int usuarioId, int id) {

        return URI.create(String.format(
            "usuario/%d/reserva/%d",

            usuarioId,
            id));
    }

    @Override
    public Collection<Reserva> get(int usuarioId)
        throws ObjetoNaoEncontradoException {

        Usuario usuario = this.usuarioPorId(usuarioId);

        return usuario.getReservas();
    }

    @Override
    public Reserva get(int usuarioId, int id) throws ObjetoNaoEncontradoException {

        Usuario usuario = this.usuarioPorId(usuarioId);

        return usuario.reservaPorId(id);
    }

    @Override
    public void post(
        int usuarioId,

        ReservaDTO dto) throws ObjetoNaoEncontradoException {

        // XXX Não será permitida atualização
        if (dto.getId() > 0) {

            throw new BadRequestException(
                "Não é permitida atualização de reservas");
        }

        Biblioteca biblioteca = this.bibliotecaPorId(dto.getBibliotecaId());

        Reserva reserva = null;

        try {

            reserva = biblioteca.reservar(
                dto.getItemAcervoId(),
                dto.getUsuarioId());

        } catch (ItemIndisponivelException e) {

            e.printStackTrace();

            throw new BadRequestException(
                e.getMessage(),
                e);
        }

        try {

            this.getParentDAO().flush();

        } catch (FalhaOperacaoException e) {

            e.printStackTrace();

            throw new ServerErrorException(
                INTERNAL_SERVER_ERROR,
                e);
        }

        this.getConversorDTO().converter(reserva, dto);
    }

    @Override
    public void put(
        int usuarioId,
        int reservaId,

        ReservaDTO obj) throws ObjetoNaoEncontradoException {

        Usuario usuario = this.getParentDAO().reference(usuarioId);

        if (obj.getStatus() == EStatusRequisicao.CANCELADA) {

            usuario.cancelar(reservaId);
        }
    }

    private Biblioteca bibliotecaPorId(int bibliotecaId)
        throws ObjetoNaoEncontradoException {

        return DAOFactory.novoDAO(Biblioteca.class).find(bibliotecaId);
    }

    private Usuario usuarioPorId(int usuarioId)
        throws ObjetoNaoEncontradoException {

        return this.getParentDAO().find(usuarioId);
    }

    @Override
    protected IConversor<ReservaDTO> getConversorDTO() {

        return CONVERSOR_DTO;
    }

    @Override
    protected IDAO<Usuario> getParentDAO() {

        return parentDAO;
    }
}
