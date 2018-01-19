package com.premps.oauth2service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true, nullable = false)
	private String email;
	
	private String firstName;
	
	private String lastName;

	private String password;

	private Boolean isEnabled = false;

	private Boolean isAccountNonExpired = false;

	private Boolean isAccountNonLocked = false;

	private Boolean isCredentialsNonExpired = false;

	@ManyToOne
	private Role role;

	public User(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.isEnabled = user.getIsEnabled();
		this.isAccountNonExpired = user.getIsAccountNonExpired();
		this.isAccountNonLocked = user.getIsAccountNonLocked();
		this.isCredentialsNonExpired = user.getIsCredentialsNonExpired();
		this.role = user.getRole();
	}
}
