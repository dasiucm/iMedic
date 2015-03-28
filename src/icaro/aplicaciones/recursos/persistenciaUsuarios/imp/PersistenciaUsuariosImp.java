package icaro.aplicaciones.recursos.persistenciaUsuarios.imp;

import icaro.aplicaciones.informacion.gestionCitas.UsuarioContexto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PersistenciaUsuariosImp implements Serializable{

	public static Map<String,UsuarioContexto> tablaChatNombre= new HashMap<String,UsuarioContexto>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3791013440695899189L;

	public static void insertarUsuario(String nombreChat, UsuarioContexto nombre ){
		tablaChatNombre.put(nombreChat, nombre);		
	}
	
	public static UsuarioContexto obtenerContextoUsuario(String nombreChat){
		return tablaChatNombre.get(nombreChat);
		
	}
	
	
}
