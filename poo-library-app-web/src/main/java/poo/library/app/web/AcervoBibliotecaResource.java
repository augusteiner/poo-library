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
package poo.library.app.web;

import static poo.library.app.web.util.Conversores.*;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Path;

import poo.library.app.web.dto.ItemAcervoDTO;
import poo.library.app.web.util.Conversores;
import poo.library.app.web.util.ISubResource;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.dao.util.BuscadorDb;
import poo.library.modelo.Biblioteca;
import poo.library.modelo.ItemAcervo;
import poo.library.modelo.comum.IBuscador;
import poo.library.util.IConversor;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Path(AcervoBibliotecaResource.PATH)
public class AcervoBibliotecaResource extends GenericSubResource<ItemAcervoDTO>
    implements ISubResource<ItemAcervoDTO>, IConversor<ItemAcervo> {

    public static final String PATH = "biblioteca/{parentId}/acervo";
    public static final IConversor<ItemAcervoDTO> CONVERSOR_DTO = conversor(ItemAcervoDTO.class);

    private final IDAO<Biblioteca> parentDAO;

    public AcervoBibliotecaResource() {

        this.parentDAO = DAOFactory.novoDAO(Biblioteca.class);

        this.initBehavior(this);
    }

    @Override
    public ItemAcervo converter(Object input) {

        return Conversores.converter(input, ItemAcervo.class);
    }

    @Override
    public void converter(Object input, ItemAcervo output) {

        Conversores.converter(input, output);
    }

    @Override
    public URI createdAt(int bibliotecaId, int id) {

        return URI.create(String.format(
            "biblioteca/%d/acervo/%d",

            bibliotecaId,
            id));
    }

    @Override
    public void delete(int bibliotecaId, int id)
        throws ObjetoNaoEncontradoException {

        IBuscador buscador = buscador(bibliotecaId);

        ItemAcervo item = (ItemAcervo) buscador.itemPorId(id);

        if (item.getBibliotecaId() != bibliotecaId) {

            throw new BadRequestException(new IllegalArgumentException(
                "Id da biblioteca deve ser o mesmo que o do Item do Acervo"));
        }

        System.out.println(String.format(
            "REMOVENDO ITEM #%d DA BIBLIOTECA (itens: %d)",

            item.getId(),

            item.getBiblioteca().getAcervo().size()));

        item.getBiblioteca().removeAcervo(item);
    }

    @Override
    public Collection<ItemAcervo> get(int bibliotecaId)
        throws ObjetoNaoEncontradoException {

        Biblioteca biblioteca = this.bibliotecaPorId(bibliotecaId);

        return biblioteca.getAcervo();
    }

    @Override
    public ItemAcervoDTO get(int bibliotecaId, int id)
        throws ObjetoNaoEncontradoException {

        IBuscador buscador = buscador(bibliotecaId);

        return this.getConversorDTO().converter(buscador.itemPorId(id));
    }

    @Override
    public void post(int bibliotecaId, ItemAcervoDTO itemAcervo)
        throws ObjetoNaoEncontradoException {

        if (bibliotecaId != itemAcervo.getBibliotecaId()) {

            throw new BadRequestException(new IllegalArgumentException(
                "Id da biblioteca deve ser o mesmo que o do Item do Acervo"));
        }

        ItemAcervo item;
        IBuscador buscador = buscador(bibliotecaId);

        if (itemAcervo.getId() == 0) {

            Biblioteca biblioteca = this.bibliotecaPorId(bibliotecaId);

            item = this.converter(itemAcervo);

            biblioteca.addAcervo(item);

        } else {

            item = (ItemAcervo) buscador.itemPorId(itemAcervo.getId());

            this.converter(itemAcervo, item);
        }

        System.out.println("FLUSING ACERVO!!!");

        this.flush();

        //Collection<?> list = item.getBiblioteca().getAcervo();
        //
        //System.out.println(String.format(
        //    "LISTA APÓS FLUSH: (lista: %s; size: %d)",
        //
        //    list,
        //    list.size()));

        this.getConversorDTO().converter(item, itemAcervo);
    }

    @Override
    public void put(
        int bibliotecaId,
        int id,

        ItemAcervoDTO dto) throws ObjetoNaoEncontradoException {

        if (dto.getId() != id ||
            dto.getBibliotecaId() != bibliotecaId) {

            throw new BadRequestException(new IllegalArgumentException(
                "Id da biblioteca deve ser o mesmo que o do Item do Acervo"));
        }

        IBuscador buscador = buscador(bibliotecaId);
        ItemAcervo item = buscador.itemPorId(id);

        this.converter(dto, item);

        //System.out.println(String.format(
        //    "PUT: %s",
        //
        //    item.toString()));
    }

    protected Biblioteca bibliotecaPorId(int bibliotecaId) throws ObjetoNaoEncontradoException {

        return this.getParentDAO().find(bibliotecaId);
    }

    protected IBuscador buscador(int bibliotecaId) {

        IBuscador buscador = new BuscadorDb();

        return buscador;
    }

    @Override
    protected IConversor<ItemAcervoDTO> getConversorDTO() {

        return CONVERSOR_DTO;
    }

    @Override
    protected IDAO<Biblioteca> getParentDAO() {

        return this.parentDAO;
    }
}
