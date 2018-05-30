package com.final_year.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.final_year.app.domain.Authorities;



@Repository

public interface AuthoritiesDao extends CrudRepository<Authorities, Long>{

	Authorities findAuthoritiesByusername(String username);
}
