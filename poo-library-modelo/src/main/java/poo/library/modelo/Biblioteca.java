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
package poo.library.modelo;

import poo.library.comum.IAluguel;
import poo.library.comum.IBiblioteca;
import poo.library.comum.IItemAcervo;
import poo.library.comum.IReserva;
import poo.library.comum.IUsuario;
import poo.library.util.ICollectionMap;
import poo.library.util.Iterables;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Biblioteca implements IBiblioteca {

    private int id;

    private String nome;
    private double multaDiaria;

    private ICollectionMap<String, ItemAcervo> acervo;

    private ICollectionMap<String, Usuario> usuarios;
    private ICollectionMap<Integer, Usuario> usuariosPorId;

    private ICollectionMap<Usuario, Aluguel> alugueis;
    private ICollectionMap<Usuario, Reserva> reservas;

    public Biblioteca() { }

    public Biblioteca(String nome, double multaDiaria) {

        this.nome = nome;
        this.multaDiaria = multaDiaria;
    }

    @Override
    public void alugar(IItemAcervo item, IUsuario usuario) {

        //
    }

    @Override
    public Iterable<IAluguel> alugueis(int usuarioId) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<IAluguel> alugueis(IUsuario usuario) {

        return null;
    }

    @Override
    public IItemAcervo buscar(int itemId) {

        return null;
    }

    @Override
    public void devolver(IItemAcervo item) {

        //
    }

    @Override
    public Iterable<IItemAcervo> getAcervo() {

        return Iterables.cast(this.acervo);
    }

    @Override
    public Iterable<IAluguel> getAlugueis() {

        return Iterables.cast(this.alugueis);
    }

    @Override
    public int getId() {

        return this.id;
    }

    @Override
    public double getMultaDiaria() {

        return this.multaDiaria;
    }

    @Override
    public String getNome() {

        return this.nome;
    }

    @Override
    public Iterable<IReserva> getReservas() {

        return Iterables.cast(this.reservas);
    }

    @Override
    public Iterable<IUsuario> getUsuarios() {

        return Iterables.cast(this.usuarios);
    }

    @Override
    public void reservar(IItemAcervo item, IUsuario usuario) {

        //
    }

    @Override
    public Iterable<IReserva> reservas(int usuarioId) {

        Usuario usuario = this.usuariosPorId.first(usuarioId);

        return Iterables.cast(this.reservas.values(usuario));
    }

    @Override
    public Iterable<IReserva> reservas(IUsuario usuario) {

        return this.reservas(usuario.getId());
    }

    public void setAcervo(ICollectionMap<String, ItemAcervo> acervo) {

        this.acervo = acervo;
    }

    public void setAlugueis(ICollectionMap<Usuario, Aluguel> alugueis) {

        this.alugueis = alugueis;
    }

    public void setId(int id) {

        this.id = id;
    }

    @Override
    public void setMultaDiaria(double multaDiaria) {

        this.multaDiaria = multaDiaria;
    }

    @Override
    public void setNome(String nome) {

        this.nome = nome;
    }

    public void setReservas(ICollectionMap<Usuario, Reserva> reservas) {

        this.reservas = reservas;
    }

    public void setUsuarios(ICollectionMap<String, Usuario> usuarios) {

        this.usuarios = usuarios;
    }

    @Override
    public Iterable<IUsuario> usuarios(String parteNome) {

        return Iterables.cast(this.usuarios.values(parteNome));
    }
}
