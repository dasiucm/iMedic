/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionIdentificador.tools.tipoNotif;
import icaro.aplicaciones.informacion.gestionCitas.FocoUsuario;
import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.NotificacionMedico;
import icaro.aplicaciones.informacion.gestionCitas.NotificacionPaciente;
import icaro.aplicaciones.informacion.gestionCitas.UsuarioContexto;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaComunicacion;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

/**
 *
 * @author Francisco J Garijo
 */
public class EliminarSessionUsuario extends TareaComunicacion{

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
				
				if(g instanceof UsuarioContexto ){
					UsuarioContexto ob = (UsuarioContexto) g;
					if(ob.getUsuario().equals(usuario)){
						this.getEnvioHechos().eliminarHechoWithoutFireRules(ob);
					}
				}
			
			}
			Notificacion notif = new Notificacion(usuario);
			notif.tipoNotificacion = tipoNotif.session;

			this.informaraOtroAgente(new NotificacionMedico(notif),
					VocabularioGestionCitas.IdentAgenteAplicacionDialogoMedico);

			this.informaraOtroAgente(new NotificacionPaciente(notif),
					VocabularioGestionCitas.IdentAgenteAplicacionDialogoPaciente);

			
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
