package es.madrid.redmetro.jx.model;

public class Cochera {

	private int codigoCochera;
	private String nombre;
	private String direccion;
	private int deposito;

	public int getCodigoCochera() {
		return codigoCochera;
	}

	public void setCodigoCochera(int codigoCochera) {
		this.codigoCochera = codigoCochera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getDeposito() {
		return deposito;
	}

	public void setDeposito(int deposito) {
		this.deposito = deposito;
	}

	@Override
	public String toString() {
		return "Cochera [codigoCochera=" + codigoCochera + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", deposito=" + deposito + "]";
	}

}
