package com.premps.oauth2service.service;

import com.premps.oauth2service.model.Role;

public interface RoleService {

	Role findOne(Long roleId);

	void saveRole(Role role);
}
