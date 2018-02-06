
package com.premps.oauth2service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.premps.oauth2service.model.oauth_client_details;

@Repository
public interface ClientRepository extends JpaRepository<oauth_client_details, Long> {

	@Query("SELECT c FROM oauth_client_details c WHERE c.client_id = :client_id")
	oauth_client_details findByClientId(@Param("client_id") String client_id);
}