package com.premps.oauth2service.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.premps.oauth2service.model.oauth_client_details;
import com.premps.oauth2service.repository.ClientRepository;
import com.premps.oauth2service.service.ClientService;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

		oauth_client_details client = clientRepository.findByClientId(clientId);

		if (client == null) {
			throw new UsernameNotFoundException("Could not find client with id " + clientId);
		} else {
			return new CustomClientDetails(client);
		}
	}

	private final static class CustomClientDetails extends oauth_client_details implements ClientDetails {

		private static final long serialVersionUID = 8250904223326796879L;

		public CustomClientDetails(oauth_client_details client) {
			super(client);
		}

		@Override
		public Set<String> getResourceIds() {

			return extractedElementsOfThisString(super.getResource_ids());
		}

		@Override
		public String getClientSecret() {
			return super.getClient_secret();
		}

		@Override
		public Set<String> getAuthorizedGrantTypes() {

			return extractedElementsOfThisString(super.getAuthorized_grant_types());
		}

		@Override
		public Set<String> getRegisteredRedirectUri() {

			return extractedElementsOfThisString(super.getWeb_server_redirect_uri());
		}

		@Override
		public Collection<GrantedAuthority> getAuthorities() {

			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

			Set<String> privileges = extractedElementsOfThisString(super.getClientAuthorities());

			privileges.forEach((privilege -> {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + privilege));
			}));

			return authorities;
		}

		@Override
		public Integer getAccessTokenValiditySeconds() {
			return super.getAccess_token_validity();
		}

		@Override
		public Integer getRefreshTokenValiditySeconds() {
			return super.getRefresh_token_validity();
		}

		@Override
		public boolean isAutoApprove(String scope) {
			return super.getScopes().equalsIgnoreCase("true") ? true : false;
		}

		private Set<String> extractedElementsOfThisString(String data) {

			Set<String> setOfElements = new HashSet<>();

			String compressedData = data.replaceAll("\\s+", "");

			StringTokenizer stringTokenizer = new StringTokenizer(compressedData, ",");

			while (stringTokenizer.hasMoreTokens()) {
				String currentToken = stringTokenizer.nextToken().toLowerCase();
				setOfElements.add(currentToken);
			}

			return setOfElements;
		}

		@Override
		public Map<String, Object> getAdditionalInformation() {
			return null;
		}

		@Override
		public String getClientId() {
			return super.getClient_id();
		}

		@Override
		public boolean isSecretRequired() {
			return super.getIsSecretRequired();
		}

		@Override
		public boolean isScoped() {
			return super.getIsScoped();
		}

		@Override
		public Set<String> getScope() {
			return extractedElementsOfThisString(super.getScopes());
		}
	}

	@Override
	public oauth_client_details findByClientId(String clientId) {
		return clientRepository.findByClientId(clientId);
	}

	@Override
	public void save(oauth_client_details client) {
		clientRepository.save(client);
	}

}
