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
package poo.library.modelo;

import java.util.Date;

import poo.library.comum.EStatusRequisicao;
import poo.library.comum.ILocacao;
import poo.library.util.Locacoes;
import poo.library.util.R;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Locacao extends Requisicao implements ILocacao {

    private double precoCobrado;

    private Date devolverAte;
    private Date devolvidoEm;

    private int diasAtraso;

    public Locacao() { }

    public Locacao(
        Date devolverAte,

        ItemAcervo item,
        Usuario usuario) {

        super(item, usuario);

        this.init(devolverAte, item);
    }

    @Override
    public void atualizarStatus(Date referencia) {

        if (this.getStatus().isTemporario() &&
            this.getDevolverAte().compareTo(referencia) < 0) {

            this.setStatus(EStatusRequisicao.VENCIDA);
        }
    }

    @Override
    public Date getDevolverAte() {

        return this.devolverAte;
    }

    @Override
    public Date getDevolvidoEm() {

        return this.devolvidoEm;
    }

    @Override
    public int getDiasAtraso() {

        return this.diasAtraso;
    }

    public long getDiasAtraso(Date referencia) {

        return Locacoes.diasAtraso(this, referencia);
    }

    @Override
    public double getPrecoCobrado() {

        return this.precoCobrado;
    }

    @Override
    public void setDevolverAte(Date devolverAte) {

        this.devolverAte = devolverAte;
    }

    @Override
    public void setDevolvidoEm(Date devolvidoEm) {

        this.devolvidoEm = devolvidoEm;
    }

    @Override
    public void setDiasAtraso(int diasAtraso) {

        this.diasAtraso = diasAtraso;
    }

    @Override
    public void setPrecoCobrado(double precoCobrado) {

        this.precoCobrado = precoCobrado;
    }

    @Override
    public String toString() {

        return String.format(
            R.LOCALE_PADRAO,

            "Locação: %s; preço: %.2f; devolver: %s; devolvido: %s",

            super.toString(),

            this.getPrecoCobrado(),
            this.getDevolverAte(),
            this.getDevolvidoEm());
    }

    private void init(Date devolverAte, ItemAcervo item) {

        this.devolverAte = devolverAte;
        this.precoCobrado = item.getPrecoLocacao();
    }
}
