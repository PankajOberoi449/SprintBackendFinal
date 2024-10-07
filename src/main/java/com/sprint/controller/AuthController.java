package com.sprint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.dto.LoginDTO;
import com.sprint.services.IadminService;
import com.sprint.services.IcustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private IadminService adminService;

	@Autowired
	private IcustomerService customerService;

	@GetMapping("/admin") // Better to specify an endpoint
	public ResponseEntity<LoginDTO> adminValidation(@RequestParam String email, @RequestParam String password) {
		logger.info("Checking admin existence for email: {}", email);

		LoginDTO adminDetails = adminService.adminValidation(email, password);
		if (adminDetails != null) {
			logger.info("Admin found: {}", adminDetails.getEmail());
			return ResponseEntity.ok(adminDetails);
		} else {
			logger.warn("Admin not found for email: {}", email);
			return ResponseEntity.ok(null); // Return 200 OK with null body if admin not found
		}
	}

	@GetMapping("/customer")
	public ResponseEntity<LoginDTO> customerValidation(@RequestParam String email, @RequestParam String password) {
		LoginDTO customerDetails = customerService.customerValidation(email,password);
		if (customerDetails != null) {
			return ResponseEntity.ok(customerDetails);
		} else {
			return ResponseEntity.ok(null); // Return 200 OK with null body if customer not found
		}
	}
}
