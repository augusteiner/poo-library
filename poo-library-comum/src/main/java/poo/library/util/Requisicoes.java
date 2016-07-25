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

import org.apache.commons.lang3.builder.HashCodeBuilder;

import poo.library.comum.ILocacao;
import poo.library.comum.IRequisicao;
import poo.library.comum.IRequisicaoId;
import poo.library.comum.IReserva;

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

    public static boolean equals(IRequisicaoId one, Object other) {

        return

            other != null &&
            one.getClass().equals(other.getClass()) &&

            equals(one, (IRequisicaoId) other);
    }

    public static boolean equals(IRequisicaoId one, IRequisicaoId other) {

        return one.getBibliotecaId() == other.getBibliotecaId() &&
            one.getItemAcervoId() == other.getItemAcervoId() &&
            one.getUsuarioId() == other.getUsuarioId() &&

            one.getRealizadaEm() != null &&
            one.getRealizadaEm().equals(other.getRealizadaEm());
    }

    public static int hashCode(ILocacao locacao) {

        return hashCode(PRIMO_LOCACAO, PRIMO_LOCACAO_MULT, (IRequisicaoId) locacao);
    }

    private static int hashCode(
        int initialOddNumber,
        int multiplierOddNumber,

        IRequisicaoId requisicaoId) {

        HashCodeBuilder builder = new HashCodeBuilder(
            initialOddNumber,
            multiplierOddNumber);

        builder.append(requisicaoId.getUsuarioId())
            .append(requisicaoId.getItemAcervoId())
            .append(requisicaoId.getBibliotecaId())
            .appendSuper(requisicaoId.getRealizadaEm().hashCode());

        return builder.toHashCode();
    }

    public static int hashCode(IRequisicaoId requisicaoId) {

        return hashCode(PRIMO_REQUISICAO, PRIMO_REQUISICAO_MULT, requisicaoId);
    }

    public static int hashCode(IReserva reserva) {

        return hashCode(PRIMO_RESERVA, PRIMO_RESERVA_MULT, (IRequisicaoId) reserva);
    }

    public static String toString(IRequisicaoId requisicaoId) {

        return String.format(
            "(%d, %d, %d, %s)",

            requisicaoId.getUsuarioId(),
            requisicaoId.getItemAcervoId(),
            requisicaoId.getBibliotecaId(),

            FORMATO_DATA_PADRAO.format(requisicaoId.getRealizadaEm()));
    }
}
