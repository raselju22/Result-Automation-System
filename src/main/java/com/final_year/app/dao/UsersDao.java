package com.final_year.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.final_year.app.domain.Users;



@Repository

public interface UsersDao extends CrudRepository<Users, Long>{

	Users findUserByusername(String username);
}
