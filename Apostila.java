package acervo;

public class Apostila extends ItemAcervo {
    
    private String titulo;
    private String autor;
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
    
    
    public int getQuantidade(){
        return quantidade;
    }
    public void setQuantidade(int valor){
        quantidade = valor;
    }
    
}
