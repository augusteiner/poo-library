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

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class Requisicao implements IRequisicao {

    private int id;

    private Date realizadaEm;

    private ItemAcervo itemAcervo;
    private int itemAcervoId;

    private Usuario usuario;
    private int usuarioId;

    public Requisicao() { }

    public Requisicao(
        int itemAcervoId,
        int usuarioId) {

        this.itemAcervoId = itemAcervoId;
        this.usuarioId = usuarioId;

        this.realizadaEm = new Date();
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
    public Usuario getUsuario() {

        return this.usuario;
    }

    @Override
    public int getUsuarioId() {

        return this.usuarioId;
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

    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;
    }

    @Override
    public void setUsuarioId(int usuarioId) {

        this.usuarioId = usuarioId;
    }
}
