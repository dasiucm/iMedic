/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.agentes.AgenteAplicacionDialogoPaciente.tareas;

import icaro.aplicaciones.informacion.gestionCitas.FocoUsuario;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

/**
 *
 * @author Francisco J Garijo
 */
public class EliminarSessionUsuario extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		String usuario = (String) params[0];
		try {

			for (Object g : this.getEnvioHechos().getItfMotorDeReglas()
					.getStatefulKnowledgeSession().getObjects()) {

				if (g instanceof Objetivo) {
					Objetivo ob = (Objetivo) g;
					if (ob.getobjectReferenceId().equals(usuario)) {
						this.getEnvioHechos().eliminarHechoWithoutFireRules(ob);
					}
				}

				if(g instanceof FocoUsuario ){
					FocoUsuario ob = (FocoUsuario) g;
					if(ob.getUsuario().equals(usuario)){
						this.getEnvioHechos().eliminarHechoWithoutFireRules(ob);
					}
				}
			
			}

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
