package com.final_year.app.service;

import com.final_year.app.domain.Authorities;

public interface AuthoritiesService {

	public void addAuthorities(Authorities authorities);
	
	Authorities findAuthoritiesByusername(String username);
}
