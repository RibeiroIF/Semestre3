package br.edu.ifsc.fln.integrador.geral.controller;

import br.edu.ifsc.fln.integrador.domain.ArquivoImagem;
import br.edu.ifsc.fln.integrador.repository.ArquivoImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arquivo-imagem")
public class ArquivoImagemController {

    @Autowired
    private ArquivoImagemRepository arquivoimagemRepository;

    @PostMapping
    public ArquivoImagem adicionar(@RequestBody ArquivoImagem arquivoimagem) {
        return arquivoimagemRepository.save(arquivoimagem);
    }

    @GetMapping
    public List<ArquivoImagem> listar() {
        return arquivoimagemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ArquivoImagem buscarPorId(@PathVariable Integer id) {
        return arquivoimagemRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ArquivoImagem atualizar(@PathVariable Integer id, @RequestBody ArquivoImagem arquivoimagem) {
        arquivoimagem.setId(id);
        return arquivoimagemRepository.save(arquivoimagem);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        arquivoimagemRepository.deleteById(id);
    }
}
