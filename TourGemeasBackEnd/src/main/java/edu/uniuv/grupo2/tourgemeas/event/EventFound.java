package edu.uniuv.grupo2.tourgemeas.event;

import java.util.Date;

import lombok.Getter;

@Getter
public class EventFound {
	private Long id;
	private String name;
	private Date startDate;
	private Date endDate;

	public EventFound(Event event) {
		this.id = event.getId();
		this.name = event.getName();
		this.startDate = Date.from(event.getStartDate().toInstant());
		this.endDate = Date.from(event.getEndDate().toInstant());
	}
}
