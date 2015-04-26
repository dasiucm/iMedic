/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.objetivos.ObtenerNombreUsuario;
import icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tools.conversacion;
import icaro.aplicaciones.informacion.gestionCitas.UsuarioContexto;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.persistenciaUsuarios.ItfPersistenciaUsuarios;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

/**
 *
 * @author Francisco J Garijo
 */
public class UsuarioIdentificado extends TareaSincrona {
	private Objetivo contextoEjecucionTarea = null;

	@Override
	public void ejecutar(Object... params) {

		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		String identInterlocutor = (String) params[0];
		UsuarioContexto cu = (UsuarioContexto) params[1];
		try {

			// // Se busca la interfaz del recurso en el repositorio de
			// interfaces
			ItfPersistenciaUsuarios persistencia = (ItfPersistenciaUsuarios) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoPersistenciaUsuario);
			UsuarioContexto ncu = persistencia.obtenerContextoUsuarioDNI(cu
					.getDNI());
			persistencia.insertarUsuario(cu.getDNI(),cu);
		

		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(
					identDeEstaTarea,
					contextoEjecucionTarea,
					identAgenteOrdenante,
					"Error-Acceso:Interfaz:"
							+ VocabularioGestionCitas.IdentRecursoComunicacionChat,
					CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}

}
