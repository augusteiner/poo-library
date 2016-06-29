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

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import poo.library.comum.IUsuario;
import poo.library.comum.ObjetoNaoEncontradoException;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Path("usuario")
public class UsuarioResource {

    private IDAO<IUsuario> dao = DAOFactory.createNew(IUsuario.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<IUsuario> get() {

        return this.dao.all();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {

        IUsuario user;

        try {

            user = this.dao.first("Id = ?", id);

        } catch (ObjetoNaoEncontradoException e) {

            throw new NotFoundException(
                String.format("Usuário #%s não encontrado", id), e);
        }

        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {

        this.dao.delete("Id = ?", id);
    }

    @POST
    public Response post(Usuario usuario) {

        this.dao.save(usuario);

        URI createdUri = URI.create(String.format(
            "usuario/%d",
            usuario.getId()));

        return Response.created(createdUri).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response put(
        @PathParam("id") int id,
        Usuario usuario) {

        this.dao.save(usuario);

        return Response.ok().build();
    }
}
