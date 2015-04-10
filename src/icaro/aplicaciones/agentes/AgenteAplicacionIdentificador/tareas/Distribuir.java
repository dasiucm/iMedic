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
			// Ver c�mo diferencias si se env�a al agente di�logo m�dico o al paciente

			this.getEnvioHechos().insertarHecho(new NotificacionPaciente(notif));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}