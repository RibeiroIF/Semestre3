package br.edu.ifsc.fln.integrador.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String motivo;
    private String data;
    //private boolean resolvido;

    @ManyToOne
    private Anuncio anuncio;

    public Denuncia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

//    public boolean isResolvido() {
//        return resolvido;
//    }
//
//    public void setResolvido(boolean resolvido) {
//        this.resolvido = resolvido;
//    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Denuncia denuncia = (Denuncia) o;
        return id == denuncia.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
