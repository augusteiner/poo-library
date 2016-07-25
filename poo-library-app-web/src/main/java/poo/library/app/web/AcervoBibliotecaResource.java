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

import static poo.library.app.web.util.Conversores.*;
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

import poo.library.app.web.dto.ItemAcervoDTO;
import poo.library.app.web.util.Conversores;
import poo.library.app.web.util.ISubResource;
import poo.library.comum.IItemAcervo;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.dao.util.DAOAcervo;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.comum.IBuscador;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.IConversor;
import poo.library.util.Iterables;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Path(AcervoBibliotecaResource.PATH)
public class AcervoBibliotecaResource implements ISubResource<ItemAcervoDTO>, IConversor<IItemAcervo> {

    public static final String PATH = "biblioteca/{bibliotecaId}/acervo";

    public static final Class<ItemAcervo> MODEL_CLASS = ItemAcervo.class;
    public static final Class<ItemAcervoDTO> DTO_CLASS = ItemAcervoDTO.class;
    public static final IConversor<ItemAcervoDTO> CONVERSOR = newConversor(DTO_CLASS);

    private IDAO<Biblioteca> parentDAO;

    public AcervoBibliotecaResource() {

        this(DAOFactory.createNew(Biblioteca.class));
    }

    public AcervoBibliotecaResource(IDAO<Biblioteca> parentDAO) {

        this.parentDAO = parentDAO;
    }

    private Biblioteca bibliotecaPorId(int bibliotecaId) throws ObjetoNaoEncontradoException {

        return this.getParentDAO().find(bibliotecaId);
    }

    private IBuscador buscador(int bibliotecaId)
        throws ObjetoNaoEncontradoException {

        return buscador(this.bibliotecaPorId(bibliotecaId));
    }

    private IBuscador buscador(Biblioteca biblioteca) {

        IBuscador buscador = new DAOAcervo(this.getParentDAO());

        Biblioteca.exportarBuscador(biblioteca, buscador);

        return buscador;
    }

    @Override
    public ItemAcervo converter(Object input) {

        return Conversores.converter(input, MODEL_CLASS);
    }

    @Override
    public void converter(Object input, IItemAcervo output) {

        Conversores.converter(input, output);
    }

    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public Response delete(
        @PathParam("bibliotecaId") int bibliotecaId,
        @PathParam("id") int id) {

        try {

            return this.deleteItem(bibliotecaId, id);

        } catch (Exception e) {

            return serverError().entity(e).build();
        }
    }

    private Response deleteItem(int bibliotecaId, int id)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        try (IBuscador buscador = buscador(bibliotecaId)) {

            Biblioteca biblioteca = buscador.getBiblioteca();
            ItemAcervo item = (ItemAcervo) buscador.itemPorId(id);

            biblioteca.removeAcervo(item);

            this.flush();

        } catch (Exception e) {

            e.printStackTrace();

            throw new FalhaOperacaoException(e.getMessage(), e);
        }

