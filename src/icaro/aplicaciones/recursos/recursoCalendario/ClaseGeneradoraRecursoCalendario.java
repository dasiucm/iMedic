package icaro.aplicaciones.recursos.recursoCalendario;

import icaro.aplicaciones.informacion.gestionCitas.CitaMedica;
import icaro.aplicaciones.informacion.gestionCitas.UsuarioContexto;
import icaro.aplicaciones.recursos.recursoCalendario.imp.RecursoCalendarioImp;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;

import java.rmi.RemoteException;

public class ClaseGeneradoraRecursoCalendario extends ImplRecursoSimple implements ItfUsoRecursoCalendario {

	public ClaseGeneradoraRecursoCalendario(String idRecurso) throws RemoteException {
		super(idRecurso);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insertaCita(UsuarioContexto paciente, String medico, String fecha) throws Exception {
		// TODO Auto-generated method stub
		RecursoCalendarioImp.insertaCita(paciente, medico, fecha);
	}

	@Override
	public String consultaCitas(UsuarioContexto paciente) throws Exception {
		// TODO Auto-generated method stub
		return RecursoCalendarioImp.consultaCitas(paciente);
	}

	@Override
	public Boolean darBajaCita(UsuarioContexto paciente, String fecha) throws Exception {
		// TODO Auto-generated method stub
		return RecursoCalendarioImp.darBajaCita(paciente, fecha);
	}

	@Override
	public Boolean cambiarCita(UsuarioContexto paciente, String medico, String fechaNueva, String fechaAntigua)
			throws Exception {
		// TODO Auto-generated method stub
		return RecursoCalendarioImp.cambiarCita(paciente, medico, fechaNueva, fechaAntigua);
	}

	@Override
	public Boolean darBajaCitas(String medico, String fechaInicio, String fechaFin) throws Exception {
		// TODO Auto-generated method stub
		return RecursoCalendarioImp.darBajaCitas(medico, fechaInicio, fechaFin);
	}

	@Override
	public String consultarHorario(String medico) throws Exception {
		// TODO Auto-generated method stub
		return RecursoCalendarioImp.consultarHorario(medico);
	}
	
}
