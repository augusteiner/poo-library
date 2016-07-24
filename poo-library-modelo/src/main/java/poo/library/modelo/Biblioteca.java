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
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import poo.library.modelo.comum.IAcervo;
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

    private IAcervo armazem;

    private Collection<ItemAcervo> acervo;

    private Map<RequisicaoId, Reserva> reservas;
    private Map<RequisicaoId, Locacao> locacoes;

    public Biblioteca() {

        this.acervo = new LinkedList<ItemAcervo>();

        this.reservas = new Hashtable<RequisicaoId, Reserva>();
        this.locacoes = new Hashtable<RequisicaoId, Locacao>();
    }

    public Biblioteca(String nome, double multaDiaria) {

        this();

        this.nome = nome;
        this.multaDiaria = multaDiaria;
    }

    @Override
    public Reserva cancelar(int reservaId)
        throws ObjetoNaoEncontradoException {

        return null;
    }

    @Override
    public void cancelar(Reserva reserva) throws FalhaOperacaoException {

    }

    @Override
    public Locacao devolver(int locacaoId)
        throws ObjetoNaoEncontradoException {

        return null;
    }

    @Override
    public void devolver(Locacao locacao) throws FalhaOperacaoException {

    }

    @Override
    public Collection<ItemAcervo> getAcervo() {

        return this.acervo;
    }

    public IBuscador getBuscador() {

        return this.armazem;
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
    public Map<RequisicaoId, ? extends Locacao> getLocacoes() {

        return this.locacoes;
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
    public Map<RequisicaoId, ? extends Reserva> getReservas() {

        return this.reservas;
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

            this.locacoes.put(
                locacao.getId(),
                locacao);

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

        ItemAcervo itemAcervo = this.getBuscador().itemPorId(itemAcervoId);

        Usuario usuario = this.getBuscador().usuarioPorId(usuarioId);

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

            this.reservas.put(
                reserva.getId(),
                reserva);

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

    public void setReservas(Map<RequisicaoId, Reserva> reservas) {

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

        buscador.setItensAcervo(biblioteca.acervo);

        buscador.setReservas(biblioteca.reservas);
        buscador.setLocacoes(biblioteca.locacoes);
    }

    public void removeAcervo(ItemAcervo item) {

        item.setBiblioteca(null);

        this.acervo.remove(item);
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
