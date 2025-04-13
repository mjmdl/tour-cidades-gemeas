package edu.uniuv.grupo2.tourgemeas.partner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import edu.uniuv.grupo2.tourgemeas.HttpException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PartnerService {
	private final PartnerRepository partnerRepository;

	public Long create(CreatePartner partner) {
		if (partnerRepository.existsByNameIgnoreCase(partner.name())) {
			throw new HttpException(
				HttpStatus.CONFLICT, 
				"PARTNER_EXISTS",
				"O parceiro já existe."
			);
		}
		Partner newPartner = new Partner();
		newPartner.setName(partner.name());
		newPartner.setScore(partner.score());
		newPartner.setLatitude(partner.latitude());
		newPartner.setLongitude(partner.longitude());
		newPartner.setMinimumValue(partner.minimumValue());
		newPartner.setMaximumValue(partner.maximumValue());
		Partner savedPartner = partnerRepository.save(newPartner);
		return savedPartner.getId();
	}

	public void update(Long partnerId, UpdatePartner changes) {
		Partner partner = partnerRepository
			.findById(partnerId)
			.orElseThrow(() -> new HttpException(
				HttpStatus.NOT_FOUND,
				"PARTNER_NOT_FOUND",
				"O parceiro não existe."
			));
		if (changes.name() != null) {
			if (
				!changes.name().equalsIgnoreCase(partner.getName()) &&
				partnerRepository.existsByNameIgnoreCase(changes.name())
			) {
				throw new HttpException(
					HttpStatus.CONFLICT, 
					"PARTNER_EXISTS", 
					"O parceiro já existe."
				);
			}
			partner.setName(changes.name());
		}
		if (changes.latitude() != null) {
			partner.setLatitude(changes.latitude());
		}
		if (changes.longitude() != null) {
			partner.setLongitude(changes.longitude());
		}
		if (changes.score() != null) {
			partner.setScore(changes.score());
		}
		if (changes.minimumValue() != null) {
			partner.setMinimumValue(changes.minimumValue());
		}
		if (changes.maximumValue() != null) {
			partner.setMaximumValue(changes.maximumValue());
		}
		partnerRepository.save(partner);
	}

	public void delete(Long partnerId) {
		if (!partnerRepository.existsById(partnerId)) {
			throw new HttpException(
				HttpStatus.NOT_FOUND, 
				"PARTNER_NOT_FOUND", 
				"O parceiro não existe."
			);
		}
		partnerRepository.deleteById(partnerId);
	}

	public Page<Partner> page(Pageable pageable) {
		return partnerRepository.findAll(pageable);
	}

	public Partner find(Long partnerId) {
		return partnerRepository
			.findById(partnerId)
			.orElseThrow(() -> new HttpException(
				HttpStatus.NOT_FOUND,
				"PARTNER_NOT_FOUND",
				"O parceiro não existe."
			));
	}
}
