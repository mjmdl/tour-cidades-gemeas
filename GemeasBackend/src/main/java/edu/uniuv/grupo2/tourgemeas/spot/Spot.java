package edu.uniuv.grupo2.tourgemeas.spot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pontoturistico", schema = "tour")
public class Spot {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pontoturisticoid")
	@SequenceGenerator(name = "sq_pontoturisticoid", sequenceName = "tour.sq_pontoturisticoid", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome")
	private String name;

	@Column(name = "pontuacao")
	private Long score;
}
