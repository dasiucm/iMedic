package icaro.aplicaciones.informacion.gestionCitas;

import icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.objetivos.Inactividad;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

import java.util.HashMap;
import java.util.Map;

public class MemUsuario {

	public Map<String, UsuarioContexto> listUsuario = new HashMap<String, UsuarioContexto>();

	public void setFoco(String usuario, Objetivo foco) {
		if (listUsuario.get(usuario) != null) {
			listUsuario.get(usuario).foco.setFoco(foco);
		}
	}

	public Objetivo getFoco(String usuario) {
		if (listUsuario.get(usuario) != null) {
			return listUsuario.get(usuario).foco.getFoco();
		}
		return null;
	}

	public void eliminarUsuario(String usuario) {

		listUsuario.remove(usuario);
	}

	public boolean existeUsuario(String usuario) {
		return listUsuario.containsKey(usuario);
	}

	public UsuarioContexto usuario(String usuario) {
		if (listUsuario.get(usuario) != null) {
			return listUsuario.get(usuario);
		}
		return null;
	}

	public void crearUsuario(String usuario) {
		if (listUsuario.get(usuario) == null) {
			UsuarioContexto cu = new UsuarioContexto();
			cu.setUsuario(usuario);
			listUsuario.put(usuario, cu);
		}
	}

	public String obtenerUsuarioObjetivo(String ob, String estado) {
		for (UsuarioContexto uc : listUsuario.values()) {
			if (uc.foco.getFoco().getgoalId().equals(ob)
					&& uc.foco.getFoco().getStateAsString().equals(estado)) {
				return uc.usuario;
			}
		}
		return null;
	}

	public String usuarioInactivo(int tiempo) {
		System.out.println("PRueba inactividad");
		Objetivo ob = new Inactividad();
		for (UsuarioContexto uc : listUsuario.values()) {
			if (!uc.foco.getFoco().getgoalId().equals(ob)
					&& uc.inactividad(tiempo)) {
				return uc.usuario;
			}
		}
		return null;

	}

	public boolean estadoObjetivo(String usuario, String ob, String estado) {
		if (existeUsuario(usuario)
				&& listUsuario.get(usuario).foco.getFoco().getgoalId()
						.equals(ob)
				&& listUsuario.get(usuario).foco.getFoco().getStateAsString()
						.equals(estado)) {
			return true;
		}
		return false;
	}
}
