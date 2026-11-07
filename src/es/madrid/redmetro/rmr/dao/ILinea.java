package es.madrid.redmetro.rmr.dao;

import es.madrid.redmetro.rmr.exception.RedMetroException;
import es.madrid.redmetro.rmr.model.Linea;

public interface ILinea {
	
	Linea obtenerLineaPorCodigo (int codigoLinea) throws RedMetroException;

}
