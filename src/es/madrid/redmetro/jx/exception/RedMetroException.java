package es.madrid.redmetro.jx.exception;

public class RedMetroException extends Exception { // crea mi propia excepción. lo hereda de la de java
	
	// este primer constructor crea la excepción sol ocon un texto.
	// lo uso cuando detecto yo el problema
	public RedMetroException (String mensaje) {
		super(mensaje);
	}
	
	// este sirve para crear la excepción con un texto y el error original.
	// lo uso cuando java me da un error (como el sqlexception) y lo convierto
	// en mi excepción sin perder lo que pasó
	public RedMetroException (String mensaje, Throwable causa) {
		super (mensaje, causa);
	}
	
	// super llama al constructor de exception y le pasa el mensaje y la causa.
	// exception lo guarda.

}
