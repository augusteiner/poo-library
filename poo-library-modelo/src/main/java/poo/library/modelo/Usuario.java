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
import poo.library.comum.IAluguel;
import poo.library.comum.IReserva;
import poo.library.comum.IUsuario;
import poo.library.util.Iterables;
import poo.library.util.Usuarios;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Usuario implements IUsuario {

    private int id;

    private String nome;
    private String cpf;
    private String endereco;

    private ETipoUsuario tipo;

    private Collection<Reserva> reservas;
    private Collection<Aluguel> alugueis;

    public Usuario() {

        this(ETipoUsuario.PADRAO);
    }

    protected Usuario(ETipoUsuario tipo) {

        this.tipo = tipo;
    }

    public Usuario(
        String nome,
        String cpf) {

        this(
            nome,
            cpf,
            ETipoUsuario.PADRAO);
    }

    protected Usuario(
        String nome,
        String cpf,
        ETipoUsuario tipo) {

        this(tipo);

        this.nome = nome;
        this.cpf = cpf;
    }

    @Override
    public Iterable<IAluguel> getAlugueis() {

        return Iterables.cast(this.alugueis);
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
    public String getNome() {

        return this.nome;
    }

    @Override
    public Iterable<IReserva> getReservas() {

        return Iterables.cast(this.reservas);
    }

    @Override
    public ETipoUsuario getTipo() {

        return this.tipo;
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

    @Override
    public void setNome(String nome) {

        this.nome = nome;
    }

    @Override
    public String toString() {

        return Usuarios.toString(this);
    }
}
