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
package poo.library.dao.jpa;

import java.net.URI;
import java.util.Hashtable;
import java.util.Map;

import poo.library.dao.comum.IDAOFactory;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class PostgreSqlConnectionDAOFactory extends JpaDAOFactory
    implements IDAOFactory {

    public static final String PERSISTENCE_UNIT_NAME = "poo.library.dao.jpa.postgresql";

    public PostgreSqlConnectionDAOFactory() {

        super(PERSISTENCE_UNIT_NAME, getProperties());
    }

    private static Map<String, String> getProperties() {

        Map<String, String> properties = new Hashtable<String, String>();

        URI uri = URI.create(System.getProperty("dao.jdbcUrl"));

        String[] userInfo = uri.getUserInfo().split(":");

        String jdbcUrl = String.format(
            "jdbc:%s://%s:%s%s",

            uri.getScheme(),
            uri.getHost(),
            uri.getPort(),
            uri.getPath());

        properties.put("javax.persistence.jdbc.url", jdbcUrl);

        properties.put("javax.persistence.jdbc.user", userInfo[0]);
        properties.put("javax.persistence.jdbc.password", userInfo[1]);

        return properties;
    }
}
