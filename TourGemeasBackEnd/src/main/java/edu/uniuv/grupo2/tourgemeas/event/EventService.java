package edu.uniuv.grupo2.tourgemeas.event;

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import edu.uniuv.grupo2.tourgemeas.DateUtil;
import edu.uniuv.grupo2.tourgemeas.HttpException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventService {
	private final @NotNull EventRepository eventRepository;

	public Long create(CreateEvent createEvent) {
		checkName(createEvent.name());
		Event newEvent = new Event();
		newEvent.setName(createEvent.name());
		newEvent.setStartDate(
			DateUtil.offsetDateTimeFromDate(createEvent.startDate())
		);
		newEvent.setEndDate(
			DateUtil.offsetDateTimeFromDate(createEvent.endDate())
		);
		checkOverlap(newEvent.getStartDate(), newEvent.getEndDate());
		Event savedEvent = eventRepository.save(newEvent);
		return savedEvent.getId();
	}

	public void update(Long eventId, UpdateEvent changes) {
		Event current = find(eventId);
		if (changes.name() != null) {
			if (!current.getName().equalsIgnoreCase(changes.name())) {
				checkName(changes.name());
			}
			current.setName(changes.name());
		}
		if (changes.startDate() != null) {
			current.setStartDate(
				DateUtil.offsetDateTimeFromDate(changes.startDate())
			);
		}
		if (changes.endDate() != null) {
			current.setEndDate(
				DateUtil.offsetDateTimeFromDate(changes.endDate())
			);
		}
		if (changes.startDate() != null || changes.endDate() != null) {
			checkOverlap(eventId, current.getStartDate(), current.getEndDate());
		}
		eventRepository.save(current);
	}

	public void delete(Long eventId) {
		checkExists(eventId);
		eventRepository.deleteById(eventId);
	}

	public Page<Event> page(Pageable pageable) {
		return eventRepository.findAll(pageable);
	}

	public Event find(Long eventId) {
		return eventRepository
			.findById(eventId)
			.orElseThrow(() -> new HttpException(
				HttpStatus.NOT_FOUND, 
				"EVENT_NOT_FOUND", 
				"O evento não existe."
			));
	}

	private void checkExists(Long eventId) {
		if (!eventRepository.existsById(eventId)) {
			throw new HttpException(
				HttpStatus.NOT_FOUND, 
				"EVENT_NOT_FOUND", 
				"O evento não existe."
			);
		}
	}

	private void checkName(String name) {
		if (eventRepository.existsByNameIgnoreCase(name)) {
			throw new HttpException(
				HttpStatus.CONFLICT,
				"EVENT_EXISTS",
				"O evento já existe."
			);
		}
	}

	private void checkOverlap(OffsetDateTime start, OffsetDateTime end) {
		if (eventRepository.existsBetweenStartAndEndDate(start, end)) {
			throw new HttpException(
				HttpStatus.CONFLICT, 
				"EVENT_OVERLAPS", 
				"Existe um evento nesse intervalo."
			);
		}
	}

	private void checkOverlap(Long eventId, OffsetDateTime start, OffsetDateTime end) {
		if (eventRepository.existsNotByIdAndBetweenStartAndEndDate(
			eventId, 
			start, 
			end
		)) {
			throw new HttpException(
				HttpStatus.CONFLICT, 
				"EVENT_OVERLAPS", 
				"Existe um evento nesse intervalo."
			);
		}
	}
}
