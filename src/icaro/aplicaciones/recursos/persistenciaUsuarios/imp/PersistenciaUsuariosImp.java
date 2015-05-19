package icaro.aplicaciones.recursos.persistenciaUsuarios.imp;

import icaro.aplicaciones.informacion.IOUtils;
import icaro.aplicaciones.informacion.gestionCitas.UsuarioContexto;

import java.io.Serializable;
import java.util.Map;

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

}
