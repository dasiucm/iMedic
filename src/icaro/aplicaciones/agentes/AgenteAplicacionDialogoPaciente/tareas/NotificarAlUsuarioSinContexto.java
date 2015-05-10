package icaro.aplicaciones.agentes.AgenteAplicacionDialogoPaciente.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionDialogoPaciente.tools.conversacionPaciente;
import icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tools.conversacion;
import icaro.aplicaciones.informacion.gestionCitas.CitaMedica;
import icaro.aplicaciones.informacion.gestionCitas.FocoUsuario;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

/**
 *
 * @author Francisco J Garijo
 */
public class NotificarAlUsuarioSinContexto extends TareaSincrona {
	private Objetivo contextoEjecucionTarea = null;
	@Override
	public void ejecutar(Object... params) {

		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		FocoUsuario foUsuario = (FocoUsuario) params[0];
		String mensajeAenviar = "";
		try {
			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionChat);
			
			if(foUsuario.intentos < 2){
				mensajeAenviar = conversacion.msg("sincontexto");
				foUsuario.intentos = foUsuario.intentos+1;
			}else{
					
			
				
				for (Object g : this.getEnvioHechos().getItfMotorDeReglas()
						.getStatefulKnowledgeSession().getObjects()) {

					if (g instanceof CitaMedica) {
						CitaMedica ob = (CitaMedica) g;
						if(!ob.usuario.equals(foUsuario.getUsuario())) continue;
						
							
						if(ob.getFecha()== null && ob.getMedico() == null){
							mensajeAenviar = conversacionPaciente.msg("imperativoPedirDatos");
						}
						if(ob.getFecha()!= null && ob.getMedico() == null){
							mensajeAenviar = conversacionPaciente.msg("imperativoPedirDoctor");
						}
						if(ob.getFecha()== null && ob.getMedico() != null){
							mensajeAenviar = conversacionPaciente.msg("imperativoPedirFecha");
						}
						break;
					}
				
				}
				
				if(foUsuario.getFoco()!= null && foUsuario.getFoco().getgoalId().equals("ObtenerInfoCita") && foUsuario.getFoco().getState() == Objetivo.SOLVING){
					mensajeAenviar = conversacionPaciente.msg("imperativoConfirmar");
				}
				foUsuario.intentos = 0;
			}
			
			if (recComunicacionChat != null) {
			
				recComunicacionChat.comenzar(identAgenteOrdenante);
				recComunicacionChat.enviarMensagePrivado(foUsuario.getUsuario(),
						mensajeAenviar);
			} else {
				identAgenteOrdenante = this.getAgente().getIdentAgente();
				this.generarInformeConCausaTerminacion(
						identDeEstaTarea,
						contextoEjecucionTarea,
						identAgenteOrdenante,
						"Error-AlObtener:Interfaz:"
								+ VocabularioGestionCitas.IdentRecursoComunicacionChat,
						CausaTerminacionTarea.ERROR);
			}
			
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
