package edu.uniuv.grupo2.tourgemeas.entities;

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
@Table(name = "usuario")
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuarioid")
	@SequenceGenerator(name = "sq_usuarioid", sequenceName = "sq_usuarioid", allocationSize = 1)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "senha", nullable = false)
	private String password;
	
	@Column(name = "admin", nullable = false)
	private Boolean admin;

	@OneToMany(mappedBy = "user")
	private List<UserSpotHint> spotHints;
}
