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

import poo.library.comum.IRequisicao;
import poo.library.util.Requisicoes;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class Requisicao implements IRequisicao {

    private RequisicaoId id;

    private ItemAcervo itemAcervo;
    private Usuario usuario;
    private Biblioteca biblioteca;

    public Requisicao() { }

    public Requisicao(
        int itemAcervoId,
        int usuarioId,
        int bibliotecaId) {

        this.id = new RequisicaoId();

        this.id.setItemAcervoId(itemAcervoId);
        this.id.setUsuarioId(usuarioId);
        this.id.setBibliotecaId(bibliotecaId);

        this.id.setRealizadaEm(new Date());
    }

    public Requisicao(
        ItemAcervo itemAcervo,
        Usuario usuario) {

        this(itemAcervo.getId(), usuario.getId(), itemAcervo.getBibliotecaId());

        this.itemAcervo = itemAcervo;
        this.biblioteca = itemAcervo.getBiblioteca();

        this.usuario = usuario;
    }

    @Override
    public Biblioteca getBiblioteca() {

        return biblioteca;
    }

    @Override
    public int getBibliotecaId() {

        return this.getId().getBibliotecaId();
    }

    @Override
    public RequisicaoId getId() {

        return this.id;
    }

    @Override
    public ItemAcervo getItemAcervo() {

        return this.itemAcervo;
    }

    @Override
    public int getItemAcervoId() {

        return this.getId().getItemAcervoId();
    }

    @Override
    public Date getRealizadaEm() {

        return this.getId().getRealizadaEm();
    }

    @Override
    public Usuario getUsuario() {

        return this.usuario;
    }

    @Override
    public int getUsuarioId() {

        return this.getId().getUsuarioId();
    }

    public void setBiblioteca(Biblioteca biblioteca) {

        this.biblioteca = biblioteca;
    }

    @Override
    public void setBibliotecaId(int bibliotecaId) {

        this.setBibliotecaId(bibliotecaId);
    }

    public void setId(RequisicaoId id) {

        this.id = id;
    }

    public void setItemAcervo(ItemAcervo itemAcervo) {

        this.itemAcervo = itemAcervo;
    }

    @Override
    public void setItemAcervoId(int itemAcervoId) {

        this.setItemAcervoId(itemAcervoId);
    }

    @Override
    public void setRealizadaEm(Date realizadaEm) {

        this.getId().setRealizadaEm(realizadaEm);
    }

    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;
    }

    @Override
    public void setUsuarioId(int usuarioId) {

        this.getId().setUsuarioId(usuarioId);
    }

    @Override
    public boolean equals(Object other) {

        return Requisicoes.equals(
            (IRequisicao) this,
            other);
    }

    @Override
    public String toString() {

        return String.format(
            "#%s - %s :: %s",

            this.getId(),

            this.getItemAcervo(),

            this.getUsuario());
    }
}
