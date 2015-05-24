package icaro.aplicaciones.recursos.recursoCalendario.imp;

import icaro.aplicaciones.informacion.IOUtils;
import icaro.aplicaciones.informacion.gestionCitas.CitaMedica;
import icaro.aplicaciones.recursos.recursoCalendario.DateUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.time.DateUtils;

public class RecursoCalendarioImp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5636331096703718484L;

	private static final String slash = "/";
	private static final String dash = "-";
	public static final SimpleDateFormat slashFormatter = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat dashFormatter = new SimpleDateFormat("dd-MM-yyyy");

	private static final String PACIENTES_PATH = "pacientes";
	private static final String MEDICOS_PATH = "medicos";

	private static final Map<String, List<CitaMedica>> calendarioCitas_pacienteIdx = IOUtils.read(PACIENTES_PATH);
	private static final Map<String, List<CitaMedica>> calendarioCitas_medicoIdx = IOUtils.read(MEDICOS_PATH);

	private RecursoCalendarioImp() {
		// Ocultar el constructor
	}

	/*------ Funcionalidad de paciente --------*/

	public static boolean insertaCita(String usuario, String medico, String fecha) throws Exception {
		CitaMedica cita = new CitaMedica(usuario, medico, fecha);
		return insertaCita(cita);
	}

	public static boolean insertaCita(CitaMedica cita) throws Exception {
		if (citaValida(cita)) {
			List<CitaMedica> citasPaciente = calendarioCitas_pacienteIdx.get(cita.getUsuario());
			if (citasPaciente == null) {
				citasPaciente = new ArrayList<CitaMedica>();
			}
			citasPaciente.add(cita);
			calendarioCitas_pacienteIdx.put(cita.getUsuario(), citasPaciente);

			List<CitaMedica> citasMedico = calendarioCitas_medicoIdx.get(cita.getMedico());
			if (citasMedico == null) {
				citasMedico = new ArrayList<CitaMedica>();
			}
			citasMedico.add(cita);
			calendarioCitas_medicoIdx.put(cita.getMedico(), citasMedico);
			guardaCalendarios();
			return true;
		}
		return false;
	}

	public static String consultaCitas(String usuario) throws Exception {
		String msg = null;

		List<CitaMedica> citasPaciente = calendarioCitas_pacienteIdx.get(usuario);
		if (citasPaciente == null || citasPaciente.isEmpty()) {
			msg = "No tiene citas.";
		} else {
			msg = "Sus citas son: " + citasPaciente.toString();
		}
		return msg;
	}

	public static Boolean darBajaCita(String usuario, String fecha) throws Exception {
		return darBajaCita(new CitaMedica(usuario, null, fecha));
	}

	public static Boolean darBajaCita(CitaMedica cita) throws Exception {

		Boolean borrado = false;

		List<CitaMedica> citasPaciente = calendarioCitas_pacienteIdx.get(cita.getUsuario());
		if (citasPaciente != null) {
			for (int i = 0; i < citasPaciente.size(); ++i) {
				CitaMedica citaPaciente = citasPaciente.get(i);
				if (citaEnFecha(citaPaciente, cita.getFecha())) {
					citasPaciente.remove(i);
					borrado = true;
				}
			}
		}

		List<CitaMedica> citasMedico = calendarioCitas_medicoIdx.get(cita.getMedico());
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

	public static Boolean darBajaCitas(String medico, String fechaInicio) throws Exception {

		Boolean borrado = false;
		List<CitaMedica> citasMedicas = calendarioCitas_medicoIdx.get(medico);
		if (citasMedicas != null) {
			for (int i = 0; i < citasMedicas.size(); ++i) {
				CitaMedica citaMedica = citasMedicas.get(i);
				if (citaEnRangoFecha(citaMedica, fechaInicio)) {
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

	private static Boolean citaEnRangoFecha(CitaMedica cita, String fechaInicio) {
		Date date = getDateFromString(cita.getFecha());
		if (date != null) {
			Date initialDate = getDateFromString(fechaInicio);
			if (initialDate != null) {
				return DateUtils.isSameDay(date, initialDate);
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

	/* --------------- RECOMENDACIONES -------------------- */
	public static String recomiendaFecha(String usuario) {
		boolean eliminaresto = true;
		if(eliminaresto) return "12/12/2015";
		
		
		List<CitaMedica> citasMedicas = calendarioCitas_pacienteIdx.get(usuario);
		int diaRepetido = 0;
		int maxRepeticiones = 0;

		// Array para almacenar los contadores de los dias de la semana
		int weekDays[] = new int[7];
		for (int i = 0; i < 7; i++) {
			weekDays[i] = 0;
		}

		// Iterar las citas medicas del usuario para ir rellenando el array
		for (int i = 0; i < citasMedicas.size(); ++i) {
			String fecha = citasMedicas.get(i).getFecha();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			try {
				cal.setTime(sdf.parse(fecha));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int weekDay = cal.get(Calendar.DAY_OF_WEEK);

			weekDays[weekDay]++;

		}

		// Iterar sobre el array para ver que dia es el mas repetido
		for (int i = 0; i < 7; i++) {
			if (maxRepeticiones <= weekDays[i]) {
				maxRepeticiones = weekDays[i];
				diaRepetido = i;
			}
		}

		// Sacar el siguiente dia mas repetido en formato fecha como un string
		// Get actual date as a calendar
		Calendar fechaRecomendada = Calendar.getInstance();

		while (fechaRecomendada.get(Calendar.DAY_OF_WEEK) != diaRepetido) {
			fechaRecomendada.add(Calendar.DATE, 1);
		}

		String fechaRecomString = fechaRecomendada.toString();

		return fechaRecomString;

	}

	public static String recomiendaMedico(String usuario) {
		boolean eliminaresto = true;
		if(eliminaresto) return "Alberto";
		
		List<CitaMedica> citasMedicas = calendarioCitas_pacienteIdx.get(usuario);
		String medicoMasRepetido = "";
		int maxRepeticiones = 0;

		HashMap<String, Integer> repeticionesMedicos = new HashMap<String, Integer>();

		// Iterar las citas medicas del usuario para ir rellenando el array
		for (int i = 0; i < citasMedicas.size(); ++i) {
			if (repeticionesMedicos.get(citasMedicas.get(i).getMedico()) == null) {
				repeticionesMedicos.put(citasMedicas.get(i).getMedico(), 0);
			} else {
				Integer value = repeticionesMedicos.get(citasMedicas.get(i).getMedico());
				value++;
				repeticionesMedicos.put(citasMedicas.get(i).getMedico(), value);
			}
		}
		
		// Iterar el mapa para coger el medico mas repetido
		Iterator<Entry<String, Integer>> it = repeticionesMedicos.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
			if(maxRepeticiones <= entry.getValue()){
				maxRepeticiones = entry.getValue();
				medicoMasRepetido = entry.getKey();
			}
		}
		
		return medicoMasRepetido;
	}
}
