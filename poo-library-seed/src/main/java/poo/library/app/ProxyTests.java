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
package poo.library.app;

import java.util.Hashtable;
import java.util.Map;

import poo.library.comum.IAdministrador;
import poo.library.comum.IUsuario;
import poo.library.util.Usuarios;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class ProxyTests {

    interface ITestProxy extends IUsuario, IAdministrador {

        void setEndereco(String endereco);
    }

    public static void main(String[] args) {

        Map<String, Object> values = new Hashtable<String, Object>();

        ITestProxy usuario = (ITestProxy) ProxyGenerator.makeNew(
            ITestProxy.class,
            values);

        values.put("id", 1);

        usuario.setCpf("07134269426");
        usuario.setNome("José Augusto");
        usuario.setEndereco("R. João Blablabla, 27");

        // p.getEndereco().setLogradouro("teste");

        for (String key : values.keySet()) {

            System.out.println(String.format(
                "Key: %s, Value: %s",
                key, values.get(key)));
        }

        System.out.println(Usuarios.toString(usuario));
    }
}
