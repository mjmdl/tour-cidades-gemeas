package edu.uniuv.grupo2.tourgemeas.event;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

public record UpdateEvent(
	@Length(min = CreateEvent.NAME_MIN, max = CreateEvent.NAME_MAX)
	String name,
	Date startDate,
	Date endDate
) {}
