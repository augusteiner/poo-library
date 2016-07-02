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
package poo.library.dao.activejdbc.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.javalite.activejdbc.Model;

/**
 * @author José Nascimento<joseaugustodearaujonascimento@gmail.com>
 */
public class ModelInvocationHandler implements InvocationHandler {

    private Model self;

    public ModelInvocationHandler(Model self) {

        this.self = self;
    }

    private String attributeName(String methodName) {

        String attr = methodName.substring(3);

        return attr.substring(0, 1).toLowerCase() + attr.substring(1);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String attr = this.attributeName(method.getName());

        if (method.getName().startsWith("get")) {

            return self.get(attr);

        } else if (method.getName().startsWith("set") &&
            args.length == 1) {

            self.set(attr, args[0]);
        }

        return null;
    }
}
