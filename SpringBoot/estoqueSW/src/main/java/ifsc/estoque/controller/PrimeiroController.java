package ifsc.estoque.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class PrimeiroController {

    @GetMapping("/teste")
    public String testeInicial(){
        return "<h1> Feito </h1>";
    }
}
