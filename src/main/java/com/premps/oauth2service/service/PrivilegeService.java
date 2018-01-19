package com.premps.oauth2service.service;

import com.premps.oauth2service.model.CustomGrantedAuthority;

public interface PrivilegeService {

	CustomGrantedAuthority fineOneByName(String name);

	void savePrivilege(CustomGrantedAuthority privilege);
}
