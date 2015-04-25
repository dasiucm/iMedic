package icaro.aplicaciones.recursos.persistenciaUsuarios.imp;

import icaro.aplicaciones.informacion.gestionCitas.UsuarioContexto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PersistenciaUsuariosImp implements Serializable {

	public static Map<String, UsuarioContexto> tablaChatNombre = new HashMap<String, UsuarioContexto>();

	/**
	 *
	 */
	private static final long serialVersionUID = 3791013440695899189L;

	public static void insertarUsuario(String usuario, UsuarioContexto nombre) {
		tablaChatNombre.put(usuario, nombre);
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

}
