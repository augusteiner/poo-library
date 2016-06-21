package acervo;

public class Livro extends ItemAcervo {
    
    private String titulo;
    private String autor;
    private String ISBM;
    private int edicao;
    private int quantidade;
    
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String valor){
        titulo = valor;
    }
    
    
    public String getAutor(){
        return autor;
    }
    public void setAutor(String valor){
        autor = valor;
    }
    
    
    public String getIsbm(){
        return ISBM;
    }
    public void setIsbm(String valor){
        ISBM = valor;
    }
    
    
    public int getEdicao(){
        return edicao;
    }
    public void setEdicao(int valor){
        edicao = valor;
    }
    
    
    public int getQuantidade(){
        return quantidade;
    }
    public void setQuantidade(int valor){
        quantidade = valor;
    }
    
}
