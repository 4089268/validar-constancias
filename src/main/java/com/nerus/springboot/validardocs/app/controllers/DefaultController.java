package com.nerus.springboot.validardocs.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
	
	@GetMapping({"","/","/index","/home"})
	public String Index(Model model) {
		model.addAttribute("titulo", "Hola mundo, soy una pagina de springboot ñá!");
		return "index";
	}

}
