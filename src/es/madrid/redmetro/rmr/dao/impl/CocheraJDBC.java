package es.madrid.redmetro.rmr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.madrid.redmetro.rmr.dao.ICochera;
import es.madrid.redmetro.rmr.exception.RedMetroException;
import es.madrid.redmetro.rmr.gestores.GestorConexionBD;
import es.madrid.redmetro.rmr.model.Cochera;

public class CocheraJDBC implements ICochera {

	@Override
	public Cochera obtenerCocheraPorCodigo(int codigoCochera) throws RedMetroException {

		Cochera cocheraEncontrada = null;
		String consulta = "SELECT cod_cochera, nombre, direccion, deposito FROM T_COCHERA WHERE cod_cochera = ?";
		
		try (Connection conexion = GestorConexionBD.getConexion();
			 PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
			
			sentencia.setInt(1, codigoCochera);
			ResultSet resultado = sentencia.executeQuery();
			
			if (resultado.next()) {
				cocheraEncontrada = new Cochera();
				cocheraEncontrada.setCodigoCochera(resultado.getInt("cod_cochera"));
				cocheraEncontrada.setNombre(resultado.getString("nombre"));
                cocheraEncontrada.setDireccion(resultado.getString("direccion"));
                cocheraEncontrada.setDeposito(resultado.getInt("deposito"));
			}
			
		} catch (SQLException e) {
			throw new RedMetroException ("Error al obtener la cochera: " + codigoCochera, e);
		}
		
		return cocheraEncontrada;
		
	}

	@Override
	public void insertarCochera(Cochera cochera) throws RedMetroException {
		
		String consulta = "INSERT INTO T_COCHERA (cod_cochera, nombre, direccion, deposito) VALUES (?, ?, ?, ?)";
		
		try (Connection conexion = GestorConexionBD.getConexion();
		     PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
			
			sentencia.setInt(1, cochera.getCodigoCochera());
			sentencia.setString(2, cochera.getNombre());
			sentencia.setString(3, cochera.getDireccion());
			sentencia.setInt(4, cochera.getDeposito());;
			sentencia.executeUpdate();
			
		} catch (SQLException e) {
			throw new RedMetroException ("Error al insertar la cochera: " + cochera.getCodigoCochera(), e);
		}
		
	}

	@Override
	public void actualizarCochera(Cochera cochera) throws RedMetroException {
		String consulta = "UPDATE T_COCHERA SET nombre = ?, direccion = ?, deposito = ? WHERE cod_cochera = ?";
		
		try (Connection conexion = GestorConexionBD.getConexion();
			 PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
			
			sentencia.setString(1, cochera.getNombre());
			sentencia.setString(2, cochera.getDireccion());
			sentencia.setInt(3, cochera.getDeposito());
			sentencia.setInt(4, cochera.getCodigoCochera());
			sentencia.executeUpdate();
			
		} catch (SQLException e) {
			throw new RedMetroException ("Error al actualizar la cochera: " + cochera.getCodigoCochera(), e);
		}
		
	}

	@Override
	public void borrarCochera(int codigoCochera) throws RedMetroException {

		String consulta = "DELETE FROM T_COCHERA WHERE cod_cochera = ?";
		
		try (Connection conexion = GestorConexionBD.getConexion();
			 PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
			
			sentencia.setInt(1, codigoCochera);
			sentencia.executeUpdate();
			
		} catch (SQLException e) {
			throw new RedMetroException ("Error al borrar la cochera: " + codigoCochera, e);
		}
		
	}
}