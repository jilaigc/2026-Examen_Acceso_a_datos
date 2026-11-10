package es.madrid.redmetro.jx.service;

import es.madrid.redmetro.jx.dao.ILinea;
import es.madrid.redmetro.jx.dao.impl.LineaJDBC;
import es.madrid.redmetro.jx.exception.RedMetroException;
import es.madrid.redmetro.jx.model.Linea;

public class LineaService {
	
	private ILinea lineaDAO = new LineaJDBC();
	
	public Linea obtenerLineaConColor (int codigoLinea) throws RedMetroException {
		return lineaDAO.obtenerLineaPorCodigo(codigoLinea);
	}

}
