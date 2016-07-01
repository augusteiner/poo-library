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

import poo.library.comum.IApostila;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Apostila extends ItemAcervo implements IApostila {

    private String titulo;
    private String autor;

    private int quantidade;

    @Override
    public String getAutor() {

        return this.autor;
    }

    @Override
    public int getQuantidade() {

        return this.quantidade;
    }

    @Override
    public String getTitulo() {

        return this.titulo;
    }

    public void setAutor(String autor) {

        this.autor = autor;
    }

    public void setQuantidade(int quantidade) {

        this.quantidade = quantidade;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }
}