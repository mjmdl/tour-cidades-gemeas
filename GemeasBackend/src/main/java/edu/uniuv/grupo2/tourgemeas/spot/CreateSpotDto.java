package edu.uniuv.grupo2.tourgemeas.spot;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CreateSpotDto {
	@Getter
	public static class Req {
		public static final int NAME_MIN = 3;
		public static final int NAME_MAX = 255;

		@NotBlank
		@Length(min = NAME_MIN, max = NAME_MAX)
		private String name;

		@NotBlank
		@PositiveOrZero
		private Long score;
	}

	@Getter
	@AllArgsConstructor
	public static class Res {
		private Long id;
	}
}
