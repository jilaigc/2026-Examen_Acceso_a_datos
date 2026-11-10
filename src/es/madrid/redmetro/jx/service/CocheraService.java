package es.madrid.redmetro.jx.service;

import es.madrid.redmetro.jx.dao.ICochera;
import es.madrid.redmetro.jx.dao.impl.CocheraJDBC;
import es.madrid.redmetro.jx.exception.RedMetroException;
import es.madrid.redmetro.jx.model.Cochera;

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
