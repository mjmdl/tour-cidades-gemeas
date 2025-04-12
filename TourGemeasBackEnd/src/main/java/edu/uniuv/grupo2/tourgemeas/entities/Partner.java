package edu.uniuv.grupo2.tourgemeas.entities;

import java.math.BigDecimal;

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
@Table(name = "parcerias")
public class Partner {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_parceriasid")
	@SequenceGenerator(name = "sq_parceriasid", sequenceName = "sq_parceriasid", allocationSize = 1)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String name;

	@Column(name = "latitude", nullable = false)
	private BigDecimal latitude;

	@Column(name = "longitude", nullable = false)
	private BigDecimal longitude;

	@Column(name = "pontuacao", nullable = false)
	private Long score;

	@Column(name = "valorminimo", nullable = true)
	private BigDecimal minimumValue;

	@Column(name = "valormaximo", nullable = true)
	private BigDecimal maximumValue;
}
