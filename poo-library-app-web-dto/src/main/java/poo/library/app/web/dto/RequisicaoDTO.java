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

import java.util.Date;

import poo.library.comum.EStatusRequisicao;
import poo.library.comum.IIdentificavel;
import poo.library.util.Dates;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class RequisicaoDTO implements IIdentificavel {

    private int id;

    private int itemAcervoId;
    private int usuarioId;
    private int bibliotecaId;

    private Date realizadaEm;

    private EStatusRequisicao status;

    public RequisicaoDTO() { }

    public int getBibliotecaId() {

        return this.bibliotecaId;
    }

    @Override
    public int getId() {

        return this.id;
    }

    public int getItemAcervoId() {

        return this.itemAcervoId;
    }

    public String getRealizadaEm() {

        return Dates.formatWithTime(this.realizadaEm);
    }

    public EStatusRequisicao getStatus() {

        return this.status;
    }

    public int getUsuarioId() {

        return this.usuarioId;
    }

    public void setBibliotecaId(int bibliotecaId) {

        this.bibliotecaId = bibliotecaId;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setItemAcervoId(int itemAcervoId) {

        this.itemAcervoId = itemAcervoId;
    }

    public void setRealizadaEm(Date realizadaEm) {

        this.realizadaEm = realizadaEm;
    }

    public void setStatus(EStatusRequisicao status) {

        this.status = status;
    }

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
