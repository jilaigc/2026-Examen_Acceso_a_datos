package es.madrid.redmetro.rmr.service;

import java.util.List;

import es.madrid.redmetro.rmr.dao.ITren;
import es.madrid.redmetro.rmr.dao.impl.TrenJDBC;
import es.madrid.redmetro.rmr.exception.RedMetroException;
import es.madrid.redmetro.rmr.model.Tren;

public class TrenService {
	
	private ITren trenDAO = new TrenJDBC();
	
	public void insertarTren (Tren tren) throws RedMetroException {
		trenDAO.insertarTren(tren);
	}
	
	public List<Tren> obtenerTodosLosTrenes() throws RedMetroException {
		return trenDAO.obtenerTodosLosTrenes();
	}

}
