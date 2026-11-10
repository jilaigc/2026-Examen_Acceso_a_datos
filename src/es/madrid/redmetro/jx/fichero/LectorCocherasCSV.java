package es.madrid.redmetro.jx.fichero;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.madrid.redmetro.jx.exception.RedMetroException;
import es.madrid.redmetro.jx.gestores.GestorConfiguracion;
import es.madrid.redmetro.jx.model.Cochera;
import es.madrid.redmetro.jx.service.CocheraService;

public class LectorCocherasCSV {
	
	private CocheraService cocheraService = new CocheraService();
	
	public void tratarFichero() throws RedMetroException {
		String rutaFichero = GestorConfiguracion.obtenerValor("fichero.cocheras");
		
		
		try (BufferedReader lector = new BufferedReader (new FileReader(rutaFichero))) {
			
			lector.readLine(); // me salto la cabecera!!!!! importante!!!!!!!!!
			
			String lineaLeida;
			while ((lineaLeida = lector.readLine()) != null) {
				
				if (lineaLeida.trim().isEmpty()) {
					continue;
				}
				
				String[] trozos = lineaLeida.split(";");
				
				int codigo = Integer.parseInt(trozos[0].trim());
				
				Cochera cocheraLeida = new Cochera();
				cocheraLeida.setCodigoCochera(Integer.parseInt(trozos[1].trim()));
				cocheraLeida.setDeposito(Integer.parseInt(trozos[2].trim()));
				cocheraLeida.setNombre(trozos[3].trim());
				cocheraLeida.setDireccion(trozos[4].trim());
				
				try {
					cocheraService.procesarOperacion(codigo, cocheraLeida);
				} catch (RedMetroException e) {
					System.out.println(e.getMessage());
				}
			}
			
		} catch (IOException e) {
			throw new RedMetroException ("Error al leer el fichero de cocheras", e);
		}
	}

}
