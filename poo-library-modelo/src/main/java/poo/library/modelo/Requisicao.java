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
import poo.library.comum.IRequisicao;
import poo.library.util.R;
import poo.library.util.Requisicoes;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class Requisicao implements IRequisicao {

    private int id;

    private ItemAcervo itemAcervo;
    private int itemAcervoId;

    private Usuario usuario;
    private int usuarioId;

    private Biblioteca biblioteca;
    private int bibliotecaId;

    private Date realizadaEm;

    private EStatusRequisicao status;

    public Requisicao() {

        this.setRealizadaEm(R.CALENDAR_PADRAO.getTime());

        this.status = EStatusRequisicao.PADRAO;
    }

    public Requisicao(
        int itemAcervoId,
        int usuarioId,
        int bibliotecaId) {

        this();

        this.setItemAcervoId(itemAcervoId);
        this.setUsuarioId(usuarioId);
        this.setBibliotecaId(bibliotecaId);
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
    public abstract void atualizarStatus(Date referencia);

    @Override
    public boolean equals(Object other) {

        return Requisicoes.equals(
            (IRequisicao) this,
            other);
    }

    @Override
    public Biblioteca getBiblioteca() {

        return biblioteca;
    }

    @Override
    public int getBibliotecaId() {

        return this.bibliotecaId;
    }

    @Override
    public int getId() {

        return this.id;
    }

    @Override
    public ItemAcervo getItemAcervo() {

        return this.itemAcervo;
    }

    @Override
    public int getItemAcervoId() {

        return this.itemAcervoId;
    }

    @Override
    public Date getRealizadaEm() {

        return this.realizadaEm;
    }

    @Override
    public EStatusRequisicao getStatus() {

        return this.status;
    }

    @Override
    public Usuario getUsuario() {

        return this.usuario;
    }

    @Override
    public int getUsuarioId() {

        return this.usuarioId;
    }

    public void setBiblioteca(Biblioteca biblioteca) {

        this.biblioteca = biblioteca;
    }

    @Override
    public void setBibliotecaId(int bibliotecaId) {

        this.bibliotecaId = bibliotecaId;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setItemAcervo(ItemAcervo itemAcervo) {

        this.itemAcervo = itemAcervo;
    }

    @Override
    public void setItemAcervoId(int itemAcervoId) {

        this.itemAcervoId = itemAcervoId;
    }

    @Override
    public void setRealizadaEm(Date realizadaEm) {

        this.realizadaEm = realizadaEm;
    }

    public void setStatus(EStatusRequisicao status) {

        this.status = status;
    }

    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;
    }

    @Override
    public void setUsuarioId(int usuarioId) {

        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {

        return String.format(
            "#%d; item: #%d; biblioteca: #%d usuário: #%d",

            this.getId(),
            this.getItemAcervoId(),
            this.getBibliotecaId(),
            this.getUsuarioId());
    }
}
