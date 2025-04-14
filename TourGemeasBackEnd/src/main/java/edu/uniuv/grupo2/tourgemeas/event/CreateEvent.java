package edu.uniuv.grupo2.tourgemeas.event;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEvent(
	@NotBlank
	@Length(min = NAME_MIN, max = NAME_MAX)
	String name,
	
	@NotNull	
	Date startDate,
	
	@NotNull
	Date endDate
) {
	public static final int NAME_MIN = 3;
	public static final int NAME_MAX = 255;
}
