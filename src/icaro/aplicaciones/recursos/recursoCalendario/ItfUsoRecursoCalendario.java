package icaro.aplicaciones.recursos.recursoCalendario;

import icaro.aplicaciones.informacion.gestionCitas.CitaMedica;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoRecursoCalendario extends ItfUsoRecursoSimple {

	/*------ Funcionalidad de paciente --------*/

	String insertaCita(String usuario, String medico, String fecha)
			throws Exception;

	String insertaCita(CitaMedica cita) throws Exception;

	String consultaCitas(String usuario) throws Exception;

	Boolean darBajaCita(String usuario, String fecha) throws Exception;

	Boolean darBajaCita(CitaMedica cita) throws Exception;

	Boolean cambiarCita(String usuario, String medico, String fechaNueva,
			String fechaAntigua) throws Exception;

	/*------ Funcionalidad de medico --------*/

	Boolean darBajaCitas(String medico, String fechaInicio, String fechaFin)
			throws Exception;

	String consultarHorario(String medico) throws Exception;

}