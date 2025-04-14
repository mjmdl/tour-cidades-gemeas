package edu.uniuv.grupo2.tourgemeas.event;

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	public Page<Event> findAll(Pageable pageable);
	public boolean existsByNameIgnoreCase(String name);
	
	@Query(
		"SELECT COUNT(Event.Id) > 0 " +
		"FROM Event Event " +
		"WHERE Event.startDate BETWEEN :startDate AND :endDate " +
		"	AND Event.endDate BETWEEN :startDate AND :endDate"
	)
    public boolean existsBetweenStartAndEndDate(
		@Param("startDate") OffsetDateTime startDate, 
        @Param("endDate") OffsetDateTime endDate
	);
	
	@Query(
		"SELECT COUNT(Event.Id) > 0 " +
		"FROM Event Event " +
		"WHERE Event.id <> :eventId " + 
		"	AND Event.startDate BETWEEN :startDate AND :endDate " +
		"	AND Event.endDate BETWEEN :startDate AND :endDate"
	)
    public boolean existsNotByIdAndBetweenStartAndEndDate(
		@Param("eventId") Long eventId,
		@Param("startDate") OffsetDateTime startDate, 
        @Param("endDate") OffsetDateTime endDate
	);
}
