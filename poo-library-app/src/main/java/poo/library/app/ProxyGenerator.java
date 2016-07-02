
package poo.library.app;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author augusteiner
 */
public class ProxyGenerator {

    private static String attributeName(String method) {

        String attr = method.substring(3);

        return attr.substring(0, 1).toLowerCase() + attr.substring(1);
    }

    public static Object makeNew(
            Class<?> cls) {

        return makeNew(cls, new Hashtable<String, Object>());
    }

    public static Object makeNew(
            Class<?> cls,
            final Map<String, Object> values) {

        return Proxy.newProxyInstance(
            cls.getClassLoader(),
            new Class<?>[] { cls },
            new InvocationHandler() {

                @Override
                public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {

                    if (method.getName().startsWith("get")) {

                        return values.get(attributeName(method.getName()));

                    } else if (method.getName().startsWith("set") && args.length == 1) {

                        values.put(attributeName(method.getName()), args[0]);
                    }

                    return null;
                }
            });
    }
}
