package icaro.aplicaciones.recursos.recursoCalendario;

import icaro.aplicaciones.informacion.gestionCitas.CitaMedica;
import icaro.aplicaciones.informacion.gestionCitas.UsuarioContexto;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

import java.util.HashMap;

public interface ItfUsoRecursoCalendario extends ItfUsoRecursoSimple {

	/*------ Funcionalidad de paciente --------*/

	void insertaCita(UsuarioContexto paciente, String medico, String fecha) throws Exception;

	String consultaCitas(UsuarioContexto paciente) throws Exception;

	Boolean darBajaCita(UsuarioContexto paciente, String fecha) throws Exception;

	Boolean cambiarCita(UsuarioContexto paciente, String medico, String fechaNueva, String fechaAntigua)
			throws Exception;

	/*------ Funcionalidad de medico --------*/

	Boolean darBajaCitas(String medico, String fechaInicio, String fechaFin) throws Exception;

	String consultarHorario(String medico) throws Exception;

}
