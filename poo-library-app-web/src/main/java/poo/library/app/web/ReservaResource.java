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

import static poo.library.app.web.util.DAOFactory.newDAO;

import javax.ws.rs.Path;

import poo.library.app.web.dto.ReservaDTO;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Reserva;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Path(ReservaResource.PATH)
public class ReservaResource extends GenericResource<ReservaDTO> {

    public static final String PATH = "reserva";

    public static final Class<Reserva> MODEL_CLASS = Reserva.class;
    public static final Class<ReservaDTO> DTO_CLASS = ReservaDTO.class;

    //private final IDAO<Reserva> dao;

    public ReservaResource() {

        this(DAOFactory.createNew(MODEL_CLASS));
    }

    public ReservaResource(IDAO<Reserva> dao) {

        super(PATH, newDAO(dao, MODEL_CLASS, DTO_CLASS));

        //this.dao = dao;
    }
}
