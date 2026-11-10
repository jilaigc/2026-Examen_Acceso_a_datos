package es.madrid.redmetro.jx.gestores;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import es.madrid.redmetro.jx.exception.RedMetroException;

public class GestorConfiguracion {
	
	private static final String RUTA_FICHERO_CONFIG = "config/app.properties";
	
	public static String obtenerValor (String clave) throws RedMetroException {
		Properties propiedadesConfig = new Properties();
		
		try (FileInputStream ficheroConfiguracion = new FileInputStream(RUTA_FICHERO_CONFIG)) {
			propiedadesConfig.load(ficheroConfiguracion);
			return propiedadesConfig.getProperty(clave);
		} catch (IOException e) {
			throw new RedMetroException ("No se puede leer el fichero de configuración: " + e);
		}
	}

}
