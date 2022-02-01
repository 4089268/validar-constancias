package com.nerus.springboot.validardocs.app.models;

import java.util.Map;
import java.util.HashMap;

public class Documento implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fecha;
	private String dependencia;
	private String sucursal;
	private String tipoDocumento;
	private String contrato;
	private String razonSocial;
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDependencia() {
		return dependencia;
	}
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public Documento() {
		this.fecha = "";
		this.dependencia = "";
		this.sucursal = "";
		this.tipoDocumento = "";
		this.contrato = "";
		this.razonSocial = "";
	}
	
	public Map<String,String> Validar(){
		var results = new HashMap<String,String>();
		
		if(fecha == null || fecha.trim().length() <= 1) {
			results.put("fecha", "Ingrese una fecha valida.");
		}
		
		if(dependencia == null || dependencia.trim().length() <= 1) {
			results.put("dependencia", "Seleccione una dependencia.");
		}
		if(sucursal == null || sucursal.trim().length() <= 1) {
			results.put("sucursal", "Seleccione una sucursal.");
		}
		if(tipoDocumento == null || tipoDocumento.trim().length() <= 1) {
			results.put("tipoDocumento", "Seleccion un tipo de documento.");
		}
		if(contrato == null || contrato.trim().length() <= 1) {
			results.put("contrato", "Escriba un contrato valido.");
		}
		if(razonSocial == null || razonSocial.trim().length() <= 1) {
			results.put("razonSocial", "Ingrese una razon social valida.");
		}
		
		
		return results;
	}
	
}
