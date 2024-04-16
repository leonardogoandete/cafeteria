package br.com.cafeteria.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Cafe implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titulo;
    private String descricao;
    private String origem;

    public Cafe(){}
    public Cafe(String titulo, String descricao, String origem) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.origem = origem;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    @Override
    public String toString() {
        return "Cafe preferido: " +
                titulo +
                ",descrição " +
                descricao +
                ",origem " +
                origem;

    }
}
