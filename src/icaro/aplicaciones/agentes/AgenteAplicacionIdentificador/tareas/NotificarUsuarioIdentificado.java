package icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tareas;

import icaro.aplicaciones.informacion.gestionCitas.NotificacionIdentificado;
import icaro.aplicaciones.informacion.gestionCitas.NotificacionPaciente;
import icaro.aplicaciones.informacion.gestionCitas.UsuarioContexto;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaComunicacion;

/**
 *
 * @author Francisco J Garijo
 */
public class NotificarUsuarioIdentificado extends TareaComunicacion {

	@Override
	public void ejecutar(Object... params) {

		this.getIdentTarea();
		this.getIdentAgente();
		UsuarioContexto cu = (UsuarioContexto) params[0];
		try {

			
			
			NotificacionIdentificado nui = new NotificacionIdentificado(cu.getUsuario());
			nui.setTipoNotificacion("identificado");
			
			this.informaraOtroAgente(nui,VocabularioGestionCitas.IdentAgenteIdentificador);
			this.informaraOtroAgente(nui,VocabularioGestionCitas.IdentAgenteAplicacionDialogoMedico);
			this.informaraOtroAgente(nui,VocabularioGestionCitas.IdentAgenteAplicacionDialogoPaciente);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
