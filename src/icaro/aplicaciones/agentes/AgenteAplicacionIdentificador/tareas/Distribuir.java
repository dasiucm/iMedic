package icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tareas;

import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.NotificacionMedico;
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
		String identidadPaciente = (String) params[2];
		try {
			if (identidadPaciente
					.equals(VocabularioGestionCitas.IdentAgenteAplicacionDialogoMedico)) {
				this.informaraOtroAgente(new NotificacionMedico(notif),
						identidadPaciente);
			} else if (identidadPaciente
					.equals(VocabularioGestionCitas.IdentAgenteAplicacionDialogoPaciente)) {
				this.informaraOtroAgente(new NotificacionPaciente(notif),
						identidadPaciente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
