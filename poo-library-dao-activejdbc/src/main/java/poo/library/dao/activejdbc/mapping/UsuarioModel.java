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
package poo.library.dao.activejdbc.mapping;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import poo.library.comum.ETipoUsuario;
import poo.library.comum.IConvertible;
import poo.library.comum.IEmprestimo;
import poo.library.comum.IEndereco;
import poo.library.comum.IReserva;
import poo.library.comum.IUsuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Table("usuario")
public class UsuarioModel extends Model implements IConvertible<IUsuario> {


    class Administrador extends Usuario {

        //
    }

    class Usuario implements IUsuario {

        private UsuarioModel self = UsuarioModel.this;

        public Usuario() { }

        @Override
        public String getCpf() {

            return self.getString("cpf");
        }

        @Override
        public IEndereco getEndereco() {

            return null;
        }

        @Override
        public int getId() {

            return self.getInteger("id");
        }

        @Override
        public String getNome() {

            return self.getString("nome");
        }

        @Override
        public ETipoUsuario getTipo() {

            return ETipoUsuario.valueOf(self.getString("tipo"));
        }

        public void setCpf(String cpf) {

            self.set("cpf", cpf);
        }

        public void setEndereco(IEndereco endereco) {

            //
        }

        public void setId(int id) {

            self.set("id", id);
        }

        @Override
        public void setNome(String nome) {

            self.setString("nome", nome);
        }

        @Override
        public void setTipo(ETipoUsuario tipo) {

            self.set("tipo", tipo.name());
        }

        @Override
        public String toString() {

            return String.format(
                "%s - %s (%s)",
                this.getId(),
                this.getNome(),
                this.getCpf());
        }

        @Override
        public Iterable<IEmprestimo> getEmprestimos() {

            return null;
        }

        @Override
        public Iterable<IReserva> getReservas() {

            return null;
        }
    }

    @Override
    public IUsuario convert() {

        //System.out.println(this);

        ETipoUsuario tipo = ETipoUsuario.PADRAO;

        String tipoValue = this.getString("tipo");

        if (tipoValue != null) {

            tipo = ETipoUsuario.valueOf(tipoValue);
        }

        switch (tipo) {

            case COMUM:
                return new Usuario();

            case ADMIN:
                return new Administrador();

            default:
                throw new IllegalArgumentException();
        }
    }
}
