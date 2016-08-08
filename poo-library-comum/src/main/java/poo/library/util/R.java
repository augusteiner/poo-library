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

import static java.util.Calendar.*;
import static java.util.TimeZone.*;
import static java.text.NumberFormat.*;
import static java.text.DateFormat.*;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class R {

    public static final String CPF_MASK = "###.###.###-##";
    public static final Locale LOCALE_PT_BR = new Locale("pt", "BR");
    public static final Locale LOCALE_PADRAO = LOCALE_PT_BR;

    public static final DateFormat FORMATTER_DATA_HORA_PADRAO = getDateTimeInstance(
            DateFormat.SHORT,
            DateFormat.SHORT,
            LOCALE_PADRAO);

    public static final DateFormat FORMATTER_DATA_PADRAO = getDateInstance(
        DateFormat.SHORT,
        LOCALE_PADRAO);

    public static final NumberFormat FORMATTER_MOEDA_PADRAO = getCurrencyInstance(
        LOCALE_PADRAO);

    public static final TimeZone TIMEZONE_PADRAO = getTimeZone("America/Fortaleza");
    public static final Calendar CALENDAR_PADRAO = getInstance(
        TIMEZONE_PADRAO,
        LOCALE_PADRAO);
}
