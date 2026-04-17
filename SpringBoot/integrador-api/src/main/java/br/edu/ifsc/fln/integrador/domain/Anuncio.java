package br.edu.ifsc.fln.integrador.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;
    private Double preco;

    @ManyToOne
    private Categoria categoria;

    public Anuncio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Anuncio anuncio = (Anuncio) o;
        return Objects.equals(id, anuncio.id) && Objects.equals(titulo, anuncio.titulo) && Objects.equals(descricao, anuncio.descricao) && Objects.equals(preco, anuncio.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, preco);
    }
}
