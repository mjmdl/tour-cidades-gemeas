package edu.uniuv.grupo2.tourgemeas.entities;

import edu.uniuv.grupo2.tourgemeas.event.Event;
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
@Table(name = "usuariopontuacao")
public class UserScore {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuariopontuacaoid")
	@SequenceGenerator(name = "sq_usuariopontuacaoid", sequenceName = "sq_usuariopontuacaoid", allocationSize = 1)
	private Long id;

	@Column(name = "pontuacao", nullable = false)
	private Long score;

	@ManyToOne
	@JoinColumn(name = "usuarioid", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "eventoid", nullable = false)
	private Event event;
}
