package edu.uniuv.grupo2.tourgemeas.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario", schema = "tour")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuarioid")
	@SequenceGenerator(name = "sq_usuarioid", sequenceName = "tour.sq_usuarioid", allocationSize = 1)
	@Column(name = "id")
	Long id;

	@Column(name = "nome")
	String name;

	@Column(name = "email")
	String email;

	@Column(name = "senha")
	String password;

	@Column(name = "admin")
	Boolean admin;
}
