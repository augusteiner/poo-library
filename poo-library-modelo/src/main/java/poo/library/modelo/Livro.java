/*
 * MIT License
 *
 * Copyright (c) 2016 José Nascimento & Juscelino Messias
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

/**
 * @author Juscelino Messias <pura_emocao2@hotmail.com>
 */
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
