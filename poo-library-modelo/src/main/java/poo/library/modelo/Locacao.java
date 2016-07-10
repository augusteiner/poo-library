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

import poo.library.comum.IItemAcervo;
import poo.library.comum.ILocacao;
import poo.library.comum.IUsuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Locacao extends Requisicao implements ILocacao {

    private double precoCobrado;

    private Date devolverAte;
    private Date devolvidoEm;

    public Locacao() { }

    public Locacao(
        Date devolverAte,

        IItemAcervo item,
        IUsuario usuario) {

        super(item.getId(), usuario.getId());

        this.init(devolverAte, item);
    }

    public Locacao(
        Date devolverAte,

        ItemAcervo item,
        Usuario usuario) {

        super(item, usuario);

        this.init(devolverAte, item);
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
    public double getPrecoCobrado() {

        return this.precoCobrado;
    }

    private void init(Date devolverAte, IItemAcervo item) {

        this.devolverAte = devolverAte;
        this.precoCobrado = item.getPrecoLocacao();
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
    public void setPrecoCobrado(double precoCobrado) {

        this.precoCobrado = precoCobrado;
    }

    @Override
    public String toString() {

        return String.format(
            super.toString() + " ~ (R$ %.2f) :: %s",

            this.getPrecoCobrado(),

            this.getUsuario());
    }
}
