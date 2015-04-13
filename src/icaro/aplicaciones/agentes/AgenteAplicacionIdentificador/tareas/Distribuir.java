package icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tareas;

import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.NotificacionPaciente;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaComunicacion;

/**
 *
 * @author Francisco J Garijo
 */
public class Distribuir extends TareaComunicacion {

	@Override
	public void ejecutar(Object... params) {

		this.getIdentTarea();
		this.getIdentAgente();
		Notificacion notif = (Notificacion) params[1];
		try {
			// Ver cómo diferencias si se envía al agente diálogo médico o al
			// paciente
			this.informaraOtroAgente(
					new NotificacionPaciente(notif),
					VocabularioGestionCitas.IdentAgenteAplicacionDialogoPaciente);
			// this.getEnvioHechos().insertarHecho(new
			// NotificacionPaciente(notif));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}