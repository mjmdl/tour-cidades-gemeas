package edu.uniuv.grupo2.tourgemeas.entities;

import java.time.OffsetDateTime;
import java.util.List;

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
@Table(name = "eventos")
public class Event {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_eventosid")
	@SequenceGenerator(name = "sq_eventosid", sequenceName = "sq_eventosid", allocationSize = 1)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String name;

	@Column(name = "datainicio", nullable = false)
	private OffsetDateTime startDate;

	@Column(name = "datafinal", nullable = false)
	private OffsetDateTime endDate;

	@OneToMany(mappedBy = "event")
	private List<EventPrize> prizes;
}
