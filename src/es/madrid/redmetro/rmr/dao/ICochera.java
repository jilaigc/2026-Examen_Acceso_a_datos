package es.madrid.redmetro.rmr.dao;

import es.madrid.redmetro.rmr.exception.RedMetroException;
import es.madrid.redmetro.rmr.model.Cochera;

public interface ICochera {
	
	Cochera obtenerCocheraPorCodigo (int codigoCochera) throws RedMetroException;
	void insertarCochera (Cochera cochera) throws RedMetroException;
	void actualizarCochera (Cochera cochera) throws RedMetroException;
	void borrarCochera (int codigoCochera) throws RedMetroException;

}