
package poo.library.app;

import poo.library.comum.IItemAcervo;
import poo.library.dao.util.DAOStorage;
import poo.library.util.IBibliotecaStorage;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class ExemploMemoria {

    public static void main(String[] args) {

        //Usuario u1;
        //Usuario u2;
        //Collection<Usuario> usuarios = new ArrayList<Usuario>();
        //
        //usuarios.add(u1 = new Usuario("José A.", "12345678911"));
        //u1.setEndereco("R. João Bonifácio");
        //
        //usuarios.add(u2 = new Usuario("João", "12345678911"));
        //u2.setEndereco("R. José 2º");

        App.configure();

        IBibliotecaStorage storage = new DAOStorage();

        Iterable<IItemAcervo> us = storage.itens();

        for (IItemAcervo u : us) {

            System.out.println(u);
        }
    }
}
