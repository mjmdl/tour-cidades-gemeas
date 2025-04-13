package edu.uniuv.grupo2.tourgemeas.spot;

import java.math.BigDecimal;
import java.util.List;

import edu.uniuv.grupo2.tourgemeas.spothint.SpotHint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pontoturistico")
public class Spot {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pontoturisticoid")
	@SequenceGenerator(name = "sq_pontoturisticoid", sequenceName = "sq_pontoturisticoid", allocationSize = 1)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String name;

	@Column(name = "latitude", nullable = false)
	private BigDecimal latitude;

	@Column(name = "longitude", nullable = false)
	private BigDecimal longitude;

	@Column(name = "pontuacao", nullable = false)
	private Long score;

	@OneToMany(mappedBy = "spot")
	private List<SpotHint> hints;
}
