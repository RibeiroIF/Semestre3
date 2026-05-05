package br.edu.ifsc.fln.model.domain;

public enum ECategoria {
    PEQUENO(1, "Carro pequeno"),
    MÉDIO(2, "Carro médio"),
    GRANDE(3, "Carro grande"),
    MOTO(4, "Moto"),
    PADRÃO(5, "Carro padrão");
    private int id;
    private String descricao;

    ECategoria(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
