package edu.uniuv.grupo2.tourgemeas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uniuv.grupo2.tourgemeas.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByEmail(String email);
	public boolean existsByEmail(String email);
}
