package es.madrid.redmetro.rmr.dao;

import java.util.List;

import es.madrid.redmetro.rmr.exception.RedMetroException;
import es.madrid.redmetro.rmr.model.Tren;

public interface ITren {
	
	void insertarTren(Tren tren) throws RedMetroException;
	List<Tren> obtenerTodosLosTrenes() throws RedMetroException;

}
