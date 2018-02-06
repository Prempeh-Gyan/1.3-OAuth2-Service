package com.premps.oauth2service.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.premps.oauth2service.model.CustomGrantedAuthority;
import com.premps.oauth2service.model.User;
import com.premps.oauth2service.repository.UserRepository;
import com.premps.oauth2service.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user " + username);
		} else {
			return new CustomUserDetails(user);
		}
	}

	private final static class CustomUserDetails extends User implements UserDetails {

		private CustomUserDetails(User user) {
			super(user);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {

			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			Set<CustomGrantedAuthority> privileges = new HashSet<>(super.getRole().getPrivileges());

			for (CustomGrantedAuthority privilege : privileges) {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + privilege.getName()));
			}
			return authorities;
		}

		@Override
		public String getFirstName() {
			return super.getFirstName();
		}

		@Override
		public String getLastName() {
			return super.getLastName();
		}

		@Override
		public String getUsername() {
			return super.getEmail();
		}

		@Override
		public boolean isAccountNonExpired() {
			return super.getIsAccountNonExpired();
		}

		@Override
		public boolean isAccountNonLocked() {
			return super.getIsAccountNonLocked();
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return super.getIsCredentialsNonExpired();
		}

		@Override
		public boolean isEnabled() {
			return super.getIsEnabled();
		}

		private static final long serialVersionUID = 5639683223516504866L;
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User findOne(long id) {
		return userRepository.findOne(id);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
