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

import static poo.library.util.Iterables.*;
import static poo.library.app.web.util.Responses.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import poo.library.app.web.dto.ItemAcervoDTO;
import poo.library.modelo.comum.IBuscador;
import poo.library.util.IConversor;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Path(AcervoBibliotecaSearchResource.PATH)
public class AcervoBibliotecaSearchResource {

    public static final String PATH = "search";
    public static final IConversor<ItemAcervoDTO> CONVERSOR_DTO = AcervoBibliotecaResource.CONVERSOR_DTO;

    private final AcervoBibliotecaResource self = new AcervoBibliotecaResource();

    @GET
    @Path("/biblioteca/{parentId}/acervo")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response httpGet(
        @PathParam("parentId") int bibliotecaId,
        @QueryParam("term") String term) {

        IBuscador buscador = self.buscador(bibliotecaId);

        Iterable<?> iter = convert(
            buscador.itensPorTermo(term),
            CONVERSOR_DTO);

        return ok().entity(iter).build();
    }
}
