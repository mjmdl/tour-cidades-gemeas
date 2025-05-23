package edu.uniuv.grupo2.tourgemeas.entities;

import edu.uniuv.grupo2.tourgemeas.spothint.SpotHint;
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
@Table(name = "usuariodicas")
public class UserSpotHint {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuariodicasid")
	@SequenceGenerator(name = "sq_usuariodicasid", sequenceName = "sq_usuariodicasid", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "usuarioid", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "pontoturisticodicasid", nullable = false)
	private SpotHint spotHint;
}
