package com.nerus.springboot.validardocs.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nerus.springboot.validardocs.app.models.Documento;


@Controller
public class DefaultController {
	
	@GetMapping({"","/","/index","/home"})
	public String Index(Model model) {
		model.addAttribute("titulo", "Hola mundo, soy una pagina de springboot ñá!");
		return "index";
	}
	
	@GetMapping({"/generar"})
	public String GenerarCodigo(Model model) {
		model.addAttribute("titulo", "Generar codigo");
		return "generar-form";
	}
	
	@PostMapping({"/generar"})
	public String GenerarCodigo_Post(Model model) {
		
		var documento = new Documento();
		documento.setContrato(123666789);
		documento.setRazonSocial("Juan Salvador Rangel Almaguer");
		
		model.addAttribute("titulo", "Generar codigo");
		model.addAttribute("documento", documento);
		return "generar-form";
	}

}
