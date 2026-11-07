package es.madrid.redmetro.rmr.model;

import java.time.LocalDate;

public class Tren {

	private int codigoTren;
	private String modelo;
	private String empresaConstructora;
	private LocalDate fechaIncorporacion;
	private Cochera cochera;
	private Linea linea;

	public int getCodigoTren() {
		return codigoTren;
	}

	public void setCodigoTren(int codigoTren) {
		this.codigoTren = codigoTren;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getEmpresaConstructora() {
		return empresaConstructora;
	}

	public void setEmpresaConstructora(String empresaConstructora) {
		this.empresaConstructora = empresaConstructora;
	}

	public LocalDate getFechaIncorporacion() {
		return fechaIncorporacion;
	}

	public void setFechaIncorporacion(LocalDate fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}

	public Cochera getCochera() {
		return cochera;
	}

	public void setCochera(Cochera cochera) {
		this.cochera = cochera;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	@Override
	public String toString() {
		return "Tren [codigoTren=" + codigoTren + ", modelo=" + modelo + ", empresaConstructora=" + empresaConstructora
				+ ", fechaIncorporacion=" + fechaIncorporacion + ", cochera=" + cochera + ", linea=" + linea + "]";
	}
	
	

}
