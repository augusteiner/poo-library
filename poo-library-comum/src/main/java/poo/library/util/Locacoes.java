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

import static java.math.BigDecimal.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import poo.library.comum.ILocacao;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Locacoes {

    public static void atualizar(
        Collection<? extends ILocacao> locacoes,
        Date referencia) {

        for (ILocacao locacao : locacoes) {

            System.out.println(String.format(
                "Locação (%s; item: %s)",

                locacao,
                locacao.getItemAcervo()));

            locacao.atualizarStatus(R.CALENDAR_PADRAO.getTime());
            locacao.setDiasAtraso((int) diasAtraso(locacao, referencia));
        }
    }

    public static void atualizar(Date referencia, ILocacao... locacoes) {

        atualizar(
            Arrays.asList(locacoes),
            referencia);
    }

    public static long diasAtraso(ILocacao locacao, Date referencia) {

        if (referencia.compareTo(locacao.getDevolverAte()) > 0) {

            long diff = referencia.getTime() - locacao.getDevolverAte().getTime();

            return diff / DateUtils.MILLIS_PER_DAY;

        } else {

            return 0;
        }
    }

    public static double totalAPagar(ILocacao locacao, Date referencia) {

        BigDecimal diasAtraso = valueOf(locacao.getDiasAtraso());
        BigDecimal precoCobrado = valueOf(locacao.getPrecoCobrado());
        BigDecimal multa = valueOf(locacao.getBiblioteca().getMultaDiaria());

        BigDecimal totalAPagar = multa.multiply(diasAtraso);

        System.out.println(String.format(
            "Total a pagar R$ %.2f (atraso: %d dias; preço: %.2f; multa: %.2f)",

            totalAPagar,
            diasAtraso.intValue(),
            precoCobrado,
            multa));

        return totalAPagar.doubleValue();
    }
}
