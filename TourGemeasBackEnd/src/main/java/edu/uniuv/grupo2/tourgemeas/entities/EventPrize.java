package edu.uniuv.grupo2.tourgemeas.entities;

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
@Table(name = "eventospremiacao")
public class EventPrize {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_eventospremiacaoid")
	@SequenceGenerator(name = "sq_eventospremiacaoid", sequenceName = "sq_eventospremiacaoid", allocationSize = 1)
	private Long id;

	@Column(name = "posicao", nullable = false)
	private Long position;

	@Column(name = "premio", nullable = false)
	private String prize;

	@ManyToOne
	@JoinColumn(name = "eventoid")
	private Event event;
}
