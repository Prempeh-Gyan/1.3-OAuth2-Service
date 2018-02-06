
package com.premps.oauth2service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.premps.oauth2service.model.CustomGrantedAuthority;

@Repository
public interface PrivilegeRepository extends JpaRepository<CustomGrantedAuthority, Long> {

	@Query("SELECT p FROM CustomGrantedAuthority p WHERE p.name = :name")
	public CustomGrantedAuthority fineOneByName(@Param("name") String name);
}
