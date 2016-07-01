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

import poo.library.comum.IEmprestimo;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Emprestimo implements IEmprestimo {

    private int id;

    private double valorEmprestimo;

    private Date devolverAte;
    private Date devolvidoEm;
    private Date realizadoEm;

    private ItemAcervo item;

    private Usuario usuario;

    @Override
    public Date getDevolverAte() {

        return this.devolverAte;
    }

    @Override
    public Date getDevolvidoEm() {

        return this.devolvidoEm;
    }

    @Override
    public int getId() {

        return this.id;
    }

    @Override
    public ItemAcervo getItem() {

        return this.item;
    }

    @Override
    public Date getRealizadoEm() {

        return this.realizadoEm;
    }

    @Override
    public Usuario getUsuario() {

        return this.usuario;
    }

    @Override
    public double getValorEmprestimo() {

        return this.valorEmprestimo;
    }

    public void setDevolverAte(Date devolverAte) {

        this.devolverAte = devolverAte;
    }

    public void setDevolvidoEm(Date devolvidoEm) {

        this.devolvidoEm = devolvidoEm;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setItem(ItemAcervo item) {

        this.item = item;
    }

    public void setRealizadoEm(Date realizadoEm) {

        this.realizadoEm = realizadoEm;
    }

    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;
    }

    public void setValorEmprestimo(double valorEmprestimo) {

        this.valorEmprestimo = valorEmprestimo;
    }
}
