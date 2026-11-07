package es.madrid.redmetro.rmr.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.madrid.redmetro.rmr.dao.ITren;
import es.madrid.redmetro.rmr.exception.RedMetroException;
import es.madrid.redmetro.rmr.gestores.GestorConexionBD;
import es.madrid.redmetro.rmr.model.Cochera;
import es.madrid.redmetro.rmr.model.Color;
import es.madrid.redmetro.rmr.model.Linea;
import es.madrid.redmetro.rmr.model.Tren;

public class TrenJDBC implements ITren {

	@Override
	public void insertarTren(Tren tren) throws RedMetroException {

		String consulta = "INSERT INTO T_TREN (cod_tren, modelo, empresa_constructora, fecha_incorporacion, cod_cochera, cod_linea) "
						  + "VALUES (?, ?, ?, ?, ?, ?)";
		
		try (Connection conexion = GestorConexionBD.getConexion();
			 PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
			
			sentencia.setInt(1, tren.getCodigoTren());
			sentencia.setString(2, tren.getModelo());
			sentencia.setString(3, tren.getEmpresaConstructora());
			sentencia.setDate(4, Date.valueOf(tren.getFechaIncorporacion()));
			sentencia.setInt(5,  tren.getCochera().getCodigoCochera());
			sentencia.setInt(6, tren.getLinea().getCodigoLinea());
			sentencia.executeUpdate();
			
		} catch (SQLException e) {
			throw new RedMetroException("Error al insertar el tren: " + tren.getCodigoTren(), e);
		}
		
	}

	@Override
	public List<Tren> obtenerTodosLosTrenes() throws RedMetroException {
		List<Tren> listaTrenes = new ArrayList<>();
		
		String consulta = "SELECT t.cod_tren, t.modelo, t.empresa_constructora, t.fecha_incorporacion, t.cod_cochera, "
			                + "l.cod_linea, l.nombre_largo, "
			                + "c.cod_color, c.nombre, c.cod_hexadecimal "
			                + "FROM T_TREN t "
			                + "JOIN T_LINEA l ON t.cod_linea = l.cod_linea "
			                + "JOIN T_COLOR c ON l.cod_color = c.cod_color";
		
		try (Connection conexion = GestorConexionBD.getConexion();
			 PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
			
			ResultSet resultado = sentencia.executeQuery();
			
			while (resultado.next()) {
				Tren trenLeido = new Tren();
				trenLeido.setCodigoTren(resultado.getInt("cod_tren"));
				trenLeido.setModelo(resultado.getString("modelo"));
				trenLeido.setEmpresaConstructora(resultado.getString("empresa_constructora"));
				trenLeido.setFechaIncorporacion(resultado.getDate("fecha_incorporacion").toLocalDate());
				
				Cochera cocheraDelTren = new Cochera();
				cocheraDelTren.setCodigoCochera(resultado.getInt("cod_cochera"));
				trenLeido.setCochera(cocheraDelTren);
				
				Linea lineaDelTren = new Linea();
				lineaDelTren.setCodigoLinea(resultado.getInt("cod_linea"));
				lineaDelTren.setNombreLargo(resultado.getString("nombre_largo"));
				
				Color colorLinea = new Color();
				colorLinea.setCodigoColor(resultado.getInt("cod_color"));
				colorLinea.setNombre(resultado.getString("nombre"));
				colorLinea.setCodigoHexadecimal(resultado.getString("cod_hexadecimal"));
				
				lineaDelTren.setColor(colorLinea);
				trenLeido.setLinea(lineaDelTren);
				
				listaTrenes.add(trenLeido);
				
			}
		} catch (SQLException e) {
			throw new RedMetroException ("Error al obtener la lista de trenes", e);
		}
		
		return listaTrenes;
		
	}

}
