package edu.uniuv.grupo2.tourgemeas.entities;

import java.math.BigDecimal;

import edu.uniuv.grupo2.tourgemeas.partner.Partner;
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
@Table(name = "patrocinios")
public class Sponsorship {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_patrociniosid")
	@SequenceGenerator(name = "sq_patrociniosid", sequenceName = "sq_patrociniosid", allocationSize = 1)
	private Long id;

	@Column(name = "valorporvisita", nullable = true)
	private BigDecimal visitValue;

	@ManyToOne
	@JoinColumn(name = "parceriaid", nullable = false)
	private Partner partner;

	@ManyToOne
	@JoinColumn(name = "eventoid", nullable = false)
	private Event event;

	@ManyToOne
	@JoinColumn(name = "pontoturisticoid", nullable = false)
	private Spot spot;
}
