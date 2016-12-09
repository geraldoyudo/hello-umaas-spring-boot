package com.gerald.umaas.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index(){
		return "index";
	}
	@GetMapping("/home")
	public String home(){
		return "home";
	}
	@GetMapping("/admin")
	public String admin(){
		return "admin";
	}
	@GetMapping("/regular")
	public String regular(){
		return "regular";
	}
	
}
