/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.objetivos.DistribuirMensajes;
import icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.objetivos.IdentificarUsuario;
import icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.objetivos.Inactividad;
import icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.objetivos.InactividadProlongada;
import icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.objetivos.ObtenerDNIUsuario;
import icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.objetivos.ObtenerNombreUsuario;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

import java.util.ArrayList;
import java.util.Collection;

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
			Collection<Object> objetosAinsertar = new ArrayList<Object>();
			objetosAinsertar.add(new DistribuirMensajes());
			objetosAinsertar.add(new IdentificarUsuario());
			objetosAinsertar.add(new Inactividad());
			objetosAinsertar.add(new InactividadProlongada());
			objetosAinsertar.add(new ObtenerDNIUsuario());
			objetosAinsertar.add(new ObtenerNombreUsuario());
			this.getEnvioHechos().insertarObjetosEnMiMemoria(objetosAinsertar);
			//this.getEnvioHechos().insertarHecho(new MemUsuario());
			//this.getEnvioHechos().insertarHechoWithoutFireRules(new Focus());
			//this.getEnvioHechos().insertarHecho(new ObtenerInformacionUsuario());

		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					"Error al ejecutar la tarea : " + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}

}
