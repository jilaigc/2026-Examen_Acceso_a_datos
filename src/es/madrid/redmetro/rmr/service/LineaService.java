package es.madrid.redmetro.rmr.service;

import es.madrid.redmetro.rmr.dao.ILinea;
import es.madrid.redmetro.rmr.dao.impl.LineaJDBC;
import es.madrid.redmetro.rmr.exception.RedMetroException;
import es.madrid.redmetro.rmr.model.Linea;

public class LineaService {
	
	private ILinea lineaDAO = new LineaJDBC();
	
	public Linea obtenerLineaConColor (int codigoLinea) throws RedMetroException {
		return lineaDAO.obtenerLineaPorCodigo(codigoLinea);
	}

}
