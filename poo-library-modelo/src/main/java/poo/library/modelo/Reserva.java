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
import poo.library.comum.IReserva;
import poo.library.util.Dates;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Reserva extends Requisicao implements IReserva {

    private Date validaAte;

    private Biblioteca biblioteca;

    public Reserva() { }

    public Reserva(
        Date validaAte,

        ItemAcervo itemAcervo,
        Usuario usuario) {

        this(validaAte, itemAcervo.getId(), usuario.getId(), itemAcervo.getBibliotecaId());
    }

    public Reserva(
        Date validaAte,

        int itemAcervoId,
        int usuarioId,
        int bibliotecaId) {

        super(itemAcervoId, usuarioId, bibliotecaId);

        this.validaAte = validaAte;
    }

    @Override
    public void atualizarStatus(Date referencia) {

        if (!this.getStatus().isDefinitivo() &&
            this.getValidaAte().compareTo(referencia) < 0) {

            this.setStatus(EStatusRequisicao.VENCIDA);
        }
    }

    @Override
    public Date getValidaAte() {

        return this.validaAte;
    }

    public void setValidaAte(Date validaAte) {

        this.validaAte = validaAte;
    }

    @Override
    public Biblioteca getBiblioteca() {

        return biblioteca;
    }

    @Override
    public void setBiblioteca(Biblioteca biblioteca) {

        this.biblioteca = biblioteca;
    }

    @Override
    public String toString() {

        return String.format(
            "Reserva: %s; validade: %s",

            super.toString(),

            Dates.format(this.getValidaAte()));
    }
}
