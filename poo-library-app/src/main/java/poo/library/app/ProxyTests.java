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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Hashtable;
import java.util.Map;

import poo.library.comum.IUsuario;
import poo.library.modelo.Endereco;
import poo.library.util.Usuarios;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class ProxyTests {

    public static String attributeName(String method) {

        String attr = method.substring(3);

        return attr.substring(0, 1).toLowerCase() + attr.substring(1);
    }

    public static void main(String[] args) {

        Class<IUsuario> cls = IUsuario.class;
        final Map<String, Object> _values = new Hashtable<String, Object>();

        IUsuario usuario = (IUsuario) Proxy.newProxyInstance(
            cls.getClassLoader(),
            new Class<?>[] { cls },
            new InvocationHandler() {

                final Map<String, Object> values = _values;

                @Override
                public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {

                    if (method.getName().startsWith("get")) {

                        return values.get(attributeName(method.getName()));

                    } else if (method.getName().startsWith("set")
                        && args.length == 1) {

                        values.put(attributeName(method.getName()), args[0]);
                    }

                    return null;
                }
            });

        _values.put("id", 1);

        usuario.setCpf("07134269426");
        usuario.setNome("José Augusto");
        usuario.setEndereco(new Endereco("R. José", "146"));

        System.out.println(usuario.getEndereco().toString());
        // p.getEndereco().setLogradouro("teste");

        for (String key : _values.keySet()) {

            System.out.println(String.format(
                "Key: %s, Value: %s",
                key, _values.get(key)));
        }

        System.out.println(Usuarios.toString(usuario));
    }
}
