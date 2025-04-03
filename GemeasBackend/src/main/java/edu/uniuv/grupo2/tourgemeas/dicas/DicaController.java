package edu.uniuv.grupo2.tourgemeas.dicas;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/dicas")
public class DicaController {
    private final DicaService dicaService;

    @GetMapping
    public List<Dica> listarDicas() {
        return dicaService.listarDicas();
    }

    @PostMapping
    public ResponseEntity<Dica> adicionarDica(@RequestBody Dica dica) {
        return ResponseEntity.ok(dicaService.salvarDica(dica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDica(@PathVariable Long id) {
        dicaService.deletarDica(id);
        return ResponseEntity.noContent().build();
    }
}
