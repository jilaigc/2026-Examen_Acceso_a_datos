package es.madrid.redmetro.rmr.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import es.madrid.redmetro.rmr.exception.RedMetroException;

public class GestorConexionBD {
	
	public static Connection getConexion() throws RedMetroException {
		String urlBaseDatos = GestorConfiguracion.obtenerValor("url");
		String usuarioBaseDatos = GestorConfiguracion.obtenerValor("usuario");
		String passBaseDatos = GestorConfiguracion.obtenerValor("password");
		
		try {
			return DriverManager.getConnection(urlBaseDatos, usuarioBaseDatos, passBaseDatos);
		} catch (SQLException e) {
			throw new RedMetroException ("No se ha podido conectar con la BBDD: " + e);
		}
	}

}
