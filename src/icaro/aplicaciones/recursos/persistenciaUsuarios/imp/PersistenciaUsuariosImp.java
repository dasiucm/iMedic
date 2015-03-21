package icaro.aplicaciones.recursos.persistenciaUsuarios.imp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PersistenciaUsuariosImp implements Serializable{

	private static Map<String,String> tablaChatNombre= new HashMap<String,String>();
	private static Map<String,String> tablaDniChat = new HashMap<String,String>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3791013440695899189L;

	public void insertarUsuario(String nombreChat, String nombre ){
		tablaChatNombre.put(nombreChat, nombre);		
	}
	
	public void insertarDNI(String nombreChat, String dni ){
		tablaDniChat.put(nombreChat, dni);		
	}
	
	public String obtenerNombreUsuario(String nombreChat){
		return tablaChatNombre.get(nombreChat);
		
	}
	
	public String obtenerDNI(String nombreChat){
		return tablaDniChat.get(nombreChat);
		
	}
	

	
	
}
