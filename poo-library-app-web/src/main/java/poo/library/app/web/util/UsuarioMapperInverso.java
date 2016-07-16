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
package poo.library.app.web.util;

import poo.library.app.web.dto.UsuarioDTO;
import poo.library.comum.ETipoUsuario;
import poo.library.modelo.Administrador;
import poo.library.modelo.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class UsuarioMapperInverso<I> extends Mapper<I, Usuario> {

    public UsuarioMapperInverso(Class<I> clsIn) {

        super(clsIn, Usuario.class);
    }

    @Override
    public Usuario converter(Object input) {

        if (input instanceof UsuarioDTO) {

            Usuario usuario;

            ETipoUsuario tipo = ((UsuarioDTO) input).getTipo();

            switch (tipo) {
                case ADMIN:
                    usuario = new Administrador();
                    break;
                case COMUM:
                    usuario = new Usuario();
                    break;
                default:
                    throw new IllegalArgumentException(String.format(
                        "DTO com tipo '%s' de usuário inválido",
                        tipo));
            }

            this.map(input, usuario);

            return usuario;
        }

        return super.converter(input);
    }
}
