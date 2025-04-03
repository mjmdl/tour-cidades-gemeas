package edu.uniuv.grupo2.tourgemeas.parceiros;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Parcerias", schema = "Tour")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parceiros {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_parceriasid")
    @SequenceGenerator(name = "sq_parceriasid", sequenceName = "tour.sq_parceriasid", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "posicao", nullable = false)
    private String posicao; 

    @Column(name = "pontuacao", nullable = false)
    private Long pontuacao;

    @Column(name = "valorminimo")
    private Double valorMinimo;

    @Column(name = "valormaximo")
    private Double valorMaximo;
}
