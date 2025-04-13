package edu.uniuv.grupo2.tourgemeas.partner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
	public Page<Partner> findAll(Pageable pageable);
	public boolean existsByNameIgnoreCase(String name);
}
