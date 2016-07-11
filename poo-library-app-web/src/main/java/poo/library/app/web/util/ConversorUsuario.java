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

import poo.library.comum.IUsuario;
import poo.library.modelo.Administrador;
import poo.library.modelo.Usuario;
import poo.library.util.IConversor;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class ConversorUsuario<I extends IUsuario> implements IConversor<I, Usuario> {

    private IConversor<I, Usuario> conversor;

    public ConversorUsuario(IConversor<I, Usuario> conversor) {

        this.conversor = conversor;
    }

    @Override
    public Usuario convert(I input) {

        if (input == null) {

            throw new IllegalArgumentException("input deve ser válido");
        }

        Usuario output;

        switch (input.getTipo()) {

            case ADMIN:

                output = new Administrador();
                break;

            case COMUM:

                output = new Usuario();
                break;

            default:
                throw new IllegalArgumentException("input deve ser do tipo IUsuario");
        }

        this.convert(input, output);

        return output;
    }

    @Override
    public void convert(I input, Usuario output) {

        this.conversor.convert(input, output);

        output.setLocacoes(null);
        output.setReservas(null);

        System.out.println(String.format(
            "%s: %s -> %s: %s",
            Integer.toHexString(System.identityHashCode(input)),
            input,
            Integer.toHexString(System.identityHashCode(output)),
            output));
    }

    public static IConversor<Usuario, Usuario> makeNew() {

        return new ConversorUsuario<Usuario>(DAOFactory.newConversor(Usuario.class));
    }
}
