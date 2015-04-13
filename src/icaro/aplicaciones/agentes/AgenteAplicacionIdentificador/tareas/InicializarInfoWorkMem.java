/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tareas;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

/**
 *
 * @author Francisco J Garijo
 */
public class InicializarInfoWorkMem extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			this.getIdentTarea();
			this.getIdentAgente();
			this.getItfConfigMotorDeReglas()
					.setDepuracionActivationRulesDebugging(true);
			this.getItfConfigMotorDeReglas()
					.setfactHandlesMonitoring_afterActivationFired_DEBUGGING(
							true);
			// this.getEnvioHechos().insertarHecho(new MemUsuario());
			// this.getEnvioHechos().insertarHechoWithoutFireRules(new Focus());
			// this.getEnvioHechos().insertarHecho(new
			// ObtenerInformacionUsuario());

		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					"Error al ejecutar la tarea : " + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}

}
