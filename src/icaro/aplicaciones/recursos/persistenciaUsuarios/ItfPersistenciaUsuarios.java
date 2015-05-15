package icaro.aplicaciones.recursos.persistenciaUsuarios;

import icaro.aplicaciones.informacion.gestionCitas.UsuarioContexto;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfPersistenciaUsuarios extends ItfUsoRecursoSimple {

	void insertarUsuario(String nombreChat, UsuarioContexto usuar)
			throws Exception;

	UsuarioContexto obtenerContextoUsuario(String nombreChat) throws Exception;
	
	UsuarioContexto obtenerContextoUsuarioDNI(String dni) throws Exception;
	
	String getUsuarioPersistenciaUsuarios(String nombre) throws Exception;
	
	String getNombrePersistenciaUsuarios(String usuario) throws Exception;

}
