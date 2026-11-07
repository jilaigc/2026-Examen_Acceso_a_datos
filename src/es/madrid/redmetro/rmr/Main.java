package es.madrid.redmetro.rmr;

import java.util.Scanner;

import es.madrid.redmetro.rmr.exception.RedMetroException;
import es.madrid.redmetro.rmr.fichero.EscritorTrenesXML;
import es.madrid.redmetro.rmr.fichero.LectorCocherasCSV;
import es.madrid.redmetro.rmr.fichero.LectorTrenesXML;
import es.madrid.redmetro.rmr.model.Linea;
import es.madrid.redmetro.rmr.service.LineaService;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Introduzca la operación a realizar: ");
		String operacion = sc.nextLine().toUpperCase();

		try {
			switch (operacion) {
			case "A":
				mostrarColorLinea(sc);
				break;
			case "B":
				new LectorCocherasCSV().tratarFichero();
				System.out.println("El tratamiento del fichero ha acabado");
				break;
			case "C":
				new LectorTrenesXML().tratarFichero();
				break;
			case "D":
				new EscritorTrenesXML().generarFichero();
				break;
			default:
				System.out.println("Operación inválida.");
			}
		} catch (RedMetroException e) {
			System.out.println(e.getMessage());
		}
		sc.close();

	}

	private static void mostrarColorLinea(Scanner sc) {
		System.out.println("Introduce el identificador de la línea: ");
		int codigoLinea = Integer.parseInt(sc.nextLine().trim());

		try {

			LineaService lineaService = new LineaService();
			Linea lineaYColor = lineaService.obtenerLineaConColor(codigoLinea);

			if (lineaYColor == null) {
				System.out.println("La linea: " + codigoLinea + " no existe.");
			} else {
				System.out.println("El color de la línea " + lineaYColor.getCodigoLinea() + " ("
						+ lineaYColor.getNombreLargo() + "), que tiene una longitud de " + lineaYColor.getKilometros()
						+ " kilómetros, es " + lineaYColor.getColor().getNombre() + " ("
						+ lineaYColor.getColor().getCodigoHexadecimal() + ").");
			}

		} catch (RedMetroException e) {
			System.out.println(e.getMessage());
		}
	}

}
