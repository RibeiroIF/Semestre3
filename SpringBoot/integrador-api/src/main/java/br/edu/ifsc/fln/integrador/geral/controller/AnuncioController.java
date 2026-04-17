package br.edu.ifsc.fln.integrador.geral.controller;

import br.edu.ifsc.fln.integrador.domain.Anuncio;
import br.edu.ifsc.fln.integrador.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @PostMapping
    public Anuncio adicionar(@RequestBody Anuncio anuncio) {
        return anuncioRepository.save(anuncio);
    }

    @GetMapping
    public List<Anuncio> listar() {
        return anuncioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Anuncio buscarPorId(@PathVariable Integer id) {
        return anuncioRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Anuncio atualizar(@PathVariable Integer id, @RequestBody Anuncio anuncio) {
        anuncio.setId(id);
        return anuncioRepository.save(anuncio);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        anuncioRepository.deleteById(id);
    }

}
