package edu.uniuv.grupo2.tourgemeas.dicas;

import edu.uniuv.grupo2.tourgemeas.spot.Spot;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PontoTuristicoDicas", schema = "Tour")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pontoturisticodicasid")
    @SequenceGenerator(name = "sq_pontoturisticodicasid", sequenceName = "tour.sq_pontoturisticodicasid", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "descricao", nullable = false)
    private String descricao;
    
    @Column(name = "pontuacao", nullable = false)
    private Long pontuacao;
    
    @ManyToOne
    @JoinColumn(name = "pontoturisticoid", nullable = false)
    private Spot pontoTuristico;
}