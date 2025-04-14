package edu.uniuv.grupo2.tourgemeas.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uniuv.grupo2.tourgemeas.auth.AdminOnly;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EventController {
	private final @NotNull EventService eventService;

	@AdminOnly
	@PostMapping("/event")
	public Long create(@RequestBody @Valid CreateEvent createEvent) {
		return eventService.create(createEvent);
	}

	@AdminOnly
	@PatchMapping("/event/id={eventId}")
	public void update(
		@PathVariable Long eventId, 
		@RequestBody @Valid UpdateEvent updateEvent
	) {
		eventService.update(eventId, updateEvent);
	}

	@AdminOnly
	@DeleteMapping("/event/id={eventId}")
	public void delete(@PathVariable Long eventId) {
		eventService.delete(eventId);
	}

	@GetMapping("/event")
	public Page<EventFound> page(Pageable pageable) {
		return eventService.page(pageable).map(EventFound::new);
	}

	@GetMapping("/event/id={eventId}")
	public EventFound find(@PathVariable Long eventId) {
		return new EventFound(eventService.find(eventId));
	}
}
