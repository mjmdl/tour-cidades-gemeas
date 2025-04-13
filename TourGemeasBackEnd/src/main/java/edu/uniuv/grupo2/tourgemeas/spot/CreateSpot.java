package edu.uniuv.grupo2.tourgemeas.spot;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class CreateSpot {
	public static final int NAME_MIN = 3;
	public static final int NAME_MAX = 255;

	@NotBlank
	@Length(min = NAME_MIN, max = NAME_MAX)
	private String name;
	
	@NotNull
	private BigDecimal latitude;
	
	@NotNull
	private BigDecimal longitude;
	
	@NotNull
	@PositiveOrZero
	private Long score;
}
