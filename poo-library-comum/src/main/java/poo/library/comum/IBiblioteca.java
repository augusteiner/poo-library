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
package poo.library.comum;

import poo.library.util.IIdentificavel;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public interface IBiblioteca extends IIdentificavel {

    Iterable<IItemAcervo> getAcervo();

    Iterable<IAluguel> getAlugueis();

    double getMultaDiaria();

    String getNome();

    Iterable<IReserva> getReservas();

    Iterable<IUsuario> getUsuarios();

    void setMultaDiaria(double multaDiaria);

    void setNome(String nome);

    void alugar(IItemAcervo item, IUsuario usuario);

    Iterable<IAluguel> alugueis(IUsuario usuario);
    Iterable<IAluguel> alugueis(int usuarioId);

    IItemAcervo buscar(int itemId);

    void devolver(IItemAcervo item);

    void reservar(IItemAcervo item, IUsuario usuario);

    Iterable<IReserva> reservas(IUsuario usuario);
    Iterable<IReserva> reservas(int usuarioId);

    Iterable<IUsuario> usuarios(String parteNome);

    // double calcularValorMultas(Date dia);
    // double valorDiarioMulta(IItemAcervo item);
}
