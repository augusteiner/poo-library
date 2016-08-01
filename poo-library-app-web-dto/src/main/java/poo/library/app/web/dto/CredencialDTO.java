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
package poo.library.app.web.dto;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class CredencialDTO {

    private int usuarioId;

    private String login;
    private String senha;

    public CredencialDTO() { }

    public String getLogin() {

        return this.login;
    }

    public String getSenha() {

        return this.senha;
    }

    public int getUsuarioId() {

        return this.usuarioId;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public void setSenha(String senha) {

        this.senha = senha;
    }

    public void setUsuarioId(int usuarioId) {

        this.usuarioId = usuarioId;
    }
}
