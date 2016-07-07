/*
 * MIT License
 *
 * Copyright (c) 2016 José Nascimento & Juscelino Messias
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
package poo.library.app.web.selfhosting;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class App {

    /**
     * Thanks to {@link at
     * http://stackoverflow.com/questions/17953886/how-to-embed-jetty-and-jersey-into-my-java-application#answer-17954360}
     *
     * @param arguments
     * @throws Exception
     */
    public static void main(String[] arguments) throws Exception {

        /**
         * Thanks to {@link at
         * http://stackoverflow.com/questions/28733805/jersey-how-to-add-jackson-to-servlet-holder#answer-28734049}
         */

        final ResourceConfig application = new ResourceConfig()
            .packages("poo.library.app.web")
            .register(JacksonFeature.class);

        Server server = new Server(9090);

        // org.glassfish.jersey.servlet.ServletContainer
        ServletContextHandler context = new ServletContextHandler();

        context.setContextPath("/");

        ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(application));
        jerseyServlet.setInitOrder(1);
        // jerseyServlet.setInitParameter("jersey.config.server.provider.packages",
        // "poo.library.app.web");

        // TODO Configurar src/main/webapp de um jar
        ServletHolder staticServlet = context.addServlet(DefaultServlet.class, "/*");
        staticServlet.setInitParameter("resourceBase", "src/main/webapp");
        staticServlet.setInitParameter("pathInfoOnly", "true");

        //context.addEventListener(new InitListener());

        context.addServlet(jerseyServlet, "/api/*");

        server.setHandler(context);

        server.start();

        server.join();
    }
}
