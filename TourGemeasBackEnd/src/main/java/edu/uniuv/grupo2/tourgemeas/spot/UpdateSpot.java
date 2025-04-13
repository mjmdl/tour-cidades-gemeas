package edu.uniuv.grupo2.tourgemeas.spot;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.PositiveOrZero;

public record UpdateSpot(
	@Length(min = CreateSpot.NAME_MIN, max = CreateSpot.NAME_MAX)
	String name,
	
	BigDecimal latitude,
	
	BigDecimal longitude,

	@PositiveOrZero
	Long score
) {}
