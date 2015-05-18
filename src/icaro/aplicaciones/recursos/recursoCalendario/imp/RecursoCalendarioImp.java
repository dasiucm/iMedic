package icaro.aplicaciones.recursos.recursoCalendario.imp;

import icaro.aplicaciones.informacion.IOUtils;
import icaro.aplicaciones.informacion.gestionCitas.CitaMedica;
import icaro.aplicaciones.recursos.recursoCalendario.DateUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	private static final Map<String, List<CitaMedica>> calendarioCitas_pacienteIdx = IOUtils
			.read(PACIENTES_PATH);
	private static final Map<String, List<CitaMedica>> calendarioCitas_medicoIdx = IOUtils
			.read(MEDICOS_PATH);

	private RecursoCalendarioImp() {
		// Ocultar el constructor
	}

	/*------ Funcionalidad de paciente --------*/

	public static void insertaCita(String usuario, String medico, String fecha)
			throws Exception {
		CitaMedica cita = new CitaMedica(usuario, medico, fecha);
		insertaCita(cita);
	}

	public static void insertaCita(CitaMedica cita) throws Exception {
		if (citaValida(cita)) {
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

	private static void guardaCalendarios() {
		IOUtils.write(PACIENTES_PATH, calendarioCitas_pacienteIdx);
		IOUtils.write(MEDICOS_PATH, calendarioCitas_medicoIdx);
	}
}
