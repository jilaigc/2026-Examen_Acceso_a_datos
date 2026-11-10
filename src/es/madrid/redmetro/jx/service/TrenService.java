package es.madrid.redmetro.jx.service;

import java.util.List;

import es.madrid.redmetro.jx.dao.ITren;
import es.madrid.redmetro.jx.dao.impl.TrenJDBC;
import es.madrid.redmetro.jx.exception.RedMetroException;
import es.madrid.redmetro.jx.model.Tren;

public class TrenService {
	
	private ITren trenDAO = new TrenJDBC();
	
	public void insertarTren (Tren tren) throws RedMetroException {
		trenDAO.insertarTren(tren);
	}
	
	public List<Tren> obtenerTodosLosTrenes() throws RedMetroException {
		return trenDAO.obtenerTodosLosTrenes();
	}

}
