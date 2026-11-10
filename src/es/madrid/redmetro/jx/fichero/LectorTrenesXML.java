package es.madrid.redmetro.jx.fichero;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.madrid.redmetro.jx.exception.RedMetroException;
import es.madrid.redmetro.jx.gestores.GestorConfiguracion;
import es.madrid.redmetro.jx.model.Cochera;
import es.madrid.redmetro.jx.model.Linea;
import es.madrid.redmetro.jx.model.Tren;
import es.madrid.redmetro.jx.service.TrenService;

public class LectorTrenesXML {

	private TrenService trenService = new TrenService();
	
	public void tratarFichero() throws RedMetroException {
		String rutaFichero = GestorConfiguracion.obtenerValor("fichero.trenes");
		
		try {
			
			DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructor = fabrica.newDocumentBuilder();
			Document documentoXML = constructor.parse(new File(rutaFichero));
			
			NodeList listaTrenes = documentoXML.getElementsByTagName("tren");
			
			for (int i = 0; i < listaTrenes.getLength(); i++) {
				Element elementoTren = (Element) listaTrenes.item(i); // el (element) es porque lla lista devuelve node y hay que decirle que es una etiqueta
				
				Tren trenLeido = new Tren();
				trenLeido.setCodigoTren(Integer.parseInt(elementoTren.getAttribute("id")));
				trenLeido.setModelo(leerEtiqueta(elementoTren, "modelo"));
				trenLeido.setEmpresaConstructora(leerEtiqueta(elementoTren, "empresa_constructora"));;
				
				int añoIncorporacion = Integer.parseInt(leerEtiqueta(elementoTren, "anyo_incorporacion"));
				trenLeido.setFechaIncorporacion(LocalDate.of(añoIncorporacion, 2, 1)); // convierto el año del xml en la fecha que pide la bd
				
				Cochera cocheraDelTren = new Cochera();
				cocheraDelTren.setCodigoCochera(Integer.parseInt(leerEtiqueta(elementoTren, "cod_cochera")));
				trenLeido.setCochera(cocheraDelTren);
				
				Linea lineaDelTren = new Linea();
				lineaDelTren.setCodigoLinea(Integer.parseInt(leerEtiqueta(elementoTren, "id_linea")));
				trenLeido.setLinea(lineaDelTren);
				
				try {
					trenService.insertarTren(trenLeido);
					System.out.println("Tren " + trenLeido.getCodigoTren() + " insertado.");
				} catch (RedMetroException e) {
					System.out.println(e.getMessage() + " - " + e.getCause());
				}
				
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new RedMetroException ("Error al leer el fichero XML de trenes", e);
		}
	}
	
	private String leerEtiqueta (Element elemento, String nombreEtiqueta) {
		return elemento.getElementsByTagName(nombreEtiqueta).item(0).getTextContent().trim();
	}
}
