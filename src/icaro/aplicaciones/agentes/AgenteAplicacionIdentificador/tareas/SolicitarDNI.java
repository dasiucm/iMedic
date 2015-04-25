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
public class SolicitarDNI extends TareaSincrona {
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

			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionChat);

			ItfPersistenciaUsuarios persistencia = (ItfPersistenciaUsuarios) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoPersistenciaUsuario);
			UsuarioContexto ncu = persistencia.obtenerContextoUsuarioDNI(cu
					.getDNI());
			if (ncu != null) {
				cu.setNombre(ncu.getNombre());
				
				Objetivo f = new ObtenerNombreUsuario();
				f.setSolved();
				f.setobjectReferenceId(cu.getUsuario());
				this.getEnvioHechos().insertarHechoWithoutFireRules(f);
				this.getEnvioHechos().actualizarHecho(cu);

				if (recComunicacionChat != null) {
					recComunicacionChat.comenzar(identAgenteOrdenante);
					String mensajeAenviar = conversacion.usuarioRegistrado;
					recComunicacionChat.enviarMensagePrivado(identInterlocutor,
							mensajeAenviar);
				}

			} else {

				if (recComunicacionChat != null) {
					recComunicacionChat.comenzar(identAgenteOrdenante);
					String mensajeAenviar = conversacion.usuarioNoRegistrado;
					recComunicacionChat.enviarMensagePrivado(identInterlocutor,
							mensajeAenviar);
				}

			}

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
