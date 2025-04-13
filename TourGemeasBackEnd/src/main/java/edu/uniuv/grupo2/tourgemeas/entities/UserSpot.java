package edu.uniuv.grupo2.tourgemeas.entities;

import edu.uniuv.grupo2.tourgemeas.spot.Spot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pontoturisticousuario")
public class UserSpot {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pontoturisticousuarioid")
	@SequenceGenerator(name = "sq_pontoturisticousuarioid", sequenceName = "sq_pontoturisticousuarioid", allocationSize = 1)
	private Long id;

	@Column(name = "pontuacao", nullable = true)
	private Long score;

	@ManyToOne
	@JoinColumn(name = "usuarioid", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "pontoturisticoid", nullable = false)
	private Spot spot;
}
