package edu.uniuv.grupo2.tourgemeas.spot;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uniuv.grupo2.tourgemeas.auth.AdminOnly;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class SpotController {
	private final SpotService spotService;
	
	@AdminOnly
	@PostMapping("/spot")
	public ResponseEntity<CreateSpotResult> create(@RequestBody @Valid CreateSpot createSpot) {
		return ResponseEntity.ok(spotService.create(createSpot));
	}

	@AdminOnly
	@PatchMapping("/spot/id={spotId}")
	public ResponseEntity<Void> update(@PathVariable Long spotId, @RequestBody @Valid UpdateSpot updateSpot) {
		spotService.update(spotId, updateSpot);
		return ResponseEntity.noContent().build();
	}

	@AdminOnly
	@DeleteMapping("/spot/id={spotId}")
	public ResponseEntity<Void> delete(@PathVariable Long spotId) {
		spotService.delete(spotId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/spot")
	public ResponseEntity<Page<Spot>> page(Pageable pageable) {
		return ResponseEntity.ok(spotService.page(pageable));
	}

	@GetMapping("/spot/id={spotId}")
	public ResponseEntity<Spot> find(@PathVariable Long spotId) {
		return ResponseEntity.ok(spotService.find(spotId));
	}
}
