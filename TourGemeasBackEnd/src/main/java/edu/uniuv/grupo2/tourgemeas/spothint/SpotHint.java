package edu.uniuv.grupo2.tourgemeas.spothint;

import java.util.List;

import edu.uniuv.grupo2.tourgemeas.entities.UserSpotHint;
import edu.uniuv.grupo2.tourgemeas.spot.Spot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pontoturisticodicas")
public class SpotHint {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pontoturisticodicasid")
	@SequenceGenerator(name = "sq_pontoturisticodicasid", sequenceName = "sq_pontoturisticodicasid", allocationSize = 1)
	private Long id;

	@Column(name = "descricao", nullable = false)
	private String description;

	@Column(name = "pontuacao", nullable = false)
	private Long score;

	@ManyToOne
	@JoinColumn(name = "pontoturisticoid", nullable = false)
	private Spot spot;

	@OneToMany(mappedBy = "spotHint")
	private List<UserSpotHint> hintedUsers;
}
