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

import javax.ws.rs.core.Response;

import poo.library.app.web.dto.ItemAcervoDTO;
import poo.library.app.web.util.Conversores;
import poo.library.app.web.util.ISubResource;
import poo.library.comum.IItemAcervo;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.comum.IBuscador;
import poo.library.util.AcervoEmMemoria;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.IConversor;
import poo.library.util.Iterables;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class AcervoResource implements ISubResource<ItemAcervoDTO>, IConversor<ItemAcervo> {

    public static final String PATH = "acervo";

    public static final Class<ItemAcervo> MODEL_CLASS = ItemAcervo.class;
    public static final Class<ItemAcervoDTO> DTO_CLASS = ItemAcervoDTO.class;
    public static final IConversor<ItemAcervoDTO> CONVERSOR = newConversor(DTO_CLASS);

    private IDAO<Biblioteca> parentDAO;

    public AcervoResource(IDAO<Biblioteca> parentDAO) {

        this.parentDAO = parentDAO;
    }

    @Override
    public ItemAcervo converter(Object input) {

        return Conversores.converter(input, MODEL_CLASS);
    }

    @Override
    public void converter(Object input, ItemAcervo output) {

        Conversores.converter(input, output);
    }

    @Override
    public Response get(int bibliotecaId) {

        try {

            Iterable<ItemAcervoDTO> iter = this.getItens(bibliotecaId);

            return ok().entity(iter).build();

        } catch (ObjetoNaoEncontradoException e) {

            return notFound().entity(e).build();
        }
    }

    @Override
    public Response get(int bibliotecaId, int id) {

        IItemAcervo item;

        try {

            Biblioteca biblioteca = this.bibliotecaPorId(bibliotecaId);

            IBuscador buscador = buscador(biblioteca);

            item = buscador.itemPorId(id);

        } catch (ObjetoNaoEncontradoException e) {

            return notFound().entity(e).build();
        }

        return ok().entity(CONVERSOR.converter(item)).build();
    }

    public Iterable<ItemAcervoDTO> getItens(int bibliotecaId) throws ObjetoNaoEncontradoException {

        Iterable<IItemAcervo> iter = this.bibliotecaPorId(bibliotecaId)
            .getAcervo();

        return Iterables.convert(
            iter,
            CONVERSOR);
    }

    @Override
    public Response post(int bibliotecaId, ItemAcervoDTO itemAcervo) {

        if (bibliotecaId != itemAcervo.getBibliotecaId()) {

            return badRequest()
                .entity(new IllegalArgumentException(
                    "Id da biblioteca deve ser igual ao do item do acervo"))
                .build();
        }

        try {

            return this.postItem(bibliotecaId, itemAcervo);

        } catch (ObjetoNaoEncontradoException e) {

            return notFound().entity(e).build();

        } catch (FalhaOperacaoException e) {

            return serverError().entity(e).build();
        }
    }

    @Override
    public Response put(int bibliotecaId, int itemAcervoId, ItemAcervoDTO itemAcervo) {

        try {

            return this.putItem(bibliotecaId, itemAcervoId, itemAcervo);

        } catch (FalhaOperacaoException e) {

            return serverError().entity(e).build();

        } catch (ObjetoNaoEncontradoException e) {

            return notFound().entity(e).build();
        }
    }

    public Response putItem(int bibliotecaId, int itemAcervoId, ItemAcervoDTO itemAcervo)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        ItemAcervo item;

        Biblioteca biblioteca = null;

        biblioteca = this.bibliotecaPorId(bibliotecaId);

        item = (ItemAcervo) buscador(biblioteca).itemPorId(itemAcervoId);

        this.converter(itemAcervo, item);

        this.getParentDAO().flush();

        return ok().entity(itemAcervo).build();
    }

    private Biblioteca bibliotecaPorId(int bibliotecaId) throws ObjetoNaoEncontradoException {

        return this.getParentDAO().find(bibliotecaId);
    }

    private IBuscador buscador(Biblioteca biblioteca) {

        IBuscador buscador = new AcervoEmMemoria();

        Biblioteca.exportarBuscador(biblioteca, buscador);

        return buscador;
    }

    private Response postItem(int bibliotecaId, ItemAcervoDTO itemAcervo)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        Biblioteca biblioteca = this.bibliotecaPorId(bibliotecaId);
        ItemAcervo item;

        if (itemAcervo.getId() == 0) {

            item = this.converter(itemAcervo);

        } else {

            item = (ItemAcervo) biblioteca.getBuscador()
                .itemPorId(itemAcervo.getId());
        }

        biblioteca.addAcervo(item);

        this.getParentDAO().flush();

        return ok().entity(CONVERSOR.converter(item)).build();
    }

    protected IDAO<Biblioteca> getParentDAO() {

        return this.parentDAO;
    }
}
