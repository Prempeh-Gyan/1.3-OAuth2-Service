package com.premps.oauth2service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.premps.oauth2service.model.CustomGrantedAuthority;
import com.premps.oauth2service.repository.PrivilegeRepository;
import com.premps.oauth2service.service.PrivilegeService;

@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {

	private final PrivilegeRepository privilegeRepository;

	@Autowired
	public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository) {
		this.privilegeRepository = privilegeRepository;
	}

	@Override
	public void savePrivilege(CustomGrantedAuthority privilege) {
		privilegeRepository.save(privilege);
	}

	@Override
	public CustomGrantedAuthority fineOneByName(String name) {
		return privilegeRepository.fineOneByName(name);
	}
}
