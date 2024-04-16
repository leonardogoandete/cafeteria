package br.com.cafeteria.model;

public class ItemLista {
    int imagem;
    String nome;
    public ItemLista(int imagem, String nome){
        this.imagem = imagem;
        this.nome = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public String getNome() {
        return nome;
    }

}
