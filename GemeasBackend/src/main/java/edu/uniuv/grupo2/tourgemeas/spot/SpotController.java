package edu.uniuv.grupo2.tourgemeas.spot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/spots")  // Define um prefixo para as rotas
public class SpotController {
    private final SpotService spotService;

    @PostMapping
    public ResponseEntity<Spot> create(@RequestBody @Valid Spot spot) {
        Spot createdSpot = spotService.createSpot(spot);
        return ResponseEntity.ok(createdSpot);
    }

    @GetMapping
    public ResponseEntity<List<Spot>> getAllSpots() {
        return ResponseEntity.ok(spotService.getAllSpots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spot> getSpotById(@PathVariable Long id) {
        return ResponseEntity.ok(spotService.getSpotById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Spot> updateSpot(@PathVariable Long id, @RequestBody Spot spot) {
        return ResponseEntity.ok(spotService.updateSpot(id, spot));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpot(@PathVariable Long id) {
        spotService.deleteSpot(id);
        return ResponseEntity.noContent().build();
    }
}
