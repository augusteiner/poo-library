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
import static poo.library.app.web.util.Inicializador.*;
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
public class AcervoBibliotecaResource implements ISubResource<ItemAcervoDTO>, IConversor<ItemAcervo> {

    public static final String PATH = "biblioteca/{bibliotecaId}/acervo";

    public static final Class<ItemAcervo> MODEL_CLASS = ItemAcervo.class;
    public static final Class<ItemAcervoDTO> DTO_CLASS = ItemAcervoDTO.class;

    public static final IConversor<ItemAcervoDTO> CONVERSOR = conversor(DTO_CLASS);

    private IDAO<Biblioteca> parentDAO;

    public AcervoBibliotecaResource() {

        this(init(BibliotecaResource.class).novoDAO().unwrap());
    }

    public AcervoBibliotecaResource(IDAO<Biblioteca> parentDAO) {

        this.parentDAO = parentDAO;
    }

    @Override
    public ItemAcervo converter(Object input) {

        return Conversores.converter(input, ItemAcervo.class);
    }

    @Override
    public void converter(Object input, ItemAcervo output) {

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

            this.deleteItem(bibliotecaId, id);

        } catch (Exception e) {

            return serverError().entity(e).build();
        }

        return noContent().build();
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

        ItemAcervo item;

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

    public Iterable<ItemAcervoDTO> getItens(int bibliotecaId)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        IBuscador buscador = buscador(bibliotecaId);

        try {
            return Iterables.convert(
                buscador.itens(),
                CONVERSOR);

        } finally {

            this.close(buscador);
        }
    }

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

        IBuscador buscador = buscador(bibliotecaId);
        ItemAcervo item = buscador.itemPorId(id);

        this.converter(dto, item);

        this.flush();

        this.close(buscador);
    }

    private Biblioteca bibliotecaPorId(int bibliotecaId) throws ObjetoNaoEncontradoException {

        return this.getParentDAO().find(bibliotecaId);
    }

    private IBuscador buscador(Biblioteca biblioteca) {

        IBuscador buscador = new DAOAcervo(this.getParentDAO());

        Biblioteca.exportarBuscador(biblioteca, buscador);

        return buscador;
    }

    private IBuscador buscador(int bibliotecaId)
        throws ObjetoNaoEncontradoException {

        return buscador(this.bibliotecaPorId(bibliotecaId));
    }

    private void close(IBuscador buscador) throws FalhaOperacaoException {

        try {

            buscador.close();

        } catch (Exception e) {

            e.printStackTrace();

            // TODO Jogar exceção ou efetuar log somente???
            throw new FalhaOperacaoException(e.getMessage(), e);
        }
    }

    private void deleteItem(int bibliotecaId, int id)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        IBuscador buscador = buscador(bibliotecaId);

        Biblioteca biblioteca = buscador.getBiblioteca();
        ItemAcervo item = (ItemAcervo) buscador.itemPorId(id);

        System.out.println(String.format(
            "Removendo item %s",

            item));

        biblioteca.removeAcervo(item);

        this.flush();

        this.close(buscador);
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

    private void flush() throws FalhaOperacaoException {

        try {

            this.getParentDAO().flush();

        } catch (FalhaOperacaoException e) {

            e.printStackTrace();

            throw e;
        }
    }

    private ItemAcervo getItem(int bibliotecaId, int id)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        IBuscador buscador = buscador(bibliotecaId);

        try {

            return (ItemAcervo) buscador.itemPorId(id);

        } finally {

            this.close(buscador);
        }
    }

    private void postItem(int bibliotecaId, ItemAcervoDTO itemAcervo)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        ItemAcervo item;
        IBuscador buscador = buscador(bibliotecaId);

        if (itemAcervo.getId() == 0) {

            Biblioteca biblioteca = this.bibliotecaPorId(bibliotecaId);

            item = this.converter(itemAcervo);

            biblioteca.addAcervo(item);

        } else {

            item = (ItemAcervo) buscador.itemPorId(itemAcervo.getId());

            this.converter(itemAcervo, item);
        }

        this.flush();

        this.close(buscador);

        CONVERSOR.converter(item, itemAcervo);
    }

    protected IDAO<Biblioteca> getParentDAO() {

        return this.parentDAO;
    }
}
