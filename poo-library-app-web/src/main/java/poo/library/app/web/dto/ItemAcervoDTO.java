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
package poo.library.app.web.dto;

import poo.library.comum.ECategoriaItem;
import poo.library.comum.IIdentificavel;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public /* abstract */ class ItemAcervoDTO implements IIdentificavel {

    private int id;

    private String autor;

    private double precoLocacao;

    private int qteDisponivel;
    private int qteTotal;

    private ECategoriaItem categoria;

    private int bibliotecaId;

    public ItemAcervoDTO() { }

    public String getAutor() {

        return this.autor;
    }

    public int getBibliotecaId() {

        return this.bibliotecaId;
    }

    public ECategoriaItem getCategoria() {

        return this.categoria;
    }

    @Override
    public int getId() {

        return this.id;
    }

    public double getPrecoLocacao() {

        return this.precoLocacao;
    }

    public int getQteDisponivel() {

        return this.qteDisponivel;
    }

    public int getQteTotal() {

        return this.qteTotal;
    }

    public void setAutor(String autor) {

        this.autor = autor;
    }

    public void setBibliotecaId(int bibliotecaId) {

        this.bibliotecaId = bibliotecaId;
    }

    public void setCategoria(ECategoriaItem categoria) {

        this.categoria = categoria;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setPrecoLocacao(double precoLocacao) {

        this.precoLocacao = precoLocacao;
    }

    public void setQteDisponivel(int qteDisponivel) {

        this.qteDisponivel = qteDisponivel;
    }

    public void setQteTotal(int qteTotal) {

        this.qteTotal = qteTotal;
    }
}
