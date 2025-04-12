package edu.uniuv.grupo2.tourgemeas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uniuv.grupo2.tourgemeas.entities.Spot;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {
}
