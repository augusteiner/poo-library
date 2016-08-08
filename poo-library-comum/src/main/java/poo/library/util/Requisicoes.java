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
package poo.library.util;

import static poo.library.util.R.*;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import poo.library.comum.IRequisicao;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Requisicoes {

    public static boolean equals(IRequisicao one, Object other) {

        return

            other != null &&
            one.getClass().equals(other.getClass()) &&

            ((Object) one.getId()).equals(((IRequisicao) other).getId());
    }

    public static String toString(IRequisicao requisicaoId) {

        return String.format(
            "(%d, %d, %d, %s)",

            requisicaoId.getUsuarioId(),
            requisicaoId.getItemAcervoId(),
            requisicaoId.getBibliotecaId(),

            FORMATTER_DATA_HORA_PADRAO.format(requisicaoId.getRealizadaEm()));
    }

    public static Date adicionarDias(Date referencia, int qteDias) {

        return new Date(referencia.getTime() + DateUtils.MILLIS_PER_DAY * qteDias);
    }
}
