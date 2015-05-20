package icaro.aplicaciones.recursos.persistenciaUsuarios.imp;

import icaro.aplicaciones.informacion.IOUtils;
import icaro.aplicaciones.informacion.gestionCitas.UsuarioContexto;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class PersistenciaUsuariosImp implements Serializable {

	private static final String USUARIOS_PATH = "usuarios";
	private static Map<String, UsuarioContexto> tablaChatNombre = IOUtils.read(USUARIOS_PATH);

	/**
	 *
	 */
	private static final long serialVersionUID = 3791013440695899189L;

	public static void insertarUsuario(String usuario, UsuarioContexto nombre) {
		tablaChatNombre.put(usuario, nombre);
		IOUtils.write(USUARIOS_PATH, tablaChatNombre);
	}

	public static UsuarioContexto obtenerContextoUsuario(String usuario) {
		return tablaChatNombre.get(usuario);

	}
	
	public static UsuarioContexto obtenerContextoUsuarioDNI(String dni) {
		for(UsuarioContexto valores : tablaChatNombre.values()){
			if(valores.getDNI().equals(dni)){
				return valores;
			}
		}
		return null;

	}
	
	public static String getUsuarioPersistenciaUsuarios(String nombre){
		Boolean found = false;
		Iterator<Entry<String, UsuarioContexto>> it = tablaChatNombre.entrySet().iterator();
		UsuarioContexto usuarioContexto;
		String usuario = "";
		
	    while (it.hasNext() && !found) {
	    	
	    	usuarioContexto = it.next().getValue();
	    	
	    	if(usuarioContexto.getNombre().equals(nombre)){
	    		found = true;
	    		usuario = usuarioContexto.getUsuario();
	    	}
	    }
	    
	    return usuario;
	}
	
	public static String getNombrePersistenciaUsuarios(String usuario){
		Boolean found = false;
		Iterator<Entry<String, UsuarioContexto>> it = tablaChatNombre.entrySet().iterator();
		UsuarioContexto usuarioContexto;
		String nombre = "";
		
	    while (it.hasNext() && !found) {
	    	
	    	usuarioContexto = it.next().getValue();
	    	
	    	if(usuarioContexto.getUsuario().equals(usuario)){
	    		found = true;
	    		nombre = usuarioContexto.getNombre();
	    	}
	    }
	    
	    return nombre;
	}

}
