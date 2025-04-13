package edu.uniuv.grupo2.tourgemeas.spothint;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateSpotHint(
	@NotNull
	@Positive
	Long spotId,
	
	@NotBlank
	@Length(min = 1, max = 5000)
	String description,

	@NotNull
	@PositiveOrZero
	Long score
) {}
