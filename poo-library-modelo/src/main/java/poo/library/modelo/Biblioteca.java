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
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import poo.library.comum.EStatusRequisicao;
import poo.library.modelo.comum.IBiblioteca;
import poo.library.modelo.comum.IBuscador;
import poo.library.util.Bibliotecas;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.ItemIndisponivelException;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Biblioteca implements IBiblioteca {

    private int id;

    private String nome;
    private String endereco;

    private double multaDiaria;
    private int qteDiasValidadeReserva;
    private int qteDiasLocacao;

    private IBuscador buscador;

    private Collection<ItemAcervo> acervo;

    private Collection<Reserva> reservas;
    private Collection<Locacao> locacoes;

    public Biblioteca() {

        this.acervo = new LinkedList<ItemAcervo>();

        this.reservas = new LinkedList<Reserva>();
        this.locacoes = new LinkedList<Locacao>();
    }

    public Biblioteca(String nome, double multaDiaria) {

        this();

        this.nome = nome;
        this.multaDiaria = multaDiaria;
    }

    @Override
    public Reserva cancelar(int reservaId)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        Reserva reserva = (Reserva) this.getBuscador().reservaPorId(reservaId);

        this.cancelar(reserva);

        return reserva;
    }

    @Override
    public void cancelar(Reserva reserva) {

        reserva.setStatus(EStatusRequisicao.CANCELADA);
    }

    @Override
    public Locacao devolver(int locacaoId)
        throws ObjetoNaoEncontradoException {

        return null;
    }

    @Override
    public void devolver(Locacao locacao) {

    }

    @Override
    public Collection<ItemAcervo> getAcervo() {

        return Collections.unmodifiableCollection(this.acervo);
    }

    public IBuscador getBuscador() {

        return this.buscador;
    }

    @Override
    public String getEndereco() {

        return this.endereco;
    }

    @Override
    public int getId() {

        return this.id;
    }

    @Override
    public Collection<Locacao> getLocacoes() {

        return Collections.unmodifiableCollection(this.locacoes);
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
    public int getQteDiasLocacao() {

        return this.qteDiasLocacao;
    }

    @Override
    public int getQteDiasValidadeReserva() {

        return this.qteDiasValidadeReserva;
    }

    @Override
    public Collection<Reserva> getReservas() {

        return Collections.unmodifiableCollection(this.reservas);
    }

    @Override
    public Iterable<Usuario> getUsuarios() {

        return null;
    }

    @Override
    public Locacao locar(int itemAcervoId, int usuarioId)
        throws ObjetoNaoEncontradoException {

        return null;
    }

    @Override
    public Locacao locar(ItemAcervo item, Usuario usuario)
        throws ItemIndisponivelException {

        if (item.getQteDisponivel() > 0) {

            // item.setQteDisponivel(item.getQteDisponivel() - 1);

            item.locar(usuario);
            usuario.locar(item);

            Instant dias = Instant.now().plus(
                this.getQteDiasLocacao(),
                ChronoUnit.DAYS);

            Date devolverAte = Date.from(dias);

            Locacao locacao = new Locacao(
                devolverAte,

                item,
                usuario);

            this.locacoes.add(locacao);

            item.setQteDisponivel(item.getQteDisponivel() - 1);

            return locacao;

        } else {

            throw new ItemIndisponivelException(
                "Não há item disponível para locar.");
        }
    }

    @Override
    public Reserva reservar(int itemAcervoId, int usuarioId)
        throws ObjetoNaoEncontradoException, ItemIndisponivelException {

        IBuscador buscador = this.getBuscador();

        ItemAcervo itemAcervo = buscador.itemPorId(itemAcervoId);

        Usuario usuario = buscador.usuarioPorId(usuarioId);

        return this.reservar(itemAcervo, usuario);
    }

    @Override
    public Reserva reservar(ItemAcervo item, Usuario usuario)
        throws ItemIndisponivelException {

        if (item.getQteDisponivel() > 0) {

            // item.setQteDisponivel(item.getQteDisponivel() - 1);

            item.reservar(usuario);
            // usuario.reservar(item);

            Instant dias = Instant.now().plus(
                this.getQteDiasValidadeReserva(),
                ChronoUnit.DAYS);

            Date validaAte = Date.from(dias);

            Reserva reserva = new Reserva(
                validaAte,

                item,
                usuario);

            this.reservas.add(reserva);

            return reserva;

        } else {

            throw new ItemIndisponivelException(
                "Não há item disponível para locar.");
        }
    }

    public void setEndereco(String endereco) {

        this.endereco = endereco;
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

    public void setQteDiasLocacao(int qteDiasLocacao) {

        this.qteDiasLocacao = qteDiasLocacao;
    }

    public void setQteDiasValidadeReserva(int qteDiasValidadeReserva) {

        this.qteDiasValidadeReserva = qteDiasValidadeReserva;
    }

    public void setReservas(Collection<Reserva> reservas) {

        this.reservas = reservas;
    }

    @Override
    public void addAcervo(ItemAcervo item) {

        item.setBiblioteca(this);

        this.acervo.add(item);
    }

    public static void exportarBuscador(
        Biblioteca biblioteca,
        IBuscador buscador) {

        if (biblioteca == null)
            return;

        if (buscador == null)
            return;

        buscador.setBiblioteca(biblioteca);

        biblioteca.buscador = buscador;
    }

    public void removeAcervo(ItemAcervo item) throws ObjetoNaoEncontradoException {

        boolean removed = this.acervo.remove(item);

        if (!removed) {

            throw new ObjetoNaoEncontradoException(String.format(
                "Item de acervo #%d não encontrado",

                item.getId()));
        }

        item.setBiblioteca(null);
    }

    @Override
    public boolean match(String term) {

        return this.getNome().contains(term) ||
            this.getEndereco().contains(term);
    }

    @Override
    public String toString() {

        return Bibliotecas.toString(this);
    }
}
