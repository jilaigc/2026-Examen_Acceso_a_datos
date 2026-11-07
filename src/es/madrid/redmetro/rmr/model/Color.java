package es.madrid.redmetro.rmr.model;

public class Color {

	private int codigoColor;
	private String nombre;
	private String codigoHexadecimal;

	public int getCodigoColor() {
		return codigoColor;
	}

	public void setCodigoColor(int codigoColor) {
		this.codigoColor = codigoColor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigoHexadecimal() {
		return codigoHexadecimal;
	}

	public void setCodigoHexadecimal(String codigoHexadecimal) {
		this.codigoHexadecimal = codigoHexadecimal;
	}

	@Override
	public String toString() {
		return "Color [codigoColor=" + codigoColor + ", nombre=" + nombre + ", codigoHexadecimal=" + codigoHexadecimal
				+ "]";
	}

}