package icaro.aplicaciones.agentes.AgenteAplicacionDialogoPaciente.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionDialogoPaciente.tools.tipoNotifPaciente;
import icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tools.tipoNotif;
import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.NotificacionObjetivo;
import icaro.aplicaciones.informacion.gestionCitas.NotificacionPaciente;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaComunicacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

/**
 *
 * @author Francisco J Garijo
 */
public class NotificarObjetivo extends TareaComunicacion {

	@Override
	public void ejecutar(Object... params) {

		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		String identInterlocutor = (String) params[0];
		NotificacionPaciente notif = (NotificacionPaciente) params[1];
		String Objetivo = (String) params[2];
		try {
			NotificacionObjetivo no =null;
			if(notif != null){
				no = new NotificacionObjetivo(notif);
				no.agente = Objetivo;
				no.tipoNotificacion = tipoNotif.objetivo;
			}else{
				no = new NotificacionObjetivo();
				no.agente = Objetivo;
				no.identNotificador = identInterlocutor;
				no.tipoNotificacion = tipoNotif.objetivo;
			}
			// Ver cómo diferencias si se envía al agente diálogo médico o al paciente
			this.informaraOtroAgente(no, VocabularioGestionCitas.IdentAgenteIdentificador);
			//this.getEnvioHechos().insertarHecho(new NotificacionPaciente(notif));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}