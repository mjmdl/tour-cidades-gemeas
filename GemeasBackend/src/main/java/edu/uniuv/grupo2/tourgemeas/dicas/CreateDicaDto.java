package edu.uniuv.grupo2.tourgemeas.dicas;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CreateDicaDto {
    @Getter
    public static class Req {
        public static final int DESC_MIN = 5;
        public static final int DESC_MAX = 500;

        @NotBlank
        @Length(min = DESC_MIN, max = DESC_MAX)
        private String descricao;

        @NotNull
        @PositiveOrZero
        private Long pontuacao;

        @NotNull
        private Long pontoTuristicoId;
    }

    @Getter
    @AllArgsConstructor
    public static class Res {
        private Long id;
    }
}
