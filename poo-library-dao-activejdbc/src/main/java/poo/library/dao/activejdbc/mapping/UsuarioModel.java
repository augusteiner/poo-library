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
