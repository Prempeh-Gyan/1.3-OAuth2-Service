package com.premps.oauth2service.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.premps.oauth2service.model.User;
import com.premps.oauth2service.model.oauth_client_details;
import com.premps.oauth2service.service.ClientService;
import com.premps.oauth2service.service.RoleService;
import com.premps.oauth2service.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class Oauth2Controller {

	private final @NotNull UserService userService;
	private final @NotNull RoleService roleService;
	private final @NotNull ClientService clientService;

	@RequestMapping(value = { "/user" })
	public Map<String, Object> user(OAuth2Authentication oauth2Authentication) {

		Map<String, Object> userDetails = new HashMap<>();

		userDetails.put("user", oauth2Authentication.getPrincipal());
		
		userDetails.put("authorities",
				AuthorityUtils.authorityListToSet(oauth2Authentication.getUserAuthentication().getAuthorities()));

		return userDetails;
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/add-user" })
	public String addUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email, @RequestParam("password") String password) {

		User user = new User();

		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		user.setIsAccountNonExpired(true);
		user.setIsAccountNonLocked(true);
		user.setIsCredentialsNonExpired(true);
		user.setIsEnabled(true);

		if (isDuplicateMember(user, userService.findByEmail(user.getEmail()))) {
			return "Error: duplicate user!";
		}

		long roleId = 2l;

		user.setRole(roleService.findOne(roleId));

		userService.saveUser(user);

		log.info("User successfully added!");

		return "success!";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/add-client" })
	public String addClient(@RequestParam("client_id") String client_id,
			@RequestParam("resource_ids") String resource_ids, @RequestParam("client_secret") String client_secret,
			@RequestParam("scopes") String scopes,
			@RequestParam("authorized_grant_types") String authorized_grant_types,
			@RequestParam("web_server_redirect_uri") String web_server_redirect_uri,
			@RequestParam("authorities") String clientAuthorities,
			@RequestParam("access_token_validity") Integer access_token_validity,
			@RequestParam("refresh_token_validity") Integer refresh_token_validity,
			@RequestParam("additional_information") String additional_information,
			@RequestParam("autoapprove") String autoapprove, @RequestParam("isScoped") String isScoped,
			@RequestParam("isSecretRequired") String isSecretRequired) {

		oauth_client_details client = new oauth_client_details();

		client.setClient_id(client_id);
		client.setAccess_token_validity(access_token_validity);
		client.setAdditional_information(additional_information);
		client.setAuthorized_grant_types(authorized_grant_types);
		client.setAutoapprove(autoapprove);
		client.setClient_id(client_id);
		client.setClient_secret(client_secret);
		client.setClientAuthorities(clientAuthorities);
		client.setRefresh_token_validity(refresh_token_validity);
		client.setResource_ids(resource_ids);
		client.setScopes(scopes);
		client.setWeb_server_redirect_uri(web_server_redirect_uri);
		client.setIsScoped(isScoped.equalsIgnoreCase("True") ? true : false);
		client.setIsSecretRequired(isSecretRequired.equalsIgnoreCase("True") ? true : false);

		if (isDuplicateClient(client, clientService.findByClientId(client.getClient_id()))) {
			return "Error: duplicate client Id!";
		}

		clientService.save(client);

		log.info("Client successfully added!");

		return "success!";
	}

	private boolean isDuplicateClient(oauth_client_details client, oauth_client_details dbClient) {
		if (client != null && dbClient != null) {
			if (client.getClient_id().equalsIgnoreCase(dbClient.getClient_id())) {
				return true;
			}
		}
		return false;
	}

	private boolean isDuplicateMember(User user, User dbUser) {
		if (user != null && dbUser != null) {
			if (user.getFirstName().equalsIgnoreCase(dbUser.getFirstName())
					&& user.getLastName().equalsIgnoreCase(dbUser.getLastName())
					&& user.getEmail().equalsIgnoreCase(dbUser.getEmail())) {
				return true;
			}
		}
		return false;
	}

}
