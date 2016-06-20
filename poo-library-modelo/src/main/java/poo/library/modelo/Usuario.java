
package poo.library.modelo;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Usuario {

    private int id;

    private String nome;
    private String endereco;
    private String cpf;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getEndereco() {

        return endereco;
    }

    public void setEndereco(String endereco) {

        this.endereco = endereco;
    }

    public String getCpf() {

        return cpf;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf;
    }
}
