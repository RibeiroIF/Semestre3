package br.edu.ifsc.fln.integrador.geral.controller;

import br.edu.ifsc.fln.integrador.domain.Denuncia;
import br.edu.ifsc.fln.integrador.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/denuncia")
public class DenunciaController {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @PostMapping
    public Denuncia adicionar(@RequestBody Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    @GetMapping
    public List<Denuncia> listar() {
        return denunciaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Denuncia buscarPorId(@PathVariable Integer id) {
        return denunciaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Denuncia atualizar(@PathVariable Integer id, @RequestBody Denuncia denuncia) {
        denuncia.setId(id);
        return denunciaRepository.save(denuncia);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        denunciaRepository.deleteById(id);
    }
}
