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

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import poo.library.app.web.dto.ReservaDTO;
import poo.library.app.web.util.ISubResource;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Reserva;
import poo.library.modelo.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Path(ReservaUsuarioResource.PATH)
public class ReservaUsuarioResource implements ISubResource<ReservaDTO> {

    public static final String PATH = "reserva";

    public static final Class<Reserva> MODEL_CLASS = Reserva.class;
    public static final Class<ReservaDTO> DTO_CLASS = ReservaDTO.class;

    private IDAO<Usuario> parentDAO;

    public ReservaUsuarioResource(IDAO<Usuario> parentDAO) {

        this.parentDAO = parentDAO;
    }

    @Override
    public Response get(int usuarioId) {

        return null;
    }

    @Override
    public Response get(int usuarioId, int id) {

        return null;
    }

    @Override
    public Response put(int usuarioId, int id, ReservaDTO obj) {

        return null;
    }

    @Override
    public Response post(int usuarioId, ReservaDTO obj) {

        return null;
    }

    @Override
    public Response delete(int usuarioId, int id) {

        return null;
    }

    protected IDAO<Usuario> getParentDAO() {

        return parentDAO;
    }
}
