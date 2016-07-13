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
import java.util.Collection;
import java.util.Date;

import poo.library.comum.IItemAcervo;
import poo.library.comum.ILocacao;
import poo.library.comum.IReserva;
import poo.library.comum.IUsuario;
import poo.library.modelo.comum.IAcervo;
import poo.library.modelo.comum.IBiblioteca;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.IBuscador;
import poo.library.util.ItemIndisponivelException;
import poo.library.util.Iterables;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Biblioteca implements IBiblioteca {

    private int id;

    private String nome;

    private double multaDiaria;
    private int qteDiasValidadeReserva;
    private int qteDiasLocacao;

    private IAcervo armazem;

    private Collection<ItemAcervo> acervo;
    private Collection<Reserva> reservas;

    public Biblioteca() { }

    public Biblioteca(String nome, double multaDiaria) {

        this.nome = nome;
        this.multaDiaria = multaDiaria;
    }

    @Override
    public Iterable<IItemAcervo> getAcervo() {

        return Iterables.cast(this.acervo);
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

        return Iterables.cast(this.reservas);
    }

    public IBuscador getBuscador() {

        return this.armazem;
    }

    @Override
    public Iterable<IUsuario> getUsuarios() {

        return null;
    }

    @Override
    public void locar(ItemAcervo item, Usuario usuario) throws ItemIndisponivelException, FalhaOperacaoException {

        if (item.getQteDisponivel() > 0) {

            // item.setQteDisponivel(item.getQteDisponivel() - 1);

            item.locar(usuario);
            usuario.locar(item);

            Instant dias = Instant.now().plus(
                this.getQteDiasLocacao(),
                ChronoUnit.DAYS);

            Date devolverAte = Date.from(dias);

            Locacao l = new Locacao(
                devolverAte,

                item,
                usuario);

            this.armazem.salvarLocacao(l);

        } else {

            throw new ItemIndisponivelException(
                "Não há item disponível para locar.");
        }
    }

    @Override
    public void reservar(ItemAcervo item, Usuario usuario) throws FalhaOperacaoException {

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

    public void setAcervo(Collection<ItemAcervo> acervo) {

        this.acervo = acervo;
    }

    @Override
    public int getQteDiasLocacao() {

        return this.qteDiasLocacao;
    }

    public void setQteDiasLocacao(int qteDiasLocacao) {

        this.qteDiasLocacao = qteDiasLocacao;
    }

    @Override
    public IReserva cancelar(int reservaId)
        throws ObjetoNaoEncontradoException {

        return null;
    }

    @Override
    public ILocacao devolver(int locacaoId)
        throws ObjetoNaoEncontradoException {

        return null;
    }

    @Override
    public ILocacao locar(int itemAcervoId, int usuarioId)
        throws ObjetoNaoEncontradoException {

        return null;
    }

    @Override
    public IReserva reservar(int itemAcervoId, int usuarioId)
        throws ObjetoNaoEncontradoException {

        return null;
    }

    @Override
    public void devolver(Locacao locacao) throws FalhaOperacaoException {

    }

    @Override
    public void cancelar(Reserva reserva) throws FalhaOperacaoException {

    }
}
