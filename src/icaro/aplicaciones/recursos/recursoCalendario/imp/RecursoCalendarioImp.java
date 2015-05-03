package icaro.aplicaciones.recursos.recursoCalendario.imp;

import icaro.aplicaciones.informacion.gestionCitas.CitaMedica;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class RecursoCalendarioImp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5636331096703718484L;

	public static HashMap<String, CitaMedica> calendarioCitas_pacienteIdx = new HashMap<String, CitaMedica>();
	public static HashMap<String, CitaMedica> calendarioCitas_medicoIdx = new HashMap<String, CitaMedica>();

	/*------ Funcionalidad de paciente --------*/

	public static void insertaCita(String usuario, String medico, String fecha) throws Exception {
		CitaMedica cita = new CitaMedica(usuario, medico, fecha);
		if (citaValida(medico, fecha)) {
			calendarioCitas_pacienteIdx.put(usuario, cita);
			calendarioCitas_medicoIdx.put(medico, cita);
		}

	}

	public static void insertaCita(CitaMedica cita) throws Exception {
		if (citaValida(cita.getMedico(), cita.getFecha())) {
			calendarioCitas_pacienteIdx.put(cita.getUsuario(), cita);
			calendarioCitas_medicoIdx.put(cita.getMedico(), cita);
		}

	}

	public static String consultaCitas(String usuario) throws Exception {
		String msg = "No tiene citas.";
		Iterator<Entry<String, CitaMedica>> it = calendarioCitas_pacienteIdx.entrySet().iterator();
		if (it.hasNext()) {
			msg = "Tiene las citas: \n";
		}
		while (it.hasNext()) {
			Entry<String, CitaMedica> cita = it.next();
			if (cita.getKey().equals(usuario)) {
				msg += "\t" + convertToString(cita.getValue()) + "\n";
			}
		}
		return msg;
	}

	public static Boolean darBajaCita(String usuario, String fecha) throws Exception {

		Boolean borrado = false;

		Iterator<Entry<String, CitaMedica>> it = calendarioCitas_pacienteIdx.entrySet().iterator();

		while (it.hasNext() && !borrado) {
			Entry<String, CitaMedica> cita = it.next();
			if (cita.getKey().equals(usuario) && citaEnFecha(cita.getValue(), fecha)) {
				calendarioCitas_pacienteIdx.remove(cita);
				calendarioCitas_medicoIdx.remove(cita.getValue().getMedico());
			}
		}

		return borrado;

	}

	public static Boolean darBajaCita(CitaMedica cita) throws Exception {

		Boolean borrado = false;

		Iterator<Entry<String, CitaMedica>> it = calendarioCitas_pacienteIdx.entrySet().iterator();

		while (it.hasNext() && !borrado) {
			Entry<String, CitaMedica> cita_int = it.next();
			if (cita_int.getKey().equals(cita.getUsuario()) && citaEnFecha(cita_int.getValue(), cita.getFecha())) {
				calendarioCitas_pacienteIdx.remove(cita_int);
				calendarioCitas_medicoIdx.remove(cita_int.getValue().getMedico());
			}
		}

		return borrado;

	}

	public static Boolean cambiarCita(String usuario, String medico, String fechaNueva, String fechaAntigua)
			throws Exception {
		if (darBajaCita(usuario, fechaAntigua)) {
			insertaCita(usuario, medico, fechaNueva);
			return true;
		} else {
			return false;
		}
	}

	/*------ Funcionalidad de medico --------*/

	public static Boolean darBajaCitas(String medico, String fechaInicio, String fechaFin) throws Exception {
		Iterator<Entry<String, CitaMedica>> it = calendarioCitas_medicoIdx.entrySet().iterator();

		Boolean borrado = false;

		while (it.hasNext()) {
			Entry<String, CitaMedica> cita = it.next();
			if (citaEnRangoFecha(cita.getValue(), fechaInicio, fechaFin)) {
				borrado = true;
				calendarioCitas_pacienteIdx.remove(cita);
				calendarioCitas_medicoIdx.remove(cita.getValue().getMedico());
			}
		}

		return borrado;
	}

	public static String consultarHorario(String medico) throws Exception {
		String msg = "No tiene citas que atender.";
		Iterator<Entry<String, CitaMedica>> it = calendarioCitas_medicoIdx.entrySet().iterator();
		if (it.hasNext()) {
			msg = "Tiene las citas: \n";
		}
		while (it.hasNext()) {
			Entry<String, CitaMedica> cita = it.next();
			msg += "\t" + convertToString(cita.getValue()) + "\n";
		}
		return msg;
	}

	/*------ Funcionalidad de sistema --------*/

	private static Boolean citaValida(String medico, String fecha) throws Exception {
		return true;
	}

	private static String convertToString(CitaMedica cita) {
		return cita.getFecha() + " --> " + cita.getMedico();
	}

	private static Boolean citaEnFecha(CitaMedica cita, String fecha) {
		return (cita.getFecha().equals(fecha));
	}

	private static Boolean citaEnRangoFecha(CitaMedica cita, String fechaInicio, String fechaFin) {
		// TODO - Coger el rango de fechas entre las dos fechas
		// se podria hacer con un split e ir haciendo +1 hasta llegar a la fecha
		// fin
		return (cita.getFecha().equals(fechaInicio) || cita.getFecha().equals(fechaFin));
	}

}
