/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.recursos.extractorSemantico.imp;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Corpus;
import gate.CorpusController;
import gate.Document;
import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.persist.PersistenceException;
import gate.util.GateException;
import gate.util.persistence.PersistenceManager;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FGarijo El objetivo de esta clase es analizar los inputs procedentes
 *         del chat y generar un conjunto de anotaciones que seran interpretadas
 *         semanticamente ej citas . El funcionamiento se basa en un procesador
 *         generado por GATE
 */
public class ExtractorSemanticoImp {

	private CorpusController extractor;
	private File ficheroProcesador;
	private String rutaCorpus;
	private Corpus corpus = null;
	private HashSet tiposAnotacionesRelevantes;
	// Se crea un atributo de tipo calendario gregoriano para obtener la fecha y la hora actuales que se guardan en los 
	// atributos actualDate y actualHour
	Calendar fecha=new GregorianCalendar();
	private String actualDate= Integer.toString(fecha.get(Calendar.YEAR)) +"/"+Integer.toString(fecha.get(Calendar.MONTH))
			+"/"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
	private String actualHour= Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));
	// Se crean atributos para la fecha y la hora recogidas del extractor semantico (GATE)
	// Para extraer la fecha es necesario concatenar los strings de los tokens obtenidos a traves de GATE
	// La idea seria tener un CASE en el extractor o en las reglas para obtener la fecha deseada y la fecha actual 
	// y crear en dicho case todas las casuisticas posibles con estos datos obtenidos
	private String extractedDate;
	private String extractedHour;
	
	public ExtractorSemanticoImp(String rutaProcesador)
			throws PersistenceException {
		// validar las rutas
		ficheroProcesador = new File(rutaProcesador);
		if (!ficheroProcesador.exists()) {
			throw new PersistenceException(
					"El fichero especificado en la ruta : " + rutaProcesador
							+ " No existe ");
			// definimos un conjunto con las anotaciones que nos interesa buscar

		}
		tiposAnotacionesRelevantes = new HashSet<String>();
		tiposAnotacionesRelevantes.add("Lookup");
		tiposAnotacionesRelevantes.add("Saludo");
		tiposAnotacionesRelevantes.add("InicioPeticion");
	}

	public void incializar() {
		try {
			// initialise GATE - this must be done before calling any GATE APIs
			Gate.init();
		} catch (GateException ex) {
			Logger.getLogger(ExtractorSemanticoImp.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		try {
			extractor = (CorpusController) PersistenceManager
					.loadObjectFromFile(ficheroProcesador);
		} catch (PersistenceException ex) {
			Logger.getLogger(ExtractorSemanticoImp.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ExtractorSemanticoImp.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (ResourceInstantiationException ex) {
			Logger.getLogger(ExtractorSemanticoImp.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		// Create a Corpus to use. We recycle the same Corpus object for each
		// iteration. The string parameter to newCorpus() is simply the
		// GATE-internal name to use for the corpus. It has no particular
		// significance.
		// Corpus corpus = null;
		try {
			corpus = Factory.newCorpus("BatchProcessApp Corpus");
		} catch (ResourceInstantiationException ex) {
			Logger.getLogger(ExtractorSemanticoImp.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		extractor.setCorpus(corpus);
	}

	public HashSet extraerAnotaciones(HashSet annotTypesRequired,
			String textoUsuario) {
		Document doc = null;
		try {
			doc = Factory.newDocument(textoUsuario);
		} catch (ResourceInstantiationException ex) {
			Logger.getLogger(ExtractorSemanticoImp.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		// put the document in the corpus
		corpus.add(doc);

		try {
			// run the application
			extractor.execute();
		} catch (ExecutionException ex) {
			Logger.getLogger(ExtractorSemanticoImp.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		// remove the document from the corpus again
		corpus.clear();

		if (annotTypesRequired == null) {
			annotTypesRequired = tiposAnotacionesRelevantes;
			// Create a temporary Set to hold the annotations we wish to write
			// out
		}

		// Set annotationsToWrite = new HashSet();
		// we only extract annotations from the default (unnamed) AnnotationSet
		// in this example
		AnnotationSet defaultAnnotSet = doc.getAnnotations();
		FeatureMap features = doc.getFeatures();
		System.out.println(features.toString());
		HashSet<Annotation> conjAnotSalida;
		conjAnotSalida = new HashSet<>(defaultAnnotSet.get(annotTypesRequired));
		return conjAnotSalida;
	}

	public void extraerEntidades(AnnotationSet anotacionesExtraidas) {

	}

}
