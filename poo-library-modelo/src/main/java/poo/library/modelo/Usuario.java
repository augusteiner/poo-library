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

import poo.library.comum.ETipoUsuario;
import poo.library.comum.IEndereco;
import poo.library.comum.IUsuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Usuario implements IUsuario {

    private int id;

    private String nome;
    private String cpf;

    private IEndereco endereco;

    private ETipoUsuario tipo;

    public Usuario() {

        this.tipo = ETipoUsuario.COMUM;
    }

    public Usuario(
        int id,
        String nome,
        String cpf) {

        this(nome, cpf);

        this.id = id;
    }

    public Usuario(String nome, String cpf) {

        this.nome = nome;
        this.cpf = cpf;
    }

    @Override
    public String getCpf() {

        return this.cpf;
    }

    @Override
    public IEndereco getEndereco() {

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
    public ETipoUsuario getTipo() {

        return this.tipo;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf;
    }

    public void setEndereco(Endereco endereco) {

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
    public void setTipo(ETipoUsuario tipo) {

        this.tipo = tipo;
    }

    @Override
    public String toString() {

        return String.format(
            "#%s - %s (%s)",
            this.getId(),
            this.getNome(),
            this.getCpf());
    }
}
