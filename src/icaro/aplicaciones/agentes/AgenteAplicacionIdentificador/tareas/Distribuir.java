package icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tareas;

import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.NotificacionPaciente;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

/**
 *
 * @author Francisco J Garijo
 */
public class Distribuir extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {

		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		String identInterlocutor = (String) params[0];
		Notificacion notif = (Notificacion) params[1];
		try {
			// Ver cómo diferencias si se envía al agente diálogo médico o al paciente

			this.getEnvioHechos().insertarHecho(new NotificacionPaciente(notif));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}