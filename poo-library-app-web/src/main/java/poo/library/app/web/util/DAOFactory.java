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
package poo.library.app.web.util;

import static poo.library.app.web.util.Conversores.newConversor;

import poo.library.comum.IIdentificavel;
import poo.library.dao.comum.IDAO;
import poo.library.util.IConversor;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class DAOFactory {

    public static <T, M extends IIdentificavel> IDAO<T> newDAO(
        IDAO<M> dao,
        Class<M> clsModel,
        Class<T> clsDto) {

        if (dao == null)
            throw new IllegalArgumentException("Argumento dao deve ser válido");

        if (clsModel == null)
            throw new IllegalArgumentException("Argumento clsModel deve ser válido");

        if (clsDto == null)
            throw new IllegalArgumentException("Argumento clsDto deve ser válido");

        return newDAOConversivel(dao, clsModel, clsDto);
    }

    private static <T, M> IDAO<T> newDAOConversivel(
        IDAO<M> dao,
        Class<M> clsModel,
        Class<T> clsDto) {

        IConversor<T> mapper;
        IConversor<M> inverso;

        mapper = newConversor(clsModel, clsDto);
        inverso = newConversor(clsDto, clsModel);

        return new DAOConversor<M, T>(
            dao,
            mapper,
            inverso);
    }
}
