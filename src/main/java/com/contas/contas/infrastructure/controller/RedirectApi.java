package com.contas.contas.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectApi {
	
	@GetMapping("/")
	public String swagger() {
		return "redirect:swagger-ui.html";
	}

}
