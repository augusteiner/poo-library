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
package poo.library.dao.util;

import java.util.Collection;

import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.dao.comum.IDAOFactory;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.Locacao;
import poo.library.modelo.Reserva;
import poo.library.modelo.Usuario;
import poo.library.modelo.comum.IBuscador;
import poo.library.util.BuscadorMemoria;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class BuscadorDb extends BuscadorMemoria implements IBuscador {

    private IDAO<ItemAcervo> itemAcervoDAO;
    private IDAO<Usuario> usuarioDAO;

    private IDAO<Locacao> locacaoDAO;
    private IDAO<Reserva> reservaDAO;

    public BuscadorDb() {

        this(DAOFactory.getImpl());
    }

    private BuscadorDb(IDAOFactory factory) {

        this.usuarioDAO = factory.createNew(Usuario.class);
        this.itemAcervoDAO = factory.createNew(ItemAcervo.class);

        this.locacaoDAO = factory.createNew(Locacao.class);
        this.reservaDAO = factory.createNew(Reserva.class);

    }

    @Override
    public void close() {

        super.close();

        this.usuarioDAO = null;
        this.itemAcervoDAO = null;
        this.locacaoDAO = null;
        this.reservaDAO = null;
    }

    @Override
    public Collection<Reserva> reservasPorUsuario(Usuario usuario) {

        return usuario.getReservas();
    }

    @Override
    public Collection<Locacao> locacoesPorUsuario(Usuario usuario) {

        return usuario.getLocacoes();
    }

    @Override
    public Locacao locacaoPorId(int locacaoId)
        throws ObjetoNaoEncontradoException {

        return this.locacaoDAO.find(locacaoId);
    }

    @Override
    public Reserva reservaPorId(int reservaId)
        throws ObjetoNaoEncontradoException {

        return this.reservaDAO.find(reservaId);
    }

    @Override
    public Usuario usuarioPorId(int usuarioId)
        throws ObjetoNaoEncontradoException {

        return this.usuarioDAO.find(usuarioId);
    }

    @Override
    public ItemAcervo itemPorId(int itemAcervoId)
        throws ObjetoNaoEncontradoException {

        return this.itemAcervoDAO.find(itemAcervoId);
    }

    /**
     * XXX Forçando carregamento EAGER da lista
     *
     * Em futuras versões do java + jpa, utilizando streams seria possível
     * reduzir o overhead de uma lista inteira na heap utilizando métodos para
     * paginação.
     *
     * <code>
     *   // exemplo
     *   list.stream().skip(10).limit(10).collect(toList());
     * </code>
     *
     * @param list
     * @return
     */
    private <T> Collection<T> initialise(Collection<T> list) {

        list.size();

        return list;
    }

    @Override
    public Collection<ItemAcervo> itens() {

        return this.initialise(super.itens());
    }

    @Override
    public Collection<Locacao> locacoes() {

        return this.initialise(super.locacoes());
    }

    @Override
    public Collection<Reserva> reservas() {

        return this.initialise(super.reservas());
    }
}
