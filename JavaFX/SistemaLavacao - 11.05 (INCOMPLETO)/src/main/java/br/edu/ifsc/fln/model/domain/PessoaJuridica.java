package br.edu.ifsc.fln.model.domain;

import java.sql.Date;
import java.time.LocalDate;

public class PessoaJuridica extends Cliente {

    private String cnpj, inscricaoEstadual;

    public PessoaJuridica() {
        super();
    }

    public PessoaJuridica(int id, String nome, String celular, String email, LocalDate dataCadastro, String cnpj, String inscricaoEstadual) {
        super(id, nome, celular, email, dataCadastro);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }
}
