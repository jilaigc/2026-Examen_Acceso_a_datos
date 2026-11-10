package es.madrid.redmetro.jx.dao;

import es.madrid.redmetro.jx.exception.RedMetroException;
import es.madrid.redmetro.jx.model.Cochera;

public interface ICochera {
	
	Cochera obtenerCocheraPorCodigo (int codigoCochera) throws RedMetroException;
	void insertarCochera (Cochera cochera) throws RedMetroException;
	void actualizarCochera (Cochera cochera) throws RedMetroException;
	void borrarCochera (int codigoCochera) throws RedMetroException;

}