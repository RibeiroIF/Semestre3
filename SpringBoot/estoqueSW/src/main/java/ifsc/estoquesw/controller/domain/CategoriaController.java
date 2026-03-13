package ifsc.estoquesw.controller.domain;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class CategoriaController {

    @GetMapping("/categoria")
    public List<Categoria> listar(){
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria(1,"Vestuário"));
        categorias.add(new Categoria(2,"Calçado"));
        return categorias;
    }
}
