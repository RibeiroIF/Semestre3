package br.edu.ifsc.fln.model.domain;

import java.util.Objects;

public class Servico {
    private int id;
    private String descricao;
    private float valor;
    static private int pontos = 20;

    public Servico() {
    }

    public Servico(int id, String descricao, float valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public static int getPontos() {
        return Servico.pontos;
    }

    public static void setPontos(int pontos) {
        Servico.pontos = pontos;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return id == servico.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
