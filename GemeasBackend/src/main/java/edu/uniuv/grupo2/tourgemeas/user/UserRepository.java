package edu.uniuv.grupo2.tourgemeas.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(final String email);
	Optional<User> findByEmail(final String email);
}
