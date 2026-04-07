package br.edu.ifsc.fln.model.domain;

import java.util.Objects;

public class Marca {
    private int id;
    private String nome;

    public Marca() {
    }

    public Marca(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

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

    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Marca marca = (Marca) o;
        return id == marca.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
