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
package poo.library.app.web.dto;

import poo.library.comum.ETipoUsuario;
import poo.library.comum.IIdentificavel;
import poo.library.util.Usuarios;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class UsuarioDTO implements IIdentificavel {

    private int id;

    private String nome;
    private String login;
    private String senha;

    private double saldoDevedor;

    private String cpf;
    private String endereco;

    private ETipoUsuario tipo = ETipoUsuario.PADRAO;

    public String getCpf() {

        return this.cpf;
    }

    public String getCpfFormatado() {

        return Usuarios.formatarCPF(this.getCpf());
    }

    public String getEndereco() {

        return this.endereco;
    }

    @Override
    public int getId() {

        return this.id;
    }

    public String getLogin() {

        return this.login;
    }

    public String getNome() {

        return this.nome;
    }

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public String getSenha() {

        return this.senha;
    }

    public ETipoUsuario getTipo() {

        return this.tipo;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf;
    }

    // TODO Avaliar necessidade
    public void setCpfFormatado(String cpfFormatado) { }

    public void setEndereco(String endereco) {

        this.endereco = endereco;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public void setSaldoDevedor(double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public void setSenha(String senha) {

        this.senha = senha;
    }

    public void setTipo(ETipoUsuario tipo) {

        this.tipo = tipo == null ? ETipoUsuario.PADRAO : tipo;
    }
}
