package edu.uniuv.grupo2.tourgemeas.partner;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.PositiveOrZero;

public record UpdatePartner(
	@Length(min = 3, max = 255)
	String name,

	BigDecimal latitude,

	BigDecimal longitude,

	@PositiveOrZero
	Long score,

	@PositiveOrZero
	BigDecimal minimumValue,

	@PositiveOrZero
	BigDecimal maximumValue
) {}
