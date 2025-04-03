package edu.uniuv.grupo2.tourgemeas.parceiros;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CreateParceiroDto {
    @Getter
    public static class Req {
        public static final int NAME_MIN = 3;
        public static final int NAME_MAX = 255;

        @NotBlank
        @Length(min = NAME_MIN, max = NAME_MAX)
        private String nome;

        @NotBlank
        private String posicao;

        @PositiveOrZero
        private Long pontuacao;

        @DecimalMin(value = "0.0", inclusive = false)
        private Double valorMinimo;

        @DecimalMax(value = "1000000.0")
        private Double valorMaximo;
    }

    @Getter
    @AllArgsConstructor
    public static class Res {
        private Long id;
    }
}
