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
	public void insertaCita(String usuario, String medico, String fecha) throws Exception {
		// TODO Auto-generated method stub
		RecursoCalendarioImp.insertaCita(usuario, medico, fecha);
	}
	
	@Override
	public void insertaCita(CitaMedica cita) throws Exception {
		// TODO Auto-generated method stub
		RecursoCalendarioImp.insertaCita(cita);
	}

	@Override
	public String consultaCitas(String usuario) throws Exception {
		// TODO Auto-generated method stub
		return RecursoCalendarioImp.consultaCitas(usuario);
	}

	@Override
	public Boolean darBajaCita(String usuario, String fecha) throws Exception {
		// TODO Auto-generated method stub
		return RecursoCalendarioImp.darBajaCita(usuario, fecha);
	}
	
	@Override
	public Boolean darBajaCita(CitaMedica cita) throws Exception{
		// TODO Auto-generated method stub
		return RecursoCalendarioImp.darBajaCita(cita);
	}

	@Override
	public Boolean cambiarCita(String usuario, String medico, String fechaNueva, String fechaAntigua)
			throws Exception {
		// TODO Auto-generated method stub
		return RecursoCalendarioImp.cambiarCita(usuario, medico, fechaNueva, fechaAntigua);
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
