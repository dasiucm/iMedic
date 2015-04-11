package icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionDialogoPaciente.tools.tipoNotifPaciente;
import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.NotificacionPaciente;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaComunicacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

/**
 *
 * @author Francisco J Garijo
 */
public class Distribuir extends TareaComunicacion {

	@Override
	public void ejecutar(Object... params) {

		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		String identInterlocutor = (String) params[0];
		Notificacion notif = (Notificacion) params[1];
		try {
			
			notif.setTipoNotificacion(tipoNotifPaciente.inicioPeticion);
			// Ver cómo diferencias si se envía al agente diálogo médico o al paciente
			this.informaraOtroAgente(new NotificacionPaciente(notif), VocabularioGestionCitas.IdentAgenteAplicacionDialogoPaciente);
			//this.getEnvioHechos().insertarHecho(new NotificacionPaciente(notif));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}