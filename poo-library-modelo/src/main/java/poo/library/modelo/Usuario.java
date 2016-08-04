/*
 * MIT License
 *
 * Copyright (c) 2016 José Nascimento & Juscelino Messias
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
import java.util.LinkedList;

import poo.library.comum.ETipoUsuario;
import poo.library.comum.IItemAcervo;
import poo.library.comum.IUsuario;
import poo.library.util.ObjetoNaoEncontradoException;
import poo.library.util.Usuarios;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Usuario implements IUsuario {

    private int id;

    private String nome;

    private String login;
    private String senha;

    private double saldoDevedor;

    private String cpf;
    private String endereco;

    private ETipoUsuario tipo;

    private Collection<Reserva> reservas;
    private Collection<Locacao> locacoes;

    public Usuario() {

        this.tipo = ETipoUsuario.PADRAO;

        this.reservas = new LinkedList<Reserva>();
        this.locacoes = new LinkedList<Locacao>();
    }

    protected Usuario(ETipoUsuario tipo) {

        this();

        this.tipo = tipo;
    }

    public Usuario(
        String nome,
        String cpf) {

        this(nome, cpf, null);
    }

    public Usuario(
        String nome,
        String cpf,
        String endereco) {

        this(nome, cpf, endereco, ETipoUsuario.COMUM);
    }

    protected Usuario(
        String nome,
        String cpf,
        String endereco,
        ETipoUsuario tipo) {

        this(tipo);

        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    @Override
    public void cancelar(int reservaId) throws ObjetoNaoEncontradoException {

        Reserva reserva = this.reservaPorId(reservaId);

        reserva.getBiblioteca().cancelar(reserva);
    }

    @Override
    public String getCpf() {

        return this.cpf;
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
    public String getLogin() {

        return this.login;
    }

    @Override
    public String getNome() {

        return this.nome;
    }

    @Override
    public Collection<Reserva> getReservas() {

        return Collections.unmodifiableCollection(this.reservas);
    }

    @Override
    public double getSaldoDevedor() {

        return this.saldoDevedor;
    }

    @Override
    public String getSenha() {

        return this.senha;
    }

    @Override
    public ETipoUsuario getTipo() {

        return this.tipo;
    }

    public Locacao locacaoPorId(int locacaoId) throws ObjetoNaoEncontradoException {

        for (Locacao locacao : this.locacoes) {

            if (locacao.getId() == locacaoId) {

                return locacao;
            }
        }

        throw new ObjetoNaoEncontradoException(String.format(
            "Locação #%d não encontrada",

            locacaoId));
    }

    public void locar(ItemAcervo item) {

        this.setSaldoDevedor(
            this.getSaldoDevedor() + item.getPrecoLocacao());
    }

    @Override
    public boolean match(String termo) {

        return Usuarios.match(this, termo);
    }

    @Override
    public void quitar() {

        //
    }

    public Reserva reservaPorId(int reservaId) throws ObjetoNaoEncontradoException {

        for (Reserva r : this.getReservas()) {

            if (r.getId() == reservaId) {

                return r;
            }
        }

        throw new ObjetoNaoEncontradoException(String.format(
            "Reserva de id #%d não encontrada",
            reservaId));
    }

    @Override
    public void setCpf(String cpf) {

        this.cpf = cpf;
    }

    @Override
    public void setEndereco(String endereco) {

        this.endereco = endereco;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setLocacoes(Collection<Locacao> locacoes) {

        this.locacoes = locacoes;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    @Override
    public void setNome(String nome) {

        this.nome = nome;
    }

    public void setReservas(Collection<Reserva> reservas) {

        this.reservas = reservas;
    }

    public void setSaldoDevedor(double saldoDevedor) {

        this.saldoDevedor = saldoDevedor;
    }

    public void setSenha(String senha) {

        this.senha = senha;
    }

    @Override
    public String toString() {

        return Usuarios.toString(this);
    }
}
