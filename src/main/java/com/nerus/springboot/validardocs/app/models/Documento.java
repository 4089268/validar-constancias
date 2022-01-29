package com.nerus.springboot.validardocs.app.models;

public class Documento {
	private String fecha;
	private String dependencia;
	private String sucursal;
	private String tipoDocumento;
	private int contrato;
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
	public int getContrato() {
		return contrato;
	}
	public void setContrato(int contrato) {
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
		this.contrato = 0;
		this.razonSocial = "";
	}

}
