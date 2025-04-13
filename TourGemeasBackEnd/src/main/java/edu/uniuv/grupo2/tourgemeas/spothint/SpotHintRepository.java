package edu.uniuv.grupo2.tourgemeas.spothint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotNull;

@Repository
public interface SpotHintRepository extends JpaRepository<SpotHint, Long> {
	public Page<SpotHint> findAllBySpotId(Long spotId, Pageable pageable);
	public boolean existsByDescriptionIgnoreCaseAndSpotId(@NotNull String description, @NotNull Long spotId);
}
