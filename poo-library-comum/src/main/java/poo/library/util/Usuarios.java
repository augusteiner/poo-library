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
package poo.library.util;

import static org.apache.commons.lang3.StringUtils.*;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import poo.library.comum.IUsuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Usuarios {

    private static final MaskFormatter cpfFormatter = new CPFFormatter();

    public static String formatarCPF(String cpf) {

        try {

            return cpfFormatter.valueToString(cpf);

        } catch (ParseException e) {

            return cpf;
        }
    }

    public static String toString(IUsuario usuario) {

        return String.format(
            "%s: %s - %s (%s) : (%s)",
            usuario.getTipo(),

            usuario.getId(),
            usuario.getNome(),

            formatarCPF(usuario.getCpf()),

            usuario.getEndereco());
    }

    public static boolean match(IUsuario usuario, String termo) {

        return containsIgnoreCase(usuario.getNome(), termo) ||
            containsIgnoreCase(usuario.getCpf(), termo) ||
            containsIgnoreCase(usuario.getEndereco(), termo) ||
            containsIgnoreCase(usuario.getLogin(), termo) ||
            containsIgnoreCase(usuario.getTipo().toString(), termo);
    }
}
