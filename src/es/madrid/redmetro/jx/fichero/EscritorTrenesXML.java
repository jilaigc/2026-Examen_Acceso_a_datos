package es.madrid.redmetro.jx.fichero;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import es.madrid.redmetro.jx.exception.RedMetroException;
import es.madrid.redmetro.jx.gestores.GestorConfiguracion;
import es.madrid.redmetro.jx.model.Color;
import es.madrid.redmetro.jx.model.Linea;
import es.madrid.redmetro.jx.model.Tren;
import es.madrid.redmetro.jx.service.TrenService;

public class EscritorTrenesXML {
	
	private TrenService trenService = new TrenService();
	
	public void generarFichero() throws RedMetroException {
		
		List<Tren> listaTrenes = trenService.obtenerTodosLosTrenes();
		String rutaFichero = GestorConfiguracion.obtenerValor("fichero.salida");
		
		try {
			
			DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructor = fabrica.newDocumentBuilder();
			Document documentoXML = constructor.newDocument();
			
			Element raiz = documentoXML.createElement("info-trenes-linea-color");
			documentoXML.appendChild(raiz);
			
			Element elementoTrenes = documentoXML.createElement("trenes");
			raiz.appendChild(elementoTrenes);
			
			for (Tren tren : listaTrenes) {
				Element elementoTren = documentoXML.createElement("tren");
				elementoTren.setAttribute("id", String.valueOf(tren.getCodigoTren()));
				elementoTrenes.appendChild(elementoTren);
				
				añadirEtiqueta(documentoXML, elementoTren, "modelo", tren.getModelo());
			    añadirEtiqueta(documentoXML, elementoTren, "anyo_incorporacion", String.valueOf(tren.getFechaIncorporacion().getYear()));
			    añadirEtiqueta(documentoXML, elementoTren, "empresa_constructora", tren.getEmpresaConstructora());
			    añadirEtiqueta(documentoXML, elementoTren, "cod_cochera", String.valueOf(tren.getCochera().getCodigoCochera()));
			    
			    Linea lineaTren = tren.getLinea();
			    Color colorLinea = lineaTren.getColor();
			    
			    Element elementoLinea = documentoXML.createElement("linea");
			    elementoLinea.setAttribute("id_linea", String.valueOf(lineaTren.getCodigoLinea()));
			    elementoTren.appendChild(elementoLinea);
			    
			    añadirEtiqueta (documentoXML, elementoLinea, "nombre_l", lineaTren.getNombreLargo());
			    
			    Element elementoColor = documentoXML.createElement("color");
			    elementoColor.setAttribute("id", String.valueOf(colorLinea.getCodigoColor()));
			    elementoColor.setAttribute("cod_hex", colorLinea.getCodigoHexadecimal());
			    elementoLinea.appendChild(elementoColor);

			    añadirEtiqueta(documentoXML, elementoColor, "descripcion", colorLinea.getNombre());
				
			}
			
			TransformerFactory fabricaTrans = TransformerFactory.newInstance();
			Transformer transformador = fabricaTrans.newTransformer();
			transformador.setOutputProperty(OutputKeys.INDENT, "yes");
			transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			
			transformador.transform(new DOMSource(documentoXML), new StreamResult(new File(rutaFichero)));
			System.out.println("Fichero: " + rutaFichero + " generado OK.");
		
		} catch (ParserConfigurationException | TransformerException e) {
			throw new RedMetroException ("Error al generar el fichero de trenes", e);
		}
	}
	
	// este método recibe 4 cosas: el documento xml que estamos construyendo, padre (etiqueta dentro de la cual va la nueva o sea tren)
	// nombreEtiqueta es cómo se llama la nueva, y texto, l oque va dentro.
	private void añadirEtiqueta (Document documento, Element padre, String nombreEtiqueta, String texto) {
		Element etiqueta = documento.createElement(nombreEtiqueta); // creop la etiqueta modelo
		etiqueta.setTextContent(texto); // le meto dentro el texto
		padre.appendChild(etiqueta); // la cuelgo dentro del tren
	}
}
