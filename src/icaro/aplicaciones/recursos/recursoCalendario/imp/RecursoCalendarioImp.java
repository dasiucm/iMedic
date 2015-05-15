package icaro.aplicaciones.recursos.recursoCalendario.imp;

import icaro.aplicaciones.informacion.gestionCitas.CitaMedica;
import icaro.aplicaciones.recursos.recursoCalendario.DateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecursoCalendarioImp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5636331096703718484L;

	private static final String slash = "/";
	private static final String dash = "-";
	public static final SimpleDateFormat slashFormatter = new SimpleDateFormat(
			"dd/MM/yyyy");
	private static final SimpleDateFormat dashFormatter = new SimpleDateFormat(
			"dd-MM-yyyy");

	private static final String PACIENTES_PATH = "pacientes";
	private static final String MEDICOS_PATH = "medicos";

	private static final Map<String, List<CitaMedica>> calendarioCitas_pacienteIdx = read(PACIENTES_PATH);
	private static final Map<String, List<CitaMedica>> calendarioCitas_medicoIdx = read(MEDICOS_PATH);

	private RecursoCalendarioImp() {
		// Ocultar el constructor
	}

	/*------ Funcionalidad de paciente --------*/

	public static String insertaCita(String usuario, String medico, String fecha)
			throws Exception {
		CitaMedica cita = new CitaMedica(usuario, medico, fecha);
		return insertaCita(cita);
	}

	public static String insertaCita(CitaMedica cita) throws Exception {
		String msg = "";
		if (citaValida(cita)) {
			// TODO - Check the medic and return an error if it's not a valid name
			List<CitaMedica> citasPaciente = calendarioCitas_pacienteIdx
					.get(cita.getUsuario());
			if (citasPaciente == null) {
				citasPaciente = new ArrayList<CitaMedica>();
			}
			citasPaciente.add(cita);
			calendarioCitas_pacienteIdx.put(cita.getUsuario(), citasPaciente);

			List<CitaMedica> citasMedico = calendarioCitas_medicoIdx.get(cita
					.getMedico());
			if (citasMedico == null) {
				citasMedico = new ArrayList<CitaMedica>();
			}
			citasMedico.add(cita);
			calendarioCitas_medicoIdx.put(cita.getMedico(), citasMedico);
			guardaCalendarios();
			
			return "";
		} else {
			return "Disculpe, pero la fecha " + cita.getFecha() + " no es una fecha válida.";
		}
	}

	public static String consultaCitas(String usuario) throws Exception {
		String msg = null;

		List<CitaMedica> citasPaciente = calendarioCitas_pacienteIdx
				.get(usuario);
		if (citasPaciente == null || citasPaciente.isEmpty()) {
			msg = "No tiene citas.";
		} else {
			msg = "Sus citas son: " + citasPaciente.toString();
		}
		return msg;
	}

	public static Boolean darBajaCita(String usuario, String fecha)
			throws Exception {
		return darBajaCita(new CitaMedica(usuario, null, fecha));
	}

	public static Boolean darBajaCita(CitaMedica cita) throws Exception {

		Boolean borrado = false;

		List<CitaMedica> citasPaciente = calendarioCitas_pacienteIdx.get(cita
				.getUsuario());
		if (citasPaciente != null) {
			for (int i = 0; i < citasPaciente.size(); ++i) {
				CitaMedica citaPaciente = citasPaciente.get(i);
				if (citaEnFecha(citaPaciente, cita.getFecha())) {
					citasPaciente.remove(i);
					borrado = true;
				}
			}
		}

		List<CitaMedica> citasMedico = calendarioCitas_medicoIdx.get(cita
				.getMedico());
		if (citasMedico != null) {
			for (int i = 0; i < citasMedico.size(); ++i) {
				CitaMedica citaMedico = citasMedico.get(i);
				if (citaEnFecha(citaMedico, cita.getFecha())) {
					citasPaciente.remove(i);
					borrado = true;
				}
			}
		}

		if (borrado) {
			guardaCalendarios();
		}
		return borrado;

	}

	public static Boolean cambiarCita(String usuario, String medico,
			String fechaNueva, String fechaAntigua) throws Exception {
		if (darBajaCita(usuario, fechaAntigua)) {
			insertaCita(usuario, medico, fechaNueva);
			return true;
		} else {
			return false;
		}
	}

	/*------ Funcionalidad de medico --------*/

	public static Boolean darBajaCitas(String medico, String fechaInicio,
			String fechaFin) throws Exception {

		Boolean borrado = false;
		List<CitaMedica> citasMedicas = calendarioCitas_medicoIdx.get(medico);
		if (citasMedicas != null) {
			for (int i = 0; i < citasMedicas.size(); ++i) {
				CitaMedica citaMedica = citasMedicas.get(i);
				if (citaEnRangoFecha(citaMedica, fechaInicio, fechaFin)) {
					darBajaCita(citaMedica);
					borrado = true;
				}
			}
		}

		return borrado;
	}

	public static String consultarHorario(String medico) throws Exception {
		List<CitaMedica> citasMedicas = calendarioCitas_medicoIdx.get(medico);
		String msg = null;

		if (citasMedicas == null || citasMedicas.isEmpty()) {
			msg = "No tiene citas que atender.";
		} else {
			msg = "Sus citas son: " + citasMedicas.toString();
		}

		return msg;
	}

	/*------ Funcionalidad de sistema --------*/

	private static Boolean citaValida(CitaMedica cita) throws Exception {
		return getDateFromString(cita.getFecha()) != null;
	}

	private static Boolean citaEnFecha(CitaMedica cita, String fecha) {
		Date citaUno = getDateFromString(cita.getFecha());
		if (citaUno != null) {
			Date citaDos = getDateFromString(fecha);
			return citaDos != null && DateUtil.sameMinute(citaUno, citaDos);
		}
		return false;
	}

	private static Boolean citaEnRangoFecha(CitaMedica cita,
			String fechaInicio, String fechaFin) {
		Date date = getDateFromString(cita.getFecha());
		if (date != null) {
			Date initialDate = getDateFromString(fechaInicio);
			if (initialDate != null) {
				Date finalDate = getDateFromString(fechaFin);
				return finalDate != null
						&& (date.after(initialDate) && date.before(finalDate));
			}
			return false;
		}
		return false;
	}

	/**
	 * 
	 * @param strDate
	 * @return the Date object or null if the string couldn't be parsed
	 */
	private static Date getDateFromString(String strDate) {
		if (strDate == null || strDate.isEmpty()) {
			return null;
		}
		try {
			if (strDate.contains(slash)) {
				return slashFormatter.parse(strDate);
			} else if (strDate.contains(dash)) {
				return dashFormatter.parse(strDate);
			} else {
				return DateUtil.parse(strDate);
			}
		} catch (Exception parseEx) {
			parseEx.printStackTrace();
			try {
				return DateUtil.parse(strDate);
			} catch (Exception nestedParseException) {
				nestedParseException.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 
	 * @param path
	 * @return un Mapa vacío si no ha podido leer el archivo o sino el mapa
	 *         leido
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, List<CitaMedica>> read(String path) {
		File file = new File(path);
		if (!file.exists() || !file.isFile()) {
			return new HashMap<String, List<CitaMedica>>();
		}
		FileInputStream f = null;
		ObjectInputStream s = null;
		Map<String, List<CitaMedica>> res = null;
		try {
			f = new FileInputStream(file);
			s = new ObjectInputStream(f);
			res = (Map<String, List<CitaMedica>>) s.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (IOException ignoredEx) {
					// Ignore the exception
				} finally {
					s = null;
				}
			}
		}
		if (res == null) {
			res = new HashMap<String, List<CitaMedica>>();
		}
		return res;
	}

	/**
	 * Escribe en el archivo el mapa del argumento
	 * 
	 * @param path
	 * @param map
	 */
	private static void write(String path, Map<String, List<CitaMedica>> map) {
		File file = new File(path);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(map);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException ignoredEx) {
					// Ignore the exception
				} finally {
					oos = null;
				}
			}
		}
	}

	private static void guardaCalendarios() {
		write(PACIENTES_PATH, calendarioCitas_pacienteIdx);
		write(MEDICOS_PATH, calendarioCitas_medicoIdx);
	}
}
