package com.nerus.springboot.validardocs.app.services;

import org.springframework.stereotype.Service;

import com.nerus.springboot.validardocs.app.models.Documento;

@Service
public class GenerarCodigoService {
	
	public String generarCodigo(Documento documento) {
		return "**codigo***";
	}

}
