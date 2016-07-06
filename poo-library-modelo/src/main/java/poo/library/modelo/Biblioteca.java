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

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import poo.library.comum.IBiblioteca;
import poo.library.comum.IItemAcervo;
import poo.library.comum.ILocacao;
import poo.library.comum.IReserva;
import poo.library.comum.IUsuario;
import poo.library.util.IBibliotecaArmazem;
import poo.library.util.IBuscador;
import poo.library.util.ItemIndisponivelException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Biblioteca implements IBiblioteca {

    private int id;

    private String nome;

    private double multaDiaria;
    private int qteDiasValidadeReserva;
    private int qteDiasLocacao;

    private IBibliotecaArmazem armazem;

    public Biblioteca() { }

    public Biblioteca(String nome, double multaDiaria) {

        this.nome = nome;
        this.multaDiaria = multaDiaria;
    }

    @Override
    public void cancelarReserva(IItemAcervo item, IUsuario usuario) {

    }

    @Override
    public void cancelarReserva(IReserva reserva) {

    }

    @Override
    public void devolver(IItemAcervo item) {

    }

    @Override
    public Iterable<IItemAcervo> getAcervo() {

        return null;
    }

    @Override
    public Iterable<ILocacao> getAlugueis() {

        return null;
    }

    @Override
    public int getId() {

        return this.id;
    }

    @Override
    public double getMultaDiaria() {

        return this.multaDiaria;
    }

    @Override
    public String getNome() {

        return this.nome;
    }

    @Override
    public int getQteDiasValidadeReserva() {

        return this.qteDiasValidadeReserva;
    }

    @Override
    public Iterable<IReserva> getReservas() {

        return null;
    }

    public IBuscador getStorage() {

        return this.armazem;
    }

    @Override
    public Iterable<IUsuario> getUsuarios() {

        return null;
    }

    @Override
    public void locar(IItemAcervo item, IUsuario usuario) throws ItemIndisponivelException {

        if (item.getQteDisponivel() > 0) {

            // item.setQteDisponivel(item.getQteDisponivel() - 1);

            item.locar(usuario);
            usuario.locar(item);

            Instant dias = Instant.now().plus(
                this.getQteDiasLocacao(),
                ChronoUnit.DAYS);

            Date devolverAte = Date.from(dias);

            ILocacao l = new Locacao(
                item.getPrecoAluguel(),
                devolverAte,

                item.getId(),
                usuario.getId());

            this.armazem.salvarLocacao(l);

        } else {

            throw new ItemIndisponivelException(
                "Não há item disponível para locar.");
        }
    }

    @Override
    public void reservar(IItemAcervo item, IUsuario usuario) {

    }

    public void setId(int id) {

        this.id = id;
    }

    @Override
    public void setMultaDiaria(double multaDiaria) {

        this.multaDiaria = multaDiaria;
    }

    @Override
    public void setNome(String nome) {

        this.nome = nome;
    }

    public void setQteDiasValidadeReserva(int qteDiasValidadeReserva) {

        this.qteDiasValidadeReserva = qteDiasValidadeReserva;
    }

    public void setStorage(IBibliotecaArmazem armazem) {

        this.armazem = armazem;
    }

    @Override
    public int getQteDiasLocacao() {

        return this.qteDiasLocacao;
    }

    public void setQteDiasLocacao(int qteDiasLocacao) {

        this.qteDiasLocacao = qteDiasLocacao;
    }
}