        return Response.ok().build();
    }

    private void flush() throws FalhaOperacaoException {

        try {

            this.getParentDAO().flush();

        } catch (FalhaOperacaoException e) {

            e.printStackTrace();

            throw e;

        }
        // TODO Avaliar necessiade e habilitar novamente
        //finally {
        //
        //    try {
        //
        //        this.close();
        //
        //    } catch (Exception e) {
        //
        //        e.printStackTrace();
        //
        //        throw new FalhaOperacaoException(e.getMessage(), e);
        //    }
        //}
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public Response get(
        @PathParam("bibliotecaId") int bibliotecaId) {

        try {

            Iterable<ItemAcervoDTO> iter = this.getItens(bibliotecaId);

            return ok().entity(iter).build();

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            return notFound().entity(e).build();

        } catch (FalhaOperacaoException e) {

            e.printStackTrace();

            return serverError().entity(e).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public Response get(
        @PathParam("bibliotecaId") int bibliotecaId,
        @PathParam("id") int id) {

        IItemAcervo item;

        try {

            item = this.getItem(bibliotecaId, id);

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            return notFound().entity(e).build();

        } catch (FalhaOperacaoException e) {

            e.printStackTrace();

            return serverError().entity(e).build();
        }

        return ok().entity(CONVERSOR.converter(item)).build();
    }

    private ItemAcervo getItem(int bibliotecaId, int id)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        try (IBuscador buscador = buscador(bibliotecaId)) {

            return (ItemAcervo) buscador.itemPorId(id);

        } catch (Exception e) {

            e.printStackTrace();

            throw new FalhaOperacaoException(e.getMessage(), e);
        }
    }

    public Iterable<ItemAcervoDTO> getItens(int bibliotecaId)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        Biblioteca biblioteca = this.bibliotecaPorId(bibliotecaId);

        try (IBuscador buscador = buscador(biblioteca)) {

            return Iterables.convert(
                buscador.itens(),
                CONVERSOR);

        } catch (Exception e) {

            e.printStackTrace();

            throw new FalhaOperacaoException(e.getMessage(), e);
        }
    }

    protected IDAO<Biblioteca> getParentDAO() {

        return this.parentDAO;
    }

//    private ItemAcervo itemPorId(Biblioteca biblioteca, int id)
//        throws ObjetoNaoEncontradoException, FalhaOperacaoException {
//
//        try (IBuscador buscador = buscador(biblioteca)) {
//
//            return (ItemAcervo) buscador.itemPorId(id);
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            throw new FalhaOperacaoException(e.getMessage(), e);
//        }
//    }
//
//    private ItemAcervo itemPorId(int bibliotecaId, int id)
//        throws ObjetoNaoEncontradoException, FalhaOperacaoException {
//
//        Biblioteca biblioteca = this.bibliotecaPorId(bibliotecaId);
//
//        return this.itemPorId(biblioteca, id);
//    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public Response post(
        @PathParam("bibliotecaId") int bibliotecaId,

        ItemAcervoDTO itemAcervo) {

        if (bibliotecaId != itemAcervo.getBibliotecaId()) {

            return badRequest()
                .entity(new IllegalArgumentException(
                    "Id da biblioteca deve ser igual ao do item do acervo"))
                .build();
        }

        try {

            this.postItem(bibliotecaId, itemAcervo);

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            return notFound().entity(e).build();

        } catch (FalhaOperacaoException e) {

            e.printStackTrace();

            return serverError().entity(e).build();
        }

        URI createdUri = URI.create(String.format(
            "biblioteca/%d/acervo/%d",

            itemAcervo.getBibliotecaId(),
            itemAcervo.getId()));

        return created(createdUri).build();
    }

    private void postItem(int bibliotecaId, ItemAcervoDTO itemAcervo)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        ItemAcervo item;

        try (IBuscador buscador = buscador(bibliotecaId)) {

            if (itemAcervo.getId() == 0) {

                Biblioteca biblioteca = this.bibliotecaPorId(bibliotecaId);

                item = this.converter(itemAcervo);

                biblioteca.addAcervo(item);

            } else {

                item = (ItemAcervo) buscador.itemPorId(itemAcervo.getId());

                this.converter(itemAcervo, item);
            }

            this.flush();

        } catch (Exception e) {

            e.printStackTrace();

            throw new FalhaOperacaoException(e.getMessage(), e);
        }

        CONVERSOR.converter(item, itemAcervo);
    }

    @PUT
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public Response put(
        @PathParam("bibliotecaId") int bibliotecaId,
        @PathParam("id") int id,

        ItemAcervoDTO itemAcervo) {

        try {

            this.putItem(bibliotecaId, id, itemAcervo);

        } catch (FalhaOperacaoException e) {

            e.printStackTrace();

            return serverError().entity(e).build();

        } catch (ObjetoNaoEncontradoException e) {

            e.printStackTrace();

            return notFound().entity(e).build();
        }

        return noContent().build();
    }

    public void putItem(int bibliotecaId, int id, ItemAcervoDTO dto)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        try (IBuscador buscador = buscador(bibliotecaId)) {

            IItemAcervo item = buscador.itemPorId(id);

            this.converter(dto, item);

            this.flush();

        } catch (Exception e) {

            e.printStackTrace();

            throw new FalhaOperacaoException(e.getMessage(), e);
        }
    }
}
