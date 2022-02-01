package com.nerus.springboot.validardocs.app.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nerus.springboot.validardocs.app.models.Documento;
import com.nerus.springboot.validardocs.app.services.CodificadorDocs;
import com.nerus.springboot.validardocs.app.services.GenerarCodigoService;


@Controller
public class DefaultController {
	
	@Autowired
	GenerarCodigoService generadorService;
	
	@Autowired
	CodificadorDocs codificador;
	
	
	@GetMapping({"","/","/index","/home"})
	public String Index(Model model) {
		model.addAttribute("titulo", "Hola mundo, soy una pagina de springboot ñá!");
		return "index";
	}
	
	@GetMapping({"/generar"})
	public String ObtenerFormulario(Model model) {
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
	public String GenerarCodigo(Documento doc,  Model model) {
		
		model.addAttribute("titulo", "Generar codigo");
		model.addAttribute("documento", doc);
		
		//*** Validar documento
		var result = doc.Validar();
		if(!result.isEmpty()) {
			model.addAttribute("errores", result);
			return "generar-form";
		}
		
		//*** Serializar documento
		String resultDoc = codificador.serializarDocumento(doc);
		
		try {
			String encriptText = codificador.codificarTexto(resultDoc);
		
			//*** Generar codigo QR
			var urlValidar = "http://127.0.0.1:8080/validar?c=".concat(encriptText);
			var imageb64 = generadorService.generarCodigo(urlValidar, 300);
			model.addAttribute("imageB64", imageb64);
			model.addAttribute("url", urlValidar);
			return "mostrar-qr";
			
		}catch(Exception err){
			return "error";
		}
		
	}
	
	@GetMapping({"/validar"})
	public String ValidarCodigo(@RequestParam("c") String codigo, Model model) {
		model.addAttribute("titulo", "Codigo validado");
		model.addAttribute("codigo", codigo);
		
		//*** validar documento
		try {
			var jsonText = codificador.decodificarTexto(codigo);
			Documento documento = codificador.serializarDocumento( jsonText );
			model.addAttribute("validado", true);
			model.addAttribute("documento", documento);
			
		}catch(Exception err) {
			System.out.println("Error:".concat(err.getMessage()));
			model.addAttribute("validado", false);
		}
		return "result-doc";
	}
	
	@GetMapping({"/codigo"})
	public String Test_MostrarCodigo(Model model) {
		model.addAttribute("titulo", "Pruebas Generador Codigo QR");
		try {
			var imageb64 = generadorService.generarCodigo("https://www.github.com/4089268", 300);
			model.addAttribute("imageB64", imageb64);
			return "mostrar-qr";				
		}catch(Exception err){
			return "error";
		}
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
