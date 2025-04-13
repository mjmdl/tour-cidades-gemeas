package edu.uniuv.grupo2.tourgemeas.spot;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import edu.uniuv.grupo2.tourgemeas.HttpException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SpotService {
	private final SpotRepository spotRepository;

	public CreateSpotResult create(CreateSpot spot) {
		if (spotRepository.existsByNameIgnoreCase(spot.getName())) {
			throw new HttpException(HttpStatus.CONFLICT, "SPOT_EXISTS", "O ponto turístico já existe.");
		}
		Spot newSpot = new Spot();
		newSpot.setName(spot.getName());
		newSpot.setLatitude(spot.getLatitude());
		newSpot.setLongitude(spot.getLongitude());
		newSpot.setScore(spot.getScore());
		Spot savedSpot = spotRepository.save(newSpot);
		return new CreateSpotResult(savedSpot.getId());
	}

	public void update(Long id, UpdateSpot update) {
		Spot current = spotRepository
			.findById(id)
			.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "SPOT_NOT_FOUND", "O ponto turístico não foi encontrado."));
		if (update.name() != null) {
			if (
				!update.name().equalsIgnoreCase(current.getName()) &&
				spotRepository.existsByNameIgnoreCase(update.name())
			) {
				throw new HttpException(HttpStatus.CONFLICT, "SPOT_EXISTS", "O ponto turístico já existe.");
			}
			current.setName(update.name());
		}
		if (update.latitude() != null) {
			current.setLatitude(update.latitude());
		}
		if (update.longitude() != null) {
			current.setLongitude(update.longitude());
		}
		if (update.score() != null) {
			current.setScore(update.score());
		}
		spotRepository.save(current);
	}

	public void delete(Long id) {
		if (!spotRepository.existsById(id)) {
			throw new HttpException(HttpStatus.NOT_FOUND, "SPOT_NOT_FOUND", "O ponto turístico não existe.");
		}
		spotRepository.deleteById(id);
	}

	public Page<Spot> page(Pageable pageable) {
		return spotRepository.findAll(pageable);
	}

	public Spot find(Long id) {
		return spotRepository
			.findById(id)
			.orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "SPOT_NOT_FOUND", "O ponto turístico não existe."));
	}
}
