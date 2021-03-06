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
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import poo.library.comum.EStatusRequisicao;
import poo.library.modelo.comum.IBiblioteca;
import poo.library.modelo.comum.IBuscador;
import poo.library.util.Bibliotecas;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.ItemIndisponivelException;
import poo.library.util.Locacoes;
import poo.library.util.ObjetoNaoEncontradoException;
import poo.library.util.Requisicoes;

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

    private Collection<ItemAcervo> acervo = new LinkedList<ItemAcervo>();

    private Collection<Reserva> reservas = new LinkedList<Reserva>();
    private Collection<Locacao> locacoes = new LinkedList<Locacao>();

    public Biblioteca() { }

    public Biblioteca(String nome, double multaDiaria) {

        this();

        this.nome = nome;
        this.multaDiaria = multaDiaria;
    }

    @Override
    public void addAcervo(ItemAcervo item) {

        item.setBiblioteca(this);

        this.acervo.add(item);
    }

    @Override
    public void atualizarLocacoes(Date referencia) {

        this.atualizarLocacoes(referencia, this.getLocacoes());
    }

    public void atualizarLocacoes(Date referencia, Iterable<Locacao> locacoes) {

        for (Locacao locacao : locacoes) {

            locacao.atualizarStatus(referencia);
        }
    }

    @Override
    public void atualizarReservas(Date referencia) {

        this.atualizarReservas(referencia, this.getReservas());
    }

    public void atualizarReservas(Date referencia, Iterable<Reserva> reservas) {

        for (Reserva reserva : reservas) {

            reserva.atualizarStatus(referencia);
        }
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
    public Locacao devolver(int locacaoId, Date referencia)
        throws ObjetoNaoEncontradoException {

        Locacao locacao = this.getBuscador().locacaoPorId(locacaoId);

        this.devolver(locacao, referencia);

        return locacao;
    }

    @Override
    public Locacao devolver(int usuarioId, int itemAcervoId, Date referencia)
        throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        Locacao locacao = this.getBuscador().ultimaLocacao(usuarioId, itemAcervoId);

        this.devolver(locacao, referencia);

        return locacao;
    }

    @Override
    public void devolver(Locacao locacao, Date referencia) {

        Locacoes.atualizar(referencia, locacao);

        ItemAcervo itemAcervo = locacao.getItemAcervo();

//        if (locacao.getStatus() == EStatusRequisicao.VENCIDA) {

            Usuario usuario = locacao.getUsuario();

            double totalAPagar = Locacoes.totalAPagar(
                locacao,
                referencia);

            System.out.println(String.format(
                "TOTAL A PAGAR: %.2f",

                totalAPagar));

            usuario.setSaldoDevedor(usuario.getSaldoDevedor() + totalAPagar);
//        }

        itemAcervo.devolver();
        // itemAcervo.setQteDisponivel(itemAcervo.getQteDisponivel() + 1);

        locacao.setStatus(EStatusRequisicao.ENCERRADA);
        locacao.setDevolvidoEm(referencia);
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
    @Deprecated
    public Iterable<Usuario> getUsuarios() {

        return this.getBuscador().usuarios();
    }

    @Override
    public Locacao locar(int itemAcervoId, int usuarioId, Date referencia)
        throws ObjetoNaoEncontradoException, ItemIndisponivelException {

        ItemAcervo itemAcervo = this.getBuscador().itemPorId(itemAcervoId);
        Usuario usuario = this.getBuscador().usuarioPorId(usuarioId);

        return this.locar(itemAcervo, usuario, referencia);
    }

    @Override
    public Locacao locar(ItemAcervo item, Usuario usuario, Date referencia)
        throws ItemIndisponivelException {

        if (item.getQteDisponivel() > 0) {

            // item.setQteDisponivel(item.getQteDisponivel() - 1);

            Date devolverAte = Requisicoes.adicionarDias(
                referencia,
                this.getQteDiasLocacao());

            Locacao locacao = new Locacao(
                devolverAte,

                item,
                usuario);

            this.locacoes.add(locacao);

            item.locar(usuario);
            usuario.locar(item);

            return locacao;

        } else {

            throw new ItemIndisponivelException(
                "Não há item disponível para locar.");
        }
    }

    @Override
    public boolean match(String term) {

        return Bibliotecas.match(this, term);
    }

    public void removeAcervo(ItemAcervo item) {

        boolean removed = this.acervo.remove(item);

        if (!removed) {

            System.out.println(String.format(
                "ITENS DO ACERVO (lista: %s; size: %d):",

                this.acervo,
                this.acervo.size()));

            for (ItemAcervo i : this.acervo) {

                System.out.println(i.toString());
            }

            //throw new ObjetoNaoEncontradoException(String.format(
            //    "Item de acervo #%d não encontrado",
            //
            //    item.getId()));
        }

        item.setBiblioteca(null);
    }

    @Override
    public Reserva reservar(int itemAcervoId, int usuarioId, Date referencia)
        throws ObjetoNaoEncontradoException, ItemIndisponivelException {

        IBuscador buscador = this.getBuscador();

        ItemAcervo itemAcervo = buscador.itemPorId(itemAcervoId);

        Usuario usuario = buscador.usuarioPorId(usuarioId);

        return this.reservar(itemAcervo, usuario, referencia);
    }

    @Override
    public Reserva reservar(ItemAcervo item, Usuario usuario, Date referencia)
        throws ItemIndisponivelException {

        if (item.getQteDisponivel() > 0) {

            // item.setQteDisponivel(item.getQteDisponivel() - 1);

            item.reservar(usuario);
            // usuario.reservar(item);

            Date validaAte = Requisicoes.adicionarDias(
                referencia,
                this.getQteDiasValidadeReserva());

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

    @Override
    public String toString() {

        return Bibliotecas.toString(this);
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
}
