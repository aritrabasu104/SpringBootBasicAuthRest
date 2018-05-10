package com.wavelet.stockmovement.security.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	@GetMapping("/auth/login")
	public HttpStatus login() {
		return HttpStatus.OK;
	}
	@PostMapping("/auth/logout")
	public HttpStatus logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){ 
		new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		//return "redirect:/login";
		return HttpStatus.OK;
	}
	
}

