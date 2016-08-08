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

import java.util.Collection;

import poo.library.comum.ECategoriaItem;
import poo.library.comum.IItemAcervo;
import poo.library.comum.IUsuario;
import poo.library.util.ItensAcervo;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class ItemAcervo implements IItemAcervo {

    private int id;

    private Biblioteca biblioteca;
    private int bibliotecaId;

    private String titulo;
    private String autor;
    private String sinopse;

    private double precoLocacao;

    private int qteReservada;
    private int qteLocada;
    private int qteTotal;

    private ECategoriaItem categoria;

    private Collection<Reserva> reservas;

    public ItemAcervo() { }

    public ItemAcervo(
        String autor,
        double precoLocacao,
        int qteDisponivel,
        int qteTotal,
        int bibliotecaId) {

        this.autor = autor;
        this.precoLocacao = precoLocacao;
        this.qteTotal = qteTotal;

        this.bibliotecaId = bibliotecaId;
    }

    protected ItemAcervo(ECategoriaItem categoria) {

        this.categoria = categoria;
    }

    protected ItemAcervo(
        String titulo,
        String autor,
        double precoAluguel,
        ECategoriaItem categoria,
        int bibliotecaId) {

        this(
            autor,
            precoAluguel,
            0,
            0,
            bibliotecaId);

        this.categoria = categoria;
    }

    @Override
    public void devolver() {

        this.setQteLocada(this.getQteLocada() - 1);
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof ItemAcervo &&
            ((ItemAcervo) obj).getId() == this.getId();
    }

    @Override
    public String getAutor() {

        return this.autor;
    }

    @Override
    public Biblioteca getBiblioteca() {

        return this.biblioteca;
    }

    @Override
    public int getBibliotecaId() {

        return this.bibliotecaId;
    }

    @Override
    public ECategoriaItem getCategoria() {

        return this.categoria;
    }

    @Override
    public int getId() {

        return this.id;
    }

    @Override
    public double getPrecoLocacao() {

        return this.precoLocacao;
    }

    @Override
    public int getQteDisponivel() {

        return this.getQteTotal() -
            this.getQteLocada() -
            this.getQteReservada();
    }

    @Override
    public int getQteLocada() {

        return this.qteLocada;
    }

    @Override
    public int getQteReservada() {

        return this.qteReservada;
    }

    @Override
    public int getQteTotal() {

        return this.qteTotal;
    }

    @Override
    public Collection<Reserva> getReservas() {

        return this.reservas;
    }

    @Override
    public String getSinopse() {

        return this.sinopse;
    }

    @Override
    public String getTitulo() {

        return titulo;
    }

    @Override
    public void locar(IUsuario usuario) {

        this.setQteLocada(this.getQteLocada() + 1);
    }

    @Override
    public boolean match(String term) {

        return ItensAcervo.match(this, term);
    }

    @Override
    public void reservar(IUsuario usuario) {

        this.setQteReservada(this.getQteReservada() + 1);
    }

    @Override
    public void setAutor(String autor) {

        this.autor = autor;
    }

    public void setBiblioteca(Biblioteca biblioteca) {

        this.biblioteca = biblioteca;

        if (biblioteca != null) {

            this.bibliotecaId = biblioteca.getId();
        }
    }

    @Override
    public void setBibliotecaId(int bibliotecaId) {

        this.bibliotecaId = bibliotecaId;
    }

    public void setCategoria(ECategoriaItem categoria) {

        this.categoria = categoria;
    }

    public void setId(int id) {

        this.id = id;
    }

    @Override
    public void setPrecoLocacao(double precoLocacao) {

        this.precoLocacao = precoLocacao;
    }

    public void setQteTotal(int qteTotal) {

        this.qteTotal = qteTotal;
    }

    public void setReservas(Collection<Reserva> reservas) {

        this.reservas = reservas;
    }

    public void setSinopse(String sinopse) {

        this.sinopse = sinopse;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    @Override
    public String toString() {

        return ItensAcervo.toString(this);
    }

    private void setQteLocada(int qteLocada) {

        this.qteLocada = qteLocada;
    }

    private void setQteReservada(int qteReservada) {

        this.qteReservada = qteReservada;
    }
}
