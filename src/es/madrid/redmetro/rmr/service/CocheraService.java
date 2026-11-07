package es.madrid.redmetro.rmr.service;

import es.madrid.redmetro.rmr.dao.ICochera;
import es.madrid.redmetro.rmr.dao.impl.CocheraJDBC;
import es.madrid.redmetro.rmr.exception.RedMetroException;
import es.madrid.redmetro.rmr.model.Cochera;

public class CocheraService {

	private ICochera cocheraDAO = new CocheraJDBC();

	public void procesarOperacion(int codigoOperacion, Cochera cochera) throws RedMetroException {

		switch (codigoOperacion) {

		case 1:
			Cochera cocheraEnBD = cocheraDAO.obtenerCocheraPorCodigo(codigoOperacion);
			if (cocheraEnBD == null) {
				cocheraDAO.insertarCochera(cochera);
				System.out.println("Cochera " + cochera.getCodigoCochera()+ " insertada correctamente.");
			} else {
				System.out.println("Cochera " + cochera.getCodigoCochera() + " ya existe.");
			}
			break;

		case 2:
			cocheraDAO.actualizarCochera(cochera);
			System.out.println("Cochera " + cochera.getCodigoCochera() + " actualizada.");
			break;

		case 3:
			cocheraDAO.borrarCochera(cochera.getCodigoCochera());
			System.out.println("Cochera " + cochera.getCodigoCochera() + " borrada.");
			break;

		default: // default es 0
			break;
		}
	}

}
