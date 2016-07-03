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

import poo.library.comum.ECategoriaItem;
import poo.library.comum.ILivro;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Livro extends ItemAcervo implements ILivro {

    private String titulo;
    private String isbn;
    private Integer edicao;

    public Livro(
        String autor,
        String titulo,
        double precoAluguel,
        int bibliotecaId) {

        super(
            autor,
            precoAluguel,
            ECategoriaItem.LIVRO,
            bibliotecaId);

        this.titulo = titulo;
    }

    @Override
    public Integer getEdicao() {

        return this.edicao;
    }

    @Override
    public String getIsbn() {

        return this.isbn;
    }

    @Override
    public String getTitulo() {

        return this.titulo;
    }

    public void setEdicao(Integer edicao) {

        this.edicao = edicao;
    }

    public void setIsbn(String isbn) {

        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }
}
