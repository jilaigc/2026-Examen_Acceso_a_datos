package es.madrid.redmetro.jx.dao;

import es.madrid.redmetro.jx.exception.RedMetroException;
import es.madrid.redmetro.jx.model.Linea;

public interface ILinea {
	
	Linea obtenerLineaPorCodigo (int codigoLinea) throws RedMetroException;

}
