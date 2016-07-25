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

import poo.library.comum.ETipoUsuario;
import poo.library.comum.IItemAcervo;
import poo.library.comum.IUsuario;
import poo.library.util.FalhaOperacaoException;
import poo.library.util.ObjetoNaoEncontradoException;
import poo.library.util.Usuarios;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Usuario implements IUsuario {

    private int id;

    private String nome;
    private String cpf;
    private String endereco;

    private ETipoUsuario tipo = ETipoUsuario.COMUM;

    private Collection<Reserva> reservas;
    private Collection<Locacao> locacoes;

    public Usuario() {

        this(ETipoUsuario.PADRAO);
    }

    protected Usuario(ETipoUsuario tipo) {

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

        return this.locacoes;
    }

    @Override
    public String getNome() {

        return this.nome;
    }

    @Override
    public Collection<Reserva> getReservas() {

        return this.reservas;
    }

    @Override
    public ETipoUsuario getTipo() {

        return this.tipo;
    }

    @Override
    public void locar(IItemAcervo item) {

        //
    }

    @Override
    public boolean match(String termo) {

        return this.getNome().contains(termo) ||
            this.getCpf().contains(termo) ||
            this.getEndereco().contains(termo);
    }

    @Override
    public void quitar() {

        //
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

    @Override
    public void setNome(String nome) {

        this.nome = nome;
    }

    public void setReservas(Collection<Reserva> reservas) {

        this.reservas = reservas;
    }

    @Override
    public String toString() {

        return Usuarios.toString(this);
    }

    @Override
    public void cancelar(int reservaId) throws ObjetoNaoEncontradoException, FalhaOperacaoException {

        Reserva reserva = this.reservaPorId(reservaId);

        reserva.getBiblioteca().cancelar(reserva);
    }

    private Reserva reservaPorId(int reservaId) throws ObjetoNaoEncontradoException {

        for (Reserva r : this.reservas) {

            if (r.getId() == reservaId) {

                return r;
            }
        }

        throw new ObjetoNaoEncontradoException(String.format(
            "Reserva de id #%d não encontrada",
            reservaId));
    }
}
