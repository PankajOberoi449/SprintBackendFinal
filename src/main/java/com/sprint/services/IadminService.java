package com.sprint.services;

import com.sprint.dto.LoginDTO;

public interface IadminService{

	LoginDTO adminValidation(String email, String password);
	
}