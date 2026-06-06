package br.edu.ifsc.fln.model.service;

import br.edu.ifsc.fln.model.dao.ParametrosSistemaDAO;
import br.edu.ifsc.fln.model.domain.ParametrosSistema;

public class ParametrosService {

    private static ParametrosSistema config;

    private static final ParametrosSistemaDAO dao = new ParametrosSistemaDAO();

    // 🔥 carregar uma única vez
    public static void carregar() {
        config = dao.buscarPrimeiro();
    }

    public static int getPontos() {
        if (config == null) {
            carregar();
        }
        return config.getPontos();
    }

    public static void atualizarPontos(int pontos) {
        config.setPontos(pontos);
        dao.atualizar(config);
    }
}
