package edu.uniuv.grupo2.tourgemeas.partner;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreatePartner(
	@NotBlank
	@Length(min = 3, max = 255)
	String name,

	@NotNull
	BigDecimal latitude,

	@NotNull
	BigDecimal longitude,

	@NotNull
	@PositiveOrZero
	Long score,

	@NotNull
	@PositiveOrZero
	BigDecimal minimumValue,

	@NotNull
	@PositiveOrZero
	BigDecimal maximumValue
) {}
