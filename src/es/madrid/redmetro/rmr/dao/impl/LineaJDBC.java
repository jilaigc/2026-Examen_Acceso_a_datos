package es.madrid.redmetro.rmr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.madrid.redmetro.rmr.dao.ILinea;
import es.madrid.redmetro.rmr.exception.RedMetroException;
import es.madrid.redmetro.rmr.gestores.GestorConexionBD;
import es.madrid.redmetro.rmr.model.Color;
import es.madrid.redmetro.rmr.model.Linea;

public class LineaJDBC implements ILinea {

	@Override
	public Linea obtenerLineaPorCodigo(int codigoLinea) throws RedMetroException {

		Linea lineaEncontrada = null;
		String consulta = "SELECT l.cod_linea, l.nombre_corto, l.nombre_largo, l.kilometros, "
				+ "c.cod_color, c.nombre, c.cod_hexadecimal "
				+ "FROM T_LINEA l JOIN T_COLOR c ON l.cod_color = c.cod_color " + "WHERE l.cod_linea = ?";

		try (Connection conexion = GestorConexionBD.getConexion();
				PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

			sentencia.setInt(1, codigoLinea);
			ResultSet resultado = sentencia.executeQuery();
			
			if (resultado.next()) {
				lineaEncontrada = new Linea();
				lineaEncontrada.setCodigoLinea(resultado.getInt("cod_linea"));
				lineaEncontrada.setNombreCorto(resultado.getString("nombre_corto"));
				lineaEncontrada.setNombreLargo(resultado.getString("nombre_largo"));
				lineaEncontrada.setKilometros(resultado.getFloat("kilometros"));
				
				Color colorLinea = new Color();
				colorLinea.setCodigoColor(resultado.getInt("cod_color"));
				colorLinea.setNombre(resultado.getString("nombre"));
				colorLinea.setCodigoHexadecimal(resultado.getString("cod_hexadecimal"));
				
				lineaEncontrada.setColor(colorLinea);				
			}

		} catch (SQLException e) {
			throw new RedMetroException("Error al obtener la línea: " + codigoLinea + " por: " + e);
		}
		
		return lineaEncontrada;
	}
}
