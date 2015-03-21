package icaro.aplicaciones.recursos.persistenciaUsuarios;

import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfPersistenciaUsuarios  extends ItfUsoRecursoSimple{
	
	void insertarUsuario(String nombreChat, String nombre, String DNI) throws Exception;
	
	void insertarNombreUsuario(String nombreChat, String nombre) throws Exception;
	
	void insertarDniUsuario(String nombreChat, String DNI) throws Exception;
	
	String obtenerNombreUsuario(String nombreChat) throws Exception;
	
	String obtenerDNI(String nombreChat) throws Exception;
	
	boolean validarUsuario(String nombreChat, String DNI) throws Exception;	

}
