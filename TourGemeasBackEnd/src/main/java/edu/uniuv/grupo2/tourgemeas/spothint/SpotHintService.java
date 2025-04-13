package edu.uniuv.grupo2.tourgemeas.spothint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import edu.uniuv.grupo2.tourgemeas.HttpException;
import edu.uniuv.grupo2.tourgemeas.spot.Spot;
import edu.uniuv.grupo2.tourgemeas.spot.SpotRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SpotHintService {
	private final SpotRepository spotRepository;
	private final SpotHintRepository spotHintRepository;

	public Long create(CreateSpotHint spotHint) {
		Spot spot = spotRepository
			.findById(spotHint.spotId())
			.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "SPOT_NOT_FOUND", "O ponto turístico não existe."));
		if (spotHintRepository.existsByDescriptionIgnoreCaseAndSpotId(spotHint.description(), spotHint.spotId())) {
			throw new HttpException(HttpStatus.CONFLICT, "SPOT_HINT_EXISTS", "A dica já existe.");
		}
		SpotHint newSpotHint = new SpotHint();
		newSpotHint.setDescription(spotHint.description());
		newSpotHint.setScore(spotHint.score());
		newSpotHint.setSpot(spot);
		SpotHint savedSpotHint = spotHintRepository.save(newSpotHint);
		return savedSpotHint.getId();
	}

	public void delete(Long id) {
		if (!spotHintRepository.existsById(id)) {
			throw new HttpException(HttpStatus.NOT_FOUND, "SPOT_HINT_NOT_FOUND", "A dica não existe.");
		}
		spotHintRepository.deleteById(id);
	}

	public Page<SpotHint> page(Long spotId, Pageable pageable) {
		return spotHintRepository.findAllBySpotId(spotId, pageable);
	}

	public SpotHint find(Long spotHintId) {
		return spotHintRepository
			.findById(spotHintId)
			.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "SPOT_HINT_NOT_FOUND", "A dica não existe."));
	}
}
