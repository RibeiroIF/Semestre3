package br.edu.ifsc.fln.integrador.geral.controller;

import br.edu.ifsc.fln.integrador.domain.Avaliacao;
import br.edu.ifsc.fln.integrador.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @PostMapping
    public Avaliacao adicionar(@RequestBody Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    @GetMapping
    public List<Avaliacao> listar() {
        return avaliacaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Avaliacao buscarPorId(@PathVariable Integer id) {
        return avaliacaoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Avaliacao atualizar(@PathVariable Integer id, @RequestBody Avaliacao avaliacao) {
        avaliacao.setId(id);
        return avaliacaoRepository.save(avaliacao);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        avaliacaoRepository.deleteById(id);
    }
}
