package edu.uniuv.grupo2.tourgemeas.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(final String email);
	User findByEmail(final String email);
}
