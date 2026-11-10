package es.madrid.redmetro.jx.dao;

import java.util.List;

import es.madrid.redmetro.jx.exception.RedMetroException;
import es.madrid.redmetro.jx.model.Tren;

public interface ITren {
	
	void insertarTren(Tren tren) throws RedMetroException;
	List<Tren> obtenerTodosLosTrenes() throws RedMetroException;

}
