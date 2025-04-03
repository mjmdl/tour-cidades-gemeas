package edu.uniuv.grupo2.tourgemeas.parceiros;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parceiros")
@RequiredArgsConstructor
public class ParceiroController {

    private final ParceiroService parceiroService;

    @GetMapping
    public ResponseEntity<List<Parceiros>> getAllParceiros() {
        List<Parceiros> parceiros = parceiroService.findAll();
        return ResponseEntity.ok(parceiros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parceiros> getParceiroById(@PathVariable Long id) {
        return parceiroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Parceiros> createParceiro(@RequestBody CreateParceiroDto.Req dto) {
        Parceiros parceiro = new Parceiros();
        parceiro.setNome(dto.getNome());
        parceiro.setPosicao(dto.getPosicao());
        parceiro.setPontuacao(dto.getPontuacao());
        parceiro.setValorMinimo(dto.getValorMinimo());
        parceiro.setValorMaximo(dto.getValorMaximo());

        Parceiros novoParceiro = parceiroService.save(parceiro);
        return ResponseEntity.ok(novoParceiro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParceiro(@PathVariable Long id) {
        if (parceiroService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        parceiroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
