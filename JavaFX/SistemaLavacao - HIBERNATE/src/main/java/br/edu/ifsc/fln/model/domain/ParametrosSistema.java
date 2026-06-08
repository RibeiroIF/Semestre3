package br.edu.ifsc.fln.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "parametros")
public class ParametrosSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pontos", nullable = false)
    private int pontos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return String.valueOf(this.pontos);
    }


}
