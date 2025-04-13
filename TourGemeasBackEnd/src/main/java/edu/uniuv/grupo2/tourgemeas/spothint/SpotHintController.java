package edu.uniuv.grupo2.tourgemeas.spothint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uniuv.grupo2.tourgemeas.auth.AdminOnly;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SpotHintController {
	private final SpotHintService spotHintService;

	@AdminOnly
	@PostMapping("/spot-hint")
	public ResponseEntity<Long> create(@RequestBody @Valid CreateSpotHint dto) {
		return ResponseEntity.ok(
			spotHintService.create(dto)
		);
	}

	@AdminOnly
	@DeleteMapping("/spot-hint/id={spotHintId}")
	public ResponseEntity<Void> delete(@PathVariable Long spotHintId) {
		spotHintService.delete(spotHintId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/spot-hint/spot-id={spotId}")
	public ResponseEntity<Page<SpotHint>> page(@PathVariable Long spotId, Pageable pageable) {
		return ResponseEntity.ok(
			spotHintService.page(spotId, pageable)
		);
	}

	@GetMapping("/spot-hint/id={spotHintId}")
	public ResponseEntity<SpotHint> find(@PathVariable Long spotHintId) {
		return ResponseEntity.ok(
			spotHintService.find(spotHintId)
		);
	}
}
