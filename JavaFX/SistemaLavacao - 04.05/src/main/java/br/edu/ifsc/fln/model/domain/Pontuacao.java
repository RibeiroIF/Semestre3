package br.edu.ifsc.fln.model.domain;

public class Pontuacao {

    private int quantidade = 0;

    public Pontuacao() {
        super();
        this.quantidade = 0;
    }

    public void somarPontos(int quantidade) {
        this.quantidade += quantidade;
    }

    // EXCEÇÃO PARA CASO SEJA SUBTRAÍDO UM VALOR MAIOR QUE A QUANTIDADE ATUAL OU A QUANTIDADE ESTEJA EM ZERO
//    public void subtrairPontos(int quantidade) throws ExceptionLavacao {
//        if (this.quantidade == 0 || quantidade >= this.quantidade) {
//            throw new ExceptionLavacao("Impossível retirar os pontos deferidos");
//        }
//        else {
//            this.quantidade -= quantidade;
//        }
//    }

    public int verificarPontos() {
        return quantidade;
    }
}
