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

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Responses {

    public static ResponseBuilder badRequest() {

        return status(Status.BAD_REQUEST);
    }

    public static ResponseBuilder created(URI createdUri) {

        return Response.created(createdUri);
    }

    public static ResponseBuilder noContent() {

        return status(Status.NO_CONTENT);
    }

    public static ResponseBuilder notFound() {

        return status(Status.NOT_FOUND);
    }

    public static ResponseBuilder ok() {

        return Response.ok();
    }

    public static ResponseBuilder ok(Object entity) {

        return Response.ok(entity);
    }

    public static ResponseBuilder serverError() {

        return Response.serverError();
    }

    public static ResponseBuilder status(Status status) {

        return Response.status(status);
    }
}
