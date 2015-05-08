package icaro.aplicaciones.recursos.comunicacionChat;

import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.recursos.recursoCalendario.DateUtil;
import icaro.aplicaciones.recursos.recursoCalendario.imp.RecursoCalendarioImp;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class ParserFecha {

	private static final int DIA = 1;
	private static final int SEMANA = 7;
	private static final int MES = 30;
	private static final int AÑO = 365;

	/**
	 * Los valores de estas claves se sumarán/restarán a la fecha actual
	 */
	private static final Map<String, Integer> MAPEO_FECHAS = new HashMap<String, Integer>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 685284131402410709L;

		{
			put("pasado mañana", 2 * DIA);
			put("mañana", DIA);
			put("ayer", -DIA);
			put("semana que viene", SEMANA);
			put("semana próxima", SEMANA);
			put("semana proxima", SEMANA);
			put("semana siguiente", SEMANA);
			put("siguiente semana", SEMANA);
			put("siguiente semana", SEMANA);
			put("mes que viene", MES);
			put("mes siguiente", MES);
			put("año siguiente", AÑO);
			put("axo próximo", AÑO);
			put("próximo año", AÑO);
			put("mes próximo", MES);
			put("mes proximo", MES);
			put("proximo año", AÑO);
			put("proximo mes", MES);
			put("próximo mes", MES);
			put("próxima semana", SEMANA);
			put("proxima semana", SEMANA);
			put("próximo día", DIA);
			put("proximo día", DIA);
			put("proximo dia", DIA);
		}
	};

	/**
	 * Los valores de estas claves serán usados para obtener la fecha correspondiente al día de la semana
	 */
	private static final Map<String, Integer> MAPEO_DIAS = new HashMap<String, Integer>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2170365284292450115L;

		{
			put("lunes", Calendar.MONDAY);
			put("martes", Calendar.TUESDAY);
			put("miércoles", Calendar.WEDNESDAY);
			put("miercoles", Calendar.WEDNESDAY);
			put("jueves", Calendar.THURSDAY);
			put("viernes", Calendar.FRIDAY);
			put("sábado", Calendar.SATURDAY);
			put("sabado", Calendar.SATURDAY);
			put("domingo", Calendar.SUNDAY);
		}
	};

	private ParserFecha() {
	}

	/**
	 * Dada una notificación con una anotación de tipo "fecha" del tipo
	 * "mañana, pasado, ayer, el viernes, etc." la convierte a un mensaje de
	 * tipo "dd/MM/yyyy"
	 * 
	 * @param notif
	 * @return
	 */
	public static final Notificacion parseaFecha(Notificacion notif) {

		String msg = notif.getMensajeNotificacion();

		Integer dias = MAPEO_FECHAS.get(msg);
		if (dias != null) {
			Date nuevaFecha = DateUtil.addDays(new Date(), dias);
			msg = RecursoCalendarioImp.slashFormatter.format(nuevaFecha);
			notif.setMensajeNotificacion(msg);
		} else {

			Integer diaSemana = MAPEO_DIAS.get(msg);
			if (diaSemana != null) {
				Calendar calendar = Calendar.getInstance();
				Date date = new Date();
				calendar.setTime(date);
				calendar.set(Calendar.DAY_OF_WEEK, diaSemana);
				msg = RecursoCalendarioImp.slashFormatter.format(calendar
						.getTime());
				notif.setMensajeNotificacion(msg);
			}
		}

		return notif;
	}
}
