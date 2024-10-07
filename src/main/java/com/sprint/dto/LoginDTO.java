package com.sprint.dto;

import lombok.Data;

@Data
public class LoginDTO {
	private int id;
    private String email;
    private String name;
    
    public LoginDTO(int id,String email, String name) {
    	this.id = id;
        this.email = email;
        this.name = name;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    

    // Getters and Setters
}

