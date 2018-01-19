package com.premps.oauth2service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.jboss.aerogear.security.otp.api.Base32;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class oauth_client_details {
	
	public oauth_client_details(oauth_client_details client) {
		this.id = client.getId();
		this.client_id = client.getClient_id();
		this.resource_ids = client.getResource_ids();
		this.client_secret = client.getClient_secret();
		this.scopes = client.getScopes();
		this.authorized_grant_types = client.getAuthorized_grant_types();
		this.web_server_redirect_uri = client.getWeb_server_redirect_uri();
		this.clientAuthorities = client.getClientAuthorities();
		this.access_token_validity = client.getAccess_token_validity();
		this.refresh_token_validity = client.getRefresh_token_validity();
		this.additional_information = client.getAdditional_information();
		this.autoapprove = client.getAutoapprove();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true, nullable = false)
	private String client_id;

	private String resource_ids;

	private String client_secret = Base32.random();

	private String scopes;

	private String authorized_grant_types;

	private String web_server_redirect_uri;

	private String clientAuthorities;

	private Integer access_token_validity;

	private Integer refresh_token_validity;

	private String additional_information;

	private String autoapprove;
	
	private Boolean isScoped;
	
	private Boolean isSecretRequired;
}
