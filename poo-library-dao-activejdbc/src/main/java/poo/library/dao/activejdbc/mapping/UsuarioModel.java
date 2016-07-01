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
import poo.library.comum.IAluguel;
import poo.library.comum.IEndereco;
import poo.library.comum.IItemAcervo;
import poo.library.comum.IReserva;
import poo.library.comum.IUsuario;
import poo.library.dao.activejdbc.mapping.ItemAcervoModel.IItemAcervoProxy;
import poo.library.dao.activejdbc.util.IModel;
import poo.library.util.Enderecos;
import poo.library.util.Usuarios;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Table("usuario")
public class UsuarioModel extends Model implements IModel<UsuarioModel.IUsuarioProxy> {

    public interface IUsuarioProxy extends IUsuario, IModel<IUsuarioProxy> { }

    class Administrador extends Usuario {

        //
    }

    class Endereco implements IEndereco {

        @Override
        public String getLogradouro() {

            return self.getString("enderecoLogradouro");
        }

        @Override
        public String getNumero() {

            return self.getString("enderecoNumero");
        }

        @Override
        public void setLogradouro(String logradouro) {

            self.set("enderecoLogradouro", logradouro);
        }

        @Override
        public void setNumero(String numero) {

            self.set("enderecoNumero", numero);
        }

        @Override
        public String toString() {

            return Enderecos.toString(this);
        }
    }

    class Usuario implements IUsuario {

        private Endereco endereco;

        public Usuario() {

            this.endereco = new Endereco();
        }

        @Override
        public String getCpf() {

            return self.getString("cpf");
        }

        @Override
        public Iterable<IAluguel> getAlugueis() {

            return null;
        }

        @Override
        public IEndereco getEndereco() {

            return this.endereco;
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
        public Iterable<IReserva> getReservas() {

            return null;
        }

        @Override
        public ETipoUsuario getTipo() {

            return ETipoUsuario.valueOf(self.getString("tipo"));
        }

        @Override
        public void setCpf(String cpf) {

            self.set("cpf", cpf);
        }

        public void setEnderecoLogradouro(String logradouro) {

            this.endereco.setLogradouro(logradouro);
        }

        public void setEnderecoNumero(String numero) {

            this.endereco.setNumero(numero);
        }

        public void setId(int id) {

            self.set("id", id);
        }

        @Override
        public void setNome(String nome) {

            self.setString("nome", nome);
        }

        public void setTipo(ETipoUsuario tipo) {

            self.set("tipo", tipo.name());
        }

        @Override
        public String toString() {

            return Usuarios.toString(this);
        }

        @Override
        public void setEndereco(IEndereco endereco) {

            // TODO Auto-generated method stub

        }
    }

    private UsuarioModel self = UsuarioModel.this;

    @Override
    public IUsuarioProxy converter() {

        //System.out.println(this);

        ETipoUsuario tipo = ETipoUsuario.PADRAO;

        String tipoValue = this.getString("tipo");

        if (tipoValue != null) {

            tipo = ETipoUsuario.valueOf(tipoValue);
        }

        return null;

        //switch (tipo) {
        //
        //    case COMUM:
        //        return new Usuario();
        //
        //    case ADMIN:
        //        return new Administrador();
        //
        //    default:
        //        throw new IllegalArgumentException();
        //}
    }
}
