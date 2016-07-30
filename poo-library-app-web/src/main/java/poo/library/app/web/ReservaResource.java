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

import java.net.URI;

import poo.library.app.web.dto.ReservaDTO;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Biblioteca;
import poo.library.util.IConversor;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class ReservaResource extends GenericSubResource<ReservaDTO> {

    public static final String PATH = "biblioteca/{parentId}/reserva";
    public static final IConversor<ReservaDTO> CONVERSOR_DTO = conversor(ReservaDTO.class);

    private IDAO<Biblioteca> parentDAO;

    public ReservaResource() {

        this(DAOFactory.novoDAO(Biblioteca.class));
    }

    public ReservaResource(IDAO<Biblioteca> dao) {

        this.parentDAO = dao;

        this.initBehavior(this);
    }

    @Override
    public URI createdAt(int bibliotecaId, int id) {

        return URI.create(String.format(
            "biblioteca/%d/reserva/%d",

            bibliotecaId,
            id));
    }

    @Override
    protected IConversor<ReservaDTO> getConversorDTO() {

        return CONVERSOR_DTO;
    }

    @Override
    protected IDAO<Biblioteca> getParentDAO() {

        return this.parentDAO;
    }
}
