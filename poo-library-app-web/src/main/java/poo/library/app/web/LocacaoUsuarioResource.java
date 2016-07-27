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

import java.util.Collection;

import javax.ws.rs.Path;

import poo.library.app.web.dto.LocacaoDTO;
import poo.library.app.web.util.ISubResource;
import poo.library.dao.comum.DAOFactory;
import poo.library.dao.comum.IDAO;
import poo.library.modelo.Locacao;
import poo.library.modelo.Usuario;
import poo.library.util.IConversor;
import poo.library.util.ObjetoNaoEncontradoException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
@Path(LocacaoUsuarioResource.PATH)
public class LocacaoUsuarioResource extends GenericSubResource<LocacaoDTO>
    implements ISubResource<LocacaoDTO> {

    public static final String PATH = "usuario/{parentId}/locacao";
    public static final IConversor<LocacaoDTO> CONVERSOR_DTO = conversor(LocacaoDTO.class);

    private final IDAO<Usuario> parentDAO;

    public LocacaoUsuarioResource() {

        this(DAOFactory.novoDAO(Usuario.class));
    }

    public LocacaoUsuarioResource(IDAO<Usuario> parentDAO) {

        this.parentDAO = parentDAO;

        this.initBehavior(this);
    }

    @Override
    public Collection<Locacao> get(int usuarioId)
        throws ObjetoNaoEncontradoException {

        return getParentDAO().find(usuarioId).getLocacoes();
    }

    @Override
    public Locacao get(int usuarioId, int id)
        throws ObjetoNaoEncontradoException {

        return getParentDAO().find(usuarioId).locacaoPorId(id);
    }

    @Override
    protected IConversor<LocacaoDTO> getConversorDTO() {

        return CONVERSOR_DTO;
    }

    @Override
    protected IDAO<Usuario> getParentDAO() {

        return parentDAO;
    }
}
