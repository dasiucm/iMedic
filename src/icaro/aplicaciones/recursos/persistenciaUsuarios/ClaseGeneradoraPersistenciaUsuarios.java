package icaro.aplicaciones.recursos.persistenciaUsuarios;

import icaro.aplicaciones.informacion.gestionCitas.UsuarioContexto;
import icaro.aplicaciones.recursos.persistenciaUsuarios.imp.PersistenciaUsuariosImp;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;

import java.rmi.RemoteException;

public class ClaseGeneradoraPersistenciaUsuarios extends ImplRecursoSimple implements ItfPersistenciaUsuarios {

	public ClaseGeneradoraPersistenciaUsuarios(String idRecurso) throws RemoteException {
		super(idRecurso);
		System.out.println("SE CREA EL RECURSO QUE QUERIAMOS");
	}

	/**
	 *
	 */

	@Override
	public void insertarUsuario(String nombreChat, UsuarioContexto usuar) throws Exception {
		PersistenciaUsuariosImp.insertarUsuario(nombreChat, usuar);
	}

	@Override
	public UsuarioContexto obtenerContextoUsuario(String nombreChat) throws Exception {
		return PersistenciaUsuariosImp.obtenerContextoUsuario(nombreChat);
	}

	@Override
	public UsuarioContexto obtenerContextoUsuarioDNI(String dni) throws Exception {
		return PersistenciaUsuariosImp.obtenerContextoUsuario(dni);
	}

	@Override
	public String getUsuarioPersistenciaUsuarios(String nombre) throws Exception {
		return PersistenciaUsuariosImp.getUsuarioPersistenciaUsuarios(nombre);
	}
	
	@Override
	public String getNombrePersistenciaUsuarios(String usuario) throws Exception {
		return PersistenciaUsuariosImp.getNombrePersistenciaUsuarios(usuario);
	}

}
