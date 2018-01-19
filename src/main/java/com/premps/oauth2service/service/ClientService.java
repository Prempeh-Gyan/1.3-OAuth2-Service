package com.premps.oauth2service.service;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import com.premps.oauth2service.model.oauth_client_details;

@Service
public interface ClientService extends ClientDetailsService {
	
	oauth_client_details findByClientId(String clientId);

	void save(oauth_client_details client);
}