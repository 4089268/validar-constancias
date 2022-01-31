package com.nerus.springboot.validardocs.app.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		var documento = new Documento();
		  
		LocalDate now = LocalDate.now();
		var dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		documento.setContrato("123666789");
		documento.setFecha(dateFormat.format(now));
		documento.setRazonSocial("Juan Salvador Rangel Almaguer");
		
		model.addAttribute("titulo", "Generar codigo");
		model.addAttribute("documento", documento);
		return "generar-form";
	}
	
	@PostMapping({"/generar"})
	public String GenerarCodigo_Post(Documento doc,  Model model) {
		model.addAttribute("titulo", "Generar codigo");
		model.addAttribute("documento", doc);
		
		//*** Validar documento
		var result = doc.Validar();
		if(!result.isEmpty()) {
			model.addAttribute("errores", result);
			return "generar-form";
		}else {
			return "mostrar-qr";
		}
	}
	
	@GetMapping({"/codigo"})
	public String Test_MostrarCodigo(Model model) {
		model.addAttribute("titulo", "Codigo generado");
		return "mostrar-qr";
	}

	
	
	@ModelAttribute("dependencias")
	public List<String> ObtenerDependencias(){
		var result = new ArrayList<String>();
		result.add("CAEV");
		return result;
	}
	@ModelAttribute("sucursales")
	public List<String> ObtenerSucursales(){
		var result = new ArrayList<String>();
		result.add("Othon P Blanco");
		result.add("Tulum");
		result.add("Felipe Carrillo Puerto");
		result.add("Bacalar");
		result.add("Cozumel");
		return result;
	}
	@ModelAttribute("constancias")
	public List<String> ObtenerTiposConstancias(){
		var result = new ArrayList<String>();
		result.add("Constancia de NO Adeudo");
		result.add("Constancia de NO Contrato");
		result.add("Constancia de A");
		result.add("Constancia de B");
		result.add("Constancia de C");
		return result;
	}
}
