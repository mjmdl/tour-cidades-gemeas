package edu.uniuv.grupo2.tourgemeas.spot;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {
	public Page<Spot> findAll(Pageable pageable);
	public boolean existsByNameIgnoreCase(String name);
}
