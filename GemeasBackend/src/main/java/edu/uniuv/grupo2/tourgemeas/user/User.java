package edu.uniuv.grupo2.tourgemeas.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User implements UserDetails {
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		return List.of();
	}

	@Override
	public String getUsername() 
	{
		return email;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
    public boolean isAccountNonLocked() 
	{
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() 
	{
        return true;
    }

    @Override
    public boolean isEnabled() 
	{
        return true;
    }
}
