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
import static poo.library.util.Iterables.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import poo.library.app.web.dto.BibliotecaDTO;
import poo.library.app.web.dto.ReservaDTO;
import poo.library.app.web.util.Conversores;
import poo.library.app.web.util.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Biblioteca;
import poo.library.util.IConversor;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Path(BibliotecaResource.PATH)
public class BibliotecaResource extends GenericResource<BibliotecaDTO>
    implements IConversor<Biblioteca> {

    public static final String PATH = "biblioteca";
    private IDAO<Biblioteca> dao;

    public BibliotecaResource() {

        this(poo.library.dao.comum.DAOFactory.novoDAO(Biblioteca.class));
    }

    public BibliotecaResource(IDAO<Biblioteca> dao) {

        super(PATH, DAOFactory.novoDAO(dao, BibliotecaDTO.class));

        this.dao = dao;
    }

    @GET
    @Path("/{id}/reserva")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getReservas(@PathParam("id") int id) {

        Biblioteca biblioteca;

        try {

            biblioteca = converter(this.dao.find(id));

        } catch (ObjetoNaoEncontradoException e) {

            return notFound().entity(e).build();
        }

        Iterable<?> iter = convert(
            biblioteca.getReservas(),
            conversor(ReservaDTO.class));

        return ok().entity(iter).build();
    }

    @Override
    public Biblioteca converter(Object input) {

        return Conversores.converter(input, Biblioteca.class);
    }

    @Override
    public void converter(Object input, Biblioteca output) {

        Conversores.converter(input, output);
    }
}
