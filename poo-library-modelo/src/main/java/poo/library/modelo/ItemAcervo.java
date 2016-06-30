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

import poo.library.comum.IItemAcervo;
import poo.library.comum.IUsuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class ItemAcervo implements IItemAcervo {

    private int id;
    private double custoEmprestimo;
    private Emprestimo ultimoEmprestimo;

    @Override
    public double devolver() {

        return 0;
    }

    @Override
    public void emprestar(IUsuario usuario) {

        //
    }

    @Override
    public double getCustoEmprestimo() {

        return this.custoEmprestimo;
    }

    @Override
    public int getId() {

        return this.id;
    }

    @Override
    public Emprestimo getUltimoEmprestimo() {

        return this.ultimoEmprestimo;
    }

    @Override
    public void reservar(IUsuario usuario) {

        //
    }

    public void setCustoEmprestimo(double custoEmprestimo) {

        this.custoEmprestimo = custoEmprestimo;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setUltimoEmprestimo(Emprestimo ultimoEmprestimo) {

        this.ultimoEmprestimo = ultimoEmprestimo;
    }
}
