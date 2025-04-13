package edu.uniuv.grupo2.tourgemeas.partner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uniuv.grupo2.tourgemeas.auth.AdminOnly;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PartnerController {
	private final PartnerService partnerService;

	@AdminOnly
	@PostMapping(path = "/partner")
	public Long create(@RequestBody @Valid CreatePartner dto) {
		return partnerService.create(dto);
	}

	@AdminOnly
	@PatchMapping(path = "/partner/id={partnerId}")
	public void update(
		@PathVariable Long partnerId, 
		@RequestBody @Valid UpdatePartner dto
	) {
		partnerService.update(partnerId, dto);
	}

	@AdminOnly
	@DeleteMapping(path = "/partner/id={partnerId}")
	public void delete(@PathVariable Long partnerId) {
		partnerService.delete(partnerId);
	}

	@GetMapping("/partner")
	public Page<Partner> page(Pageable pageable) {
		return partnerService.page(pageable);
	}

	@GetMapping("/partner/id={partnerId}")
	public Partner find(@PathVariable Long partnerId) {
		return partnerService.find(partnerId);
	}
}
